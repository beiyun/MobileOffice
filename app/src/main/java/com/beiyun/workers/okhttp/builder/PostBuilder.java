package com.beiyun.workers.okhttp.builder;





import com.beiyun.workers.okhttp.request.OkHttpRequest;
import com.beiyun.workers.okhttp.request.PostRequest;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by beiyun on 2016/8/4.
 *
 */
public class PostBuilder extends OkHttpRequestBuilder<PostBuilder> implements HashParamsable{

    private ArrayList<FileInput> files = new ArrayList<>();

    @Override
    public OkHttpRequest build() {
        return new PostRequest(files,params,headers,tag,url);
    }

    @Override
    public PostBuilder params(HashMap<String, String> params) {

        this.params = params;
        return this;
    }

    @Override
    public PostBuilder addParams(String key, String val) {

        if (this.params == null) {
            this.params = new LinkedHashMap<>();
        }
        this.params.put(key,val);
        return this;
    }

    public PostBuilder addFile(File file, String filename, String key){

        files.add(new FileInput(file,filename,key));
        return this;
    }

    public PostBuilder files(String key, HashMap<String, File> files){
        if (files == null) return this;

        for (String filename : files.keySet()){
            this.files.add(new FileInput(files.get(filename),filename,key));
        }
        return this;
    }


    public static class FileInput{

        public String key;
        public String filename;
        public File file;

        public FileInput(File file, String filename, String key) {
            this.file = file;
            this.filename = filename;
            this.key = key;
        }

        @Override
        public String toString() {
            return "FileInput{" +
                    "file=" + file +
                    ", key='" + key + '\'' +
                    ", filename='" + filename + '\'' +
                    '}';
        }
    }
}
