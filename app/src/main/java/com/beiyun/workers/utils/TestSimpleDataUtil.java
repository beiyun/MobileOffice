package com.beiyun.workers.utils;

import com.beiyun.workers.R;
import com.beiyun.workers.entity.SearchPersonEntity;
import com.beiyun.workers.entity.SearchPlantEntity;
import com.beiyun.workers.entity.SearchPublicEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by beiyun on 2018/4/1.
 * Workers
 */
public class TestSimpleDataUtil {

    public static int[] BANNERS = {R.mipmap.banner1,R.mipmap.banner2,
            R.mipmap.banner3,R.mipmap.banner4,R.mipmap.banner5
            ,R.mipmap.banner6};

    public static int[] IMAGE_RES = {R.mipmap.nz1,R.mipmap.nz2,
            R.mipmap.nz3,R.mipmap.nz4,R.mipmap.nz5,
            R.mipmap.nz6,R.mipmap.nz7,R.mipmap.nz8,
            R.mipmap.nz9,R.mipmap.nz10,};

    public static String[] VIDEO_URLS = {
            "http://jzvd.nathen.cn/6ea7357bc3fa4658b29b7933ba575008/fbbba953374248eb913cb1408dc61d85-5287d2089db37e62345123a1be272f8b.mp4",
            "http://jzvd.nathen.cn/35b3dc97fbc240219961bd1fccc6400b/8d9b76ab5a584bce84a8afce012b72d3-5287d2089db37e62345123a1be272f8b.mp4",
            "http://jzvd.nathen.cn/df6096e7878541cbbea3f7298683fbed/ef76450342914427beafe9368a4e0397-5287d2089db37e62345123a1be272f8b.mp4",
            "http://jzvd.nathen.cn/384d341e000145fb82295bdc54ecef88/103eab5afca34baebc970378dd484942-5287d2089db37e62345123a1be272f8b.mp4",
            "http://jzvd.nathen.cn/f55530ba8a59403da0621cbf4faef15e/adae4f2e3ecf4ea780beb057e7bce84c-5287d2089db37e62345123a1be272f8b.mp4",
            "http://jzvd.nathen.cn/6340efd1962946ad80eeffd19b3be89c/65b499c0f16e4dd8900497e51ffa0949-5287d2089db37e62345123a1be272f8b.mp4",
            "http://jzvd.nathen.cn/f07fa9fddd1e45a6ae1570c7fe7967c1/c6db82685b894e25b523b1cb28d79f2e-5287d2089db37e62345123a1be272f8b.mp4",
            "http://jzvd.nathen.cn/d2e969f2ec734520b46ab0965d2b68bd/f124edfef6c24be8b1a7b7f996ccc5e0-5287d2089db37e62345123a1be272f8b.mp4",
            "http://jzvd.nathen.cn/4f965ad507ef4194a60a943a34cfe147/32af151ea132471f92c9ced2cff785ea-5287d2089db37e62345123a1be272f8b.mp4",
            "http://jzvd.nathen.cn/342a5f7ef6124a4a8faf00e738b8bee4/cf6d9db0bd4d41f59d09ea0a81e918fd-5287d2089db37e62345123a1be272f8b.mp4"
    };

    public static final String[] VIDEO_THUMBS = {
            "http://jzvd-pic.nathen.cn/jzvd-pic/bd7ffc84-8407-4037-a078-7d922ce0fb0f.jpg",
            "http://jzvd-pic.nathen.cn/jzvd-pic/f2dbd12e-b1cb-4daf-aff1-8c6be2f64d1a.jpg",
            "http://jzvd-pic.nathen.cn/jzvd-pic/ccd86ca1-66c7-4331-9450-a3b7f765424a.png",
            "http://jzvd-pic.nathen.cn/jzvd-pic/2adde364-9be1-4864-b4b9-0b0bcc81ef2e.jpg",
            "http://jzvd-pic.nathen.cn/jzvd-pic/2a877211-4b68-4e3a-87be-6d2730faef27.png",
            "http://jzvd-pic.nathen.cn/jzvd-pic/aaeb5da9-ac50-4712-a28d-863fe40f1fc6.png",
            "http://jzvd-pic.nathen.cn/jzvd-pic/e565f9cc-eedc-45f0-99f8-5b0fa3aed567%281%29.jpg",
            "http://jzvd-pic.nathen.cn/jzvd-pic/3430ec64-e6a7-4d8e-b044-9d408e075b7c.jpg",
            "http://jzvd-pic.nathen.cn/jzvd-pic/2204a578-609b-440e-8af7-a0ee17ff3aee.jpg",
            "http://jzvd-pic.nathen.cn/jzvd-pic/1bb2ebbe-140d-4e2e-abd2-9e7e564f71ac.png"
    };

    public static final List<String> getPersons(){
        final List<String> persons = new ArrayList<>();
        persons.add("职工");
        persons.add("辅导员");
        persons.add("烟农");
        persons.add("非烟农");
        return persons;

    }


    public static final List<String> getPlantCategory(){
        final List<String> plantCategories = new ArrayList<>();
        plantCategories.add("种植承诺");
        plantCategories.add("种植申请");
        plantCategories.add("资格审查");
        plantCategories.add("预签合同");
        plantCategories.add("清塘点株");
        plantCategories.add("种植合同");
        plantCategories.add("烤烟预约");
        plantCategories.add("收购预约");

        return plantCategories;
    }


    public static final List<SearchPersonEntity> getSearchPersonData(){
        List<SearchPersonEntity> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            SearchPersonEntity entity = new SearchPersonEntity();
            if(i%2 == 0){
                entity.setPersonCall("12658264854");
                entity.setPersonCompanyName("石林烟站");
                entity.setPersonName("上官云燕");
            }else{
                entity.setPersonCall("16584568455");
                entity.setPersonCompanyName("Apple Park");
                entity.setPersonName("Mark Dona");
            }
            data.add(entity);
        }

        return data;
    }


    public static final List<SearchPlantEntity> getSearchPlantData(){
        List<SearchPlantEntity> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            SearchPlantEntity entity = new SearchPlantEntity();
            if(i%2 == 0){
                entity.setPlantCall("12658264854");
                entity.setPlantName("上官云燕");
            }else{
                entity.setPlantCall("16584568455");
                entity.setPlantName("Mark Dona");
            }
            data.add(entity);
        }

        return data;
    }


    public static final List<SearchPublicEntity> getSearchPublicData(){
        List<SearchPublicEntity> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            SearchPublicEntity entity = new SearchPublicEntity();
            if(i%2 == 0){
                entity.setPublicTime("2018-05-04");
                entity.setPublicTitle("奉天承运皇帝诏曰");
                entity.setPublicCompanyName("乾清宫");
            }else{
                entity.setPublicTime("2018-03-28");
                entity.setPublicTitle("This day we'll Fight Syria");
                entity.setPublicCompanyName("White House");
            }
            data.add(entity);
        }

        return data;
    }

    public static String getUserNumber(){
        return "5301260301";
    }

}
