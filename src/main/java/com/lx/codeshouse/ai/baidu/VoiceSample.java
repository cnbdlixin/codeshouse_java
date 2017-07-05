package com.lx.codeshouse.ai.baidu;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by lee_xin on 17/7/5.
 */
public class VoiceSample {
    //设置APPID/AK/SK
    public static final String APP_ID = "百度语音云申请";
    public static final String API_KEY = "百度语音云申请";
    public static final String SECRET_KEY = "百度语音云申请";

    public static void main(String[] args) throws IOException {

        // 初始化一个FaceClient
        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        VoiceService vservice = new VoiceService();
        vservice.synthesis(client);

//        // 调用API
//        JSONObject res = client.asr("test.pcm", "pcm", 16000, null);
//        System.out.println(res.toString(2));
    }

}
