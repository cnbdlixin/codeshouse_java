package com.lx.codeshouse.ai.baidu;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lee_xin on 17/11/10.
 */
public class Photo2word {

    //设置APPID/AK/SK
    public static final String APP_ID = "10346803";
    public static final String API_KEY = "3i5UPgP56LU1OViIdjKLy9AS";
    public static final String SECRET_KEY = "0n5iwEP3uCDaqI0aQAQ2XrUMBBRGF6j2";

    public static void main(String[] args) {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

//        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 调用接口
        String path = "l26.2.png";
        JSONObject res = client.basicGeneral(path, new HashMap<String, String>());
        JSONArray wordarry =   res.getJSONArray("words_result");
        String a = "";

        for(int i =0;i<wordarry.length();i++){
            JSONObject wordb = wordarry.getJSONObject(i);
            a=a+wordb.getString("words")+"\r\n";
        }


//        JSONObject wordobject = res.get("words_result");

        System.out.println(a);

    }

}
