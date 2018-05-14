package com.beiyun.workers.entity;

import com.beiyun.workers.R;
import com.beiyun.library.util.Logs;

public class News {


    private String content;
    private String id;
    private String title;
    private String plate;
    private int counter;
    private int titleImage;
    private int readCount;
    private String auditTime;
    private String img;

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(int titleImage) {
        this.titleImage = titleImage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }


    @Override
    public String toString() {
        return "News{" +
                "content='" + content + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", plate='" + plate + '\'' +
                ", counter=" + counter +
                ", titleImage=" + titleImage +
                ", readCount=" + readCount +
                ", auditTime='" + auditTime + '\'' +
                ", img='" + img + '\'' +
                '}';
    }

    /**
     * 1 石烟动态 2 聚焦国内外 3 行业动态 4 石烟文苑
     * 5 基层党建 6 烟草标准 7 精益管理 8 廉政建设 9 政策法规
     */
    public static String getPlateString(int plate){
        String palate = null;
        switch (plate){
            case 1:
                palate = "石烟动态";
                break;
            case 2:
                palate = "聚焦国内外";
                break;
            case 3:
                palate = "行业动态";
                break;
            case 4:
                palate = "石烟文苑";
                break;
            case 5:
                palate = "基层党建";
                break;
            case 6:
                palate = "烟草标准";
                break;
            case 7:
                palate = "精益管理";
                break;
            case 8:
                palate = "廉政建设";
                break;
            case 9:
                palate = "政策法规";
                break;
                default:

        }

        return palate;
    }


    static int[] res = {R.mipmap.nz1,R.mipmap.nz2,
            R.mipmap.nz3,R.mipmap.nz4,R.mipmap.nz5,
            R.mipmap.nz6,R.mipmap.nz7,R.mipmap.nz8,
            R.mipmap.nz9,R.mipmap.nz10,};

    public static int getTitleImageRes(int position){
        try {
            String intString = String.valueOf(position);
            String s = intString.substring(intString.length() - 1);
            Logs.e("News getTitleImageRes position = "+position+" s = "+s );
            return res[Integer.valueOf(s)];
        }catch (Exception e){
            e.printStackTrace();
        }

        return 0;
    }


    public static int getCounter(int position){
        if(position%2 == 0){
            return 1314;
        }else{
            return 3219;
        }
    }
}
