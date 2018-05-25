package com.beiyun.workers;

import com.beiyun.workers.entity.SearchPlantEntity;
import com.beiyun.workers.entity.TGLetterOfCommitmentBean;
import com.beiyun.workers.okhttp.callback.BaseInfo;
import com.beiyun.workers.okhttp.callback.ResponseTCallBack;
import com.beiyun.workers.utils.AppRequests;
import com.beiyun.workers.utils.GsonUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {


        String json = "{\"reason\":\"success\",\"data\":{\"total\":603,\"list\":[{\"linkTel\":\"18087172010\",\"status\":false,\"townName\":\"鹿阜街道办事处\",\"promise\":\"2017年03月16日\",\"farmerTel\":\"18087172010\",\"variety\":\"4\",\"id\":\"297e7c585a8f7f2e015ad5c6faa901bf\",\"expectArea\":\"24.0\",\"village\":\"青山村委会\",\"towns\":\"鹿阜街道办事处\",\"villageGroup\":\"吴家村\",\"name\":\"吴永恒\",\"year\":\"2017\",\"signature\":\"/image/01919f94-fadd-4856-90e7-9bf79ad6b7ca.jpg\"},{\"linkTel\":\"18208731950\",\"status\":false,\"townName\":\"鹿阜街道办事处\",\"promise\":\"2017年03月16日\",\"farmerTel\":\"18208731950\",\"variety\":\"4\",\"id\":\"297e7c585af9dd51015b143a92e200d9\",\"expectArea\":\"13.7\",\"village\":\"板桥村委会\",\"towns\":\"鹿阜街道办事处\",\"villageGroup\":\"马街2组\",\"name\":\"易春云\",\"year\":\"2017\",\"signature\":\"/image/321e5596-7ced-45cc-86a9-4050d04c7a97.jpg\"},{\"linkTel\":\"13888444654\",\"status\":false,\"townName\":\"鹿阜街道办事处\",\"promise\":\"2017年03月20日\",\"farmerTel\":\"13888444654\",\"variety\":\"4\",\"id\":\"402880825c9a3d05015c9b04268a0100\",\"expectArea\":\"24.0\",\"village\":\"青山村委会\",\"towns\":\"鹿阜街道办事处\",\"villageGroup\":\"三家村2组\",\"name\":\"王志芳\",\"year\":\"2017\",\"signature\":\"/image/4e2225b0-c800-4314-b310-268ad0d4eccd.jpg\"},{\"linkTel\":\"15911555690\",\"status\":false,\"townName\":\"鹿阜街道办事处\",\"promise\":\"2017年07月01日\",\"farmerTel\":\"15911555690\",\"variety\":\"4\",\"id\":\"402880825cf95e5a015cfd1f41fe0169\",\"expectArea\":\"10.6\",\"village\":\"青山村委会\",\"towns\":\"鹿阜街道办事处\",\"villageGroup\":\"青山\",\"name\":\"李红明\",\"year\":\"2017\",\"signature\":\"/image/93f27c9a-4770-4e59-8dd8-d9ea716744a0.jpg\"},{\"linkTel\":\"13888427340\",\"status\":false,\"townName\":\"鹿阜街道办事处\",\"promise\":\"2017年03月16日\",\"farmerTel\":\"13888427340\",\"variety\":\"4\",\"id\":\"402880825cf95e5a015cfd240941016a\",\"expectArea\":\"13.5\",\"village\":\"青山村委会\",\"towns\":\"鹿阜街道办事处\",\"villageGroup\":\"青山\",\"name\":\"毕正学\",\"year\":\"2017\",\"signature\":\"/image/12f66821-a9a6-49ce-861d-26565db87a8d.jpg\"},{\"linkTel\":\"13888103224\",\"status\":false,\"townName\":\"鹿阜街道办事处\",\"promise\":\"2017年03月16日\",\"farmerTel\":\"13888103224\",\"variety\":\"4\",\"id\":\"402880825cf95e5a015cfd2512a3016b\",\"expectArea\":\"10.1\",\"village\":\"青山村委会\",\"towns\":\"鹿阜街道办事处\",\"villageGroup\":\"青山\",\"name\":\"王志学\",\"year\":\"2017\",\"signature\":\"/image/070760dd-dfa6-46ea-b76f-ba51006560fc.jpg\"},{\"linkTel\":\"15559868270\",\"status\":false,\"townName\":\"鹿阜街道办事处\",\"promise\":\"2017年03月16日\",\"farmerTel\":\"15559868270\",\"variety\":\"4\",\"id\":\"402880825cf95e5a015cfd26a9f2016c\",\"expectArea\":\"10.2\",\"village\":\"青山村委会\",\"towns\":\"鹿阜街道办事处\",\"villageGroup\":\"青山\",\"name\":\"昂志英\",\"year\":\"2017\",\"signature\":\"/image/0cda5d2b-2079-4c23-a771-10a7b4a4d79e.jpg\"},{\"linkTel\":\"18214559029\",\"status\":false,\"townName\":\"鹿阜街道办事处\",\"promise\":\"2017年03月16日\",\"farmerTel\":\"18214559029\",\"variety\":\"4\",\"id\":\"402880825cf95e5a015cfd278774016d\",\"expectArea\":\"15.0\",\"village\":\"青山村委会\",\"towns\":\"鹿阜街道办事处\",\"villageGroup\":\"青山\",\"name\":\"毕贵义\",\"year\":\"2017\",\"signature\":\"/image/d91b16ba-aed3-4af2-ade3-fef0bca9f30f.jpg\"},{\"linkTel\":\"15912432925\",\"status\":false,\"townName\":\"鹿阜街道办事处\",\"promise\":\"2017年03月16日\",\"farmerTel\":\"15912432925\",\"variety\":\"4\",\"id\":\"402880825cf95e5a015cfd283b19016e\",\"expectArea\":\"17.0\",\"village\":\"青山村委会\",\"towns\":\"鹿阜街道办事处\",\"villageGroup\":\"青山\",\"name\":\"李国伟\",\"year\":\"2017\",\"signature\":\"/image/41b82fa7-1cdf-40e0-bb66-25579d65d982.jpg\"},{\"linkTel\":\"13888674314\",\"status\":false,\"townName\":\"鹿阜街道办事处\",\"promise\":\"2017年03月16日\",\"farmerTel\":\"13888674314\",\"variety\":\"4\",\"id\":\"402880825cf95e5a015cfd291e93016f\",\"expectArea\":\"11.1\",\"village\":\"青山村委会\",\"towns\":\"鹿阜街道办事处\",\"villageGroup\":\"青山\",\"name\":\"王文学\",\"year\":\"2017\",\"signature\":\"/image/980a1725-349e-45eb-85aa-75299d2e084c.jpg\"},{\"linkTel\":\"13668745248\",\"status\":false,\"townName\":\"鹿阜街道办事处\",\"promise\":\"2017年03月16日\",\"farmerTel\":\"13668745248\",\"variety\":\"4\",\"id\":\"402880825cf95e5a015cfd29f2c30170\",\"expectArea\":\"25.6\",\"village\":\"青山村委会\",\"towns\":\"鹿阜街道办事处\",\"villageGroup\":\"青山\",\"name\":\"王艳琼\",\"year\":\"2017\",\"signature\":\"/image/33f2175c-fe28-4949-895f-5143949e79f9.jpg\"},{\"linkTel\":\"13908878567\",\"status\":false,\"townName\":\"鹿阜街道办事处\",\"promise\":\"2017年03月16日\",\"farmerTel\":\"13908878567\",\"variety\":\"4\",\"id\":\"402880825cf95e5a015cfd2ac1760171\",\"expectArea\":\"10.1\",\"village\":\"青山村委会\",\"towns\":\"鹿阜街道办事处\",\"villageGroup\":\"青山\",\"name\":\"高建勇\",\"year\":\"2017\",\"signature\":\"/image/e391816e-4a4d-4157-937b-34233f0a8968.jpg\"},{\"linkTel\":\"13518788598\",\"status\":false,\"townName\":\"鹿阜街道办事处\",\"promise\":\"2017年03月16日\",\"farmerTel\":\"13518788598\",\"variety\":\"4\",\"id\":\"402880825cf95e5a015cfd2b9a470172\",\"expectArea\":\"15.6\",\"village\":\"青山村委会\",\"towns\":\"鹿阜街道办事处\",\"villageGroup\":\"青山\",\"name\":\"高建波\",\"year\":\"2017\",\"signature\":\"/image/59d2047a-7e5f-4471-97e4-d1f598e4bab8.jpg\"},{\"linkTel\":\"13808706411\",\"status\":false,\"townName\":\"鹿阜街道办事处\",\"promise\":\"2017年03月16日\",\"farmerTel\":\"13808706411\",\"variety\":\"4\",\"id\":\"402880825cf95e5a015cfd2ca1490173\",\"expectArea\":\"11.0\",\"village\":\"青山村委会\",\"towns\":\"鹿阜街道办事处\",\"villageGroup\":\"吴家村\",\"name\":\"李文\",\"year\":\"2017\",\"signature\":\"/image/e4d8fb93-7c9b-4686-bbb1-775d6401656f.jpg\"},{\"linkTel\":\"13888913554\",\"status\":false,\"townName\":\"鹿阜街道办事处\",\"promise\":\"2017年03月16日\",\"farmerTel\":\"13888913554\",\"variety\":\"4\",\"id\":\"402880825cf95e5a015cfd2d571f0174\",\"expectArea\":\"10.1\",\"village\":\"青山村委会\",\"towns\":\"鹿阜街道办事处\",\"villageGroup\":\"吴家村\",\"name\":\"李刚\",\"year\":\"2017\",\"signature\":\"/image/7fd3c848-eecc-4908-94e8-88468bcf810c.jpg\"},{\"linkTel\":\"15969548937\",\"status\":false,\"townName\":\"鹿阜街道办事处\",\"promise\":\"2017年03月16日\",\"farmerTel\":\"15969548937\",\"variety\":\"4\",\"id\":\"402880825cf95e5a015cfd2e0ee90175\",\"expectArea\":\"10.2\",\"village\":\"青山村委会\",\"towns\":\"鹿阜街道办事处\",\"villageGroup\":\"吴家村\",\"name\":\"周可芳\",\"year\":\"2017\",\"signature\":\"/image/cdc4368b-981b-4b57-8280-fa82d970668c.jpg\"},{\"linkTel\":\"13888718507\",\"status\":false,\"townName\":\"鹿阜街道办事处\",\"promise\":\"2017年03月16日\",\"farmerTel\":\"13888718507\",\"variety\":\"4\",\"id\":\"402880825cf95e5a015cfd2eefb50176\",\"expectArea\":\"15.2\",\"village\":\"青山村委会\",\"towns\":\"鹿阜街道办事处\",\"villageGroup\":\"吴家村\",\"name\":\"吴家宏\",\"year\":\"2017\",\"signature\":\"/image/022f67e9-d36d-4351-9269-d6e5eceffcbc.jpg\"},{\"linkTel\":\"13759511645\",\"status\":false,\"townName\":\"鹿阜街道办事处\",\"promise\":\"2017年03月16日\",\"farmerTel\":\"13759511645\",\"variety\":\"4\",\"id\":\"402880825cf95e5a015cfd2fa8880177\",\"expectArea\":\"10.0\",\"village\":\"青山村委会\",\"towns\":\"鹿阜街道办事处\",\"villageGroup\":\"吴家村\",\"name\":\"吴培兴\",\"year\":\"2017\",\"signature\":\"/image/4553e486-27cf-4be0-bbc9-38a41c59c9e3.jpg\"},{\"linkTel\":\"15812113759\",\"status\":false,\"townName\":\"鹿阜街道办事处\",\"promise\":\"2017年03月16日\",\"farmerTel\":\"15812113759\",\"variety\":\"4\",\"id\":\"402880825cf95e5a015cfd3293b00178\",\"expectArea\":\"14.0\",\"village\":\"青山村委会\",\"towns\":\"鹿阜街道办事处\",\"villageGroup\":\"吴家村\",\"name\":\"吴志刚\",\"year\":\"2017\",\"signature\":\"/image/8c4c3c30-3a6e-45c0-953c-6ee255b73c27.jpg\"},{\"linkTel\":\"13888488857\",\"status\":false,\"townName\":\"鹿阜街道办事处\",\"promise\":\"2017年03月16日\",\"farmerTel\":\"13888488857\",\"variety\":\"4\",\"id\":\"402880825cf95e5a015cfd333a5a0179\",\"expectArea\":\"13.6\",\"village\":\"青山村委会\",\"towns\":\"鹿阜街道办事处\",\"villageGroup\":\"吴家村\",\"name\":\"周春文\",\"year\":\"2017\",\"signature\":\"/image/7c77a983-9150-4a83-a105-4034bcfbd29e.jpg\"}]},\"resultCode\":100}";

        Gson gson = new Gson();
        ArrayList<TGLetterOfCommitmentBean> tgLetterOfCommitmentBeans = (ArrayList<TGLetterOfCommitmentBean>) GsonUtil.parseJson(json, new TypeToken<BaseInfo<ArrayList<TGLetterOfCommitmentBean>>>(){});
        System.out.print(tgLetterOfCommitmentBeans.get(0).getTownName());
//        for (TGLetterOfCommitmentBean bean:
//                tgLetterOfCommitmentBeans) {
//            System.out.print(bean);
//
//        }



    }

    @Test
    public void testPlant(){


        AppRequests.getPlantInfo(1, 2017, null, 1, new ResponseTCallBack<BaseInfo>() {
            @Override
            protected void onSuccess(BaseInfo data) {
                System.out.print(data);

            }

            @Override
            public void onFailure(IOException e) {
                System.out.print(e.getMessage());

            }
        });

    }
}