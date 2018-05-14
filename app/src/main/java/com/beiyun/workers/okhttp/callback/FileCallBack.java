package com.beiyun.workers.okhttp.callback;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Response;

/**
 * Created by beiyun on 2016/8/4.
 */
public abstract class FileCallBack extends CallBack {

    @Override
    public File parseResponse(Response response) throws IOException {
        return saveFile(response);
    }

    /**
     * 目标文件存储的文件夹路径
     */
    private String destFileDir;
    /**
     * 目标文件存储的文件名
     */
    private String destFileName;


    public FileCallBack(String destFileDir, String destFileName) {
        this.destFileDir = destFileDir;
        this.destFileName = destFileName;
    }



    public File saveFile(Response response) throws IOException {

        InputStream is = null;
        byte[] buf = new byte[2048];
        int len;
        FileOutputStream fos = null;
        try {

            is = response.body().byteStream();
            final long total = response.body().contentLength();
            long sum = 0;

            File dir = new File(destFileDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File file = new File(dir, destFileName);
            fos = new FileOutputStream(file);

            while ((len = is.read(buf)) != -1){
                sum += len;
                fos.write(buf, 0, len);
                final long finalSum = sum;
                inProgress(finalSum * 1.0f / total,total,finalSum == total);
            }
            fos.flush();

            return file;

        }finally{

            try{
                response.body().close();
                if (is != null) is.close();
            } catch (IOException ignored){}

            try{
                if (fos != null) fos.close();
            } catch (IOException ignored){}

        }
    }
}
