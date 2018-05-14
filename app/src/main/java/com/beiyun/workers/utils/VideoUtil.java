package com.beiyun.workers.utils;

import com.beiyun.library.util.Logs;

import java.util.HashMap;

/**
 * Created by beiyun on 2018/4/11.
 * Workers
 */
public class VideoUtil {

    public static String getRingDuring(String mUri){
        String duration=null;
        android.media.MediaMetadataRetriever mmr = new android.media.MediaMetadataRetriever();

        try {
            if (mUri != null) {
                HashMap<String, String> headers;
                headers = new HashMap<>();
                headers.put("User-Agent", "Mozilla/5.0 (Linux; U; Android 4.4.2; zh-CN; MW-KW-001 Build/JRO03C) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 UCBrowser/1.0.0.001 U4/0.8.0 Mobile Safari/533.1");
                mmr.setDataSource(mUri, headers);
            }

            duration = mmr.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_DURATION);
        } catch (Exception ignored) {
        } finally {
            mmr.release();
        }
        Logs.e("LearnPage1Adapter getRingDuring:" + ""+duration);
        return duration;
    }
}
