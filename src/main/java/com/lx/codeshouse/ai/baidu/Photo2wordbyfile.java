package com.lx.codeshouse.ai.baidu;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.imageio.stream.FileImageInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by lee_xin on 17/11/10.
 * 读取图片文件 以流的方式 传给解析服务器
 */
public class Photo2wordbyfile {

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
        String path = "l16.1.png";
        byte[] file = readImageFile(path,client);


    }

    private static byte[] readImageFile(String path,AipOcr client){
        byte[] data = null;
        FileImageInputStream input = null;
        try {
            input = new FileImageInputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buf = new byte[1024*8*100];
            int numBytesRead = 0;
            String result ="";
            while ((numBytesRead = input.read(buf)) != -1) {
                JSONObject response = client.general(buf, new HashMap<String, String>());
                JSONArray wordarry =   response.getJSONArray("words_result");
                String resultstr = "";
                for(int i =0;i<wordarry.length();i++){
                    JSONObject wordb = wordarry.getJSONObject(i);
                    resultstr=resultstr+wordb.getString("words")+"\r\n";
                }
                System.out.println(resultstr);
//                output.write(buf, 0, numBytesRead);
            }
            data = output.toByteArray();
            output.close();
            input.close();
        }
        catch (FileNotFoundException ex1) {
            ex1.printStackTrace();
        }
        catch (IOException ex1) {
            ex1.printStackTrace();
        }
        return data;
    }
}
