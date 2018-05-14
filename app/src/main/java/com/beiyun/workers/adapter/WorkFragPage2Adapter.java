package com.beiyun.workers.adapter;

import android.view.View;

import com.beiyun.workers.R;
import com.beiyun.workers.entity.WorkFrag2Entity;
import com.beiyun.workers.entity.WorkFrag2ExpandReasonEntity;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by beiyun on 2018/4/6.
 * Workers
 */
public class WorkFragPage2Adapter extends BaseMultiItemQuickAdapter<MultiItemEntity,BaseViewHolder> {

    public static final int TYPE_HEADLER = -1;
    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public WorkFragPage2Adapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_LEVEL_0,R.layout.item_work_frag_page_2);
        addItemType(TYPE_LEVEL_1,R.layout.item_work_frag_page_2_item);
    }



    @Override
    protected void convert(final BaseViewHolder helper, final MultiItemEntity item) {

        switch (helper.getItemViewType()){

            case TYPE_LEVEL_0:
                final WorkFrag2Entity entity = (WorkFrag2Entity) item;
                helper.setText(R.id.item_name,entity.getName())
                        .setImageResource(R.id.item_complete_state,entity.isComplete()? R.drawable.ic_done_24dp : R.drawable.ic_clear_24dp)
                        .setText(R.id.item_complete_time,entity.isComplete()?entity.getCompleteTime():"——");

                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!entity.isComplete()){
                            if(entity.isExpanded()){
                                collapse(helper.getAdapterPosition());
                            }else{
                                expand(helper.getAdapterPosition());
                            }
                        }
                    }
                });
                break;
            case TYPE_LEVEL_1:
                WorkFrag2ExpandReasonEntity reasonEntity = (WorkFrag2ExpandReasonEntity) item;
                helper.setText(R.id.item_none_complete_reason,reasonEntity.getNoneCompleteReason());
                break;
        }

    }
}
