package com.beiyun.workers.view;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.beiyun.library.util.AppCaches;
import com.beiyun.library.util.Apps;
import com.beiyun.library.util.Logs;
import com.beiyun.workers.R;
import com.beiyun.workers.entity.VideoEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.jzvd.JZUtils;

/**
 * Created by beiyun on 2018/4/11.
 * Workers
 */
public class VideoListDialog extends AlertDialog {

    private RecyclerView videoList;
    private ContentLoadingProgressBar progressBar;
    private TextView text;

    public VideoListDialog(@NonNull Context context) {
        this(context,true,null);
    }

    public VideoListDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    private VideoListDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        View view = Apps.getLayoutInflater().inflate(R.layout.view_video_list_dialog, null);
        videoList = view.findViewById(R.id.video_list);
        progressBar = view.findViewById(R.id.video_progress);
        text = view.findViewById(R.id.video_text);
        videoList.setLayoutManager(new LinearLayoutManager(getContext()));
        videoList.setHasFixedSize(true);
        setView(view);
        setTitle("本地视频列表");
    }

    @Override
    public void show() {
        super.show();
        new VideoData().start();

    }

    private List<VideoEntity> mData = new ArrayList<>();

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressLint("ShowToast")
        public void handleMessage(android.os.Message msg) {
            Logs.e("VideoListDialog handleMessage: videoList = " + videoList);

            if (msg.what == 1&&mData!=null && videoList != null) {
                Logs.e("VideoListDialog handleMessage:" + ""+mData);
                VideoListAdapter adapter = new VideoListAdapter(mData);
                videoList.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);
                if(mData.size() == 0){
                    text.setText("本地没有找到视频");
                }
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        if(mListener != null){
                            mListener.onItemClick(mData.get(position));
                            dismiss();
                        }
                    }
                });
            }
        };
    };

    private OnItemClickListener mListener;
    public interface OnItemClickListener{
        void onItemClick(VideoEntity entity);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }

    /**
     *
     * 遍历系统数据库找出相应的是视频的信息，每找出一条视频信息的同时利用与之关联的找出对应缩略图的uri
     * 再异步加载缩略图，
     * 由于查询速度非常快，全部查找完成在设置，等待时间不会太长
     * @author Administrator
     *
     */
    class VideoData extends Thread {


        @Override
        public void run() {
            // 如果有sd卡（外部存储卡）
            if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
                Uri originalUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                ContentResolver cr = getContext().getContentResolver();
                Cursor cursor = cr.query(originalUri, null, null, null, null);
                if (cursor == null) {
                    return;
                }
                for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                    String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE));
                    String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
                    long size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE));
                    long duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION));
                    //获取当前Video对应的Id，然后根据该ID获取其缩略图的uri
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID));
                    String[] selectionArgs = new String[] { id+""};
                    String[] thumbColumns = new String[] { MediaStore.Video.Thumbnails.DATA,
                            MediaStore.Video.Thumbnails.VIDEO_ID };
                    String selection = MediaStore.Video.Thumbnails.VIDEO_ID + "=?";

                    String uri_thumb = "";
                    Cursor thumbCursor = (getContext().getContentResolver()).query(
                            MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI, thumbColumns, selection, selectionArgs,
                            null);

                    if (thumbCursor != null && thumbCursor.moveToFirst()) {
                        uri_thumb = thumbCursor
                                .getString(thumbCursor.getColumnIndexOrThrow(MediaStore.Video.Thumbnails.DATA));

                    }


                    VideoEntity entity = new VideoEntity();
                    entity.setPath(path);
                    entity.setDuration(duration);
                    entity.setSize(size);
                    entity.setTitle(title);
                    entity.setThumbImage(uri_thumb);
                    mData.add(entity);
                    assert thumbCursor != null;
                    thumbCursor.close();

                }
                mHandler.sendEmptyMessage(1);
                cursor.close();

            }
        }
    }


    class VideoListAdapter extends BaseQuickAdapter<VideoEntity,BaseViewHolder>{

        VideoListAdapter(@Nullable List<VideoEntity> data) {
            super(R.layout.item_video_list_dialog,data);
        }

        @Override
        protected void convert(BaseViewHolder helper, VideoEntity item) {
            helper.setText(R.id.item_video_list_duration, JZUtils.stringForTime(item.getDuration()))
                    .setText(R.id.item_video_list_title,item.getTitle())
                    .setText(R.id.item_video_list_size, AppCaches.getFormatSize(item.getSize()));
            SimpleDraweeView draweeView = helper.getView(R.id.item_video_list_image);
            draweeView.setImageURI(UriUtil.getUriForFile(new File(item.getThumbImage())));

        }
    }

}
