package com.lx.codeshouse.ai.baidu;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;

import java.io.*;
import java.util.HashMap;

/**
 * Created by lee_xin on 17/7/5.
 * java开发api
 * http://ai.baidu.com/docs#/TTS-Online-Java-SDK/top
 */
public class VoiceService {
    public void synthesis(AipSpeech client) throws IOException {
//        TtsResponse res = client.synthesis("你好百度", "zh", 1, null);
//        System.out.println(res.getErrorCode());

        // 设置可选参数
        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("spd", "5");
        options.put("pit", "5");
        options.put("per", "4");
        String content ="百度AI开放平台,是面向企业/机构/创业者/开发者,将百度在人工智能领域积累的技术以API或SDK等形式对外共享的在线平台";
        TtsResponse res = client.synthesis(content, "zh", 1, options);
        System.out.println(res.getErrorCode());
        byte[] datav = res.getData();
        FileOutputStream out = null;

        FileOutputStream outSTr = null;

        BufferedOutputStream Buff=null;
        outSTr = new FileOutputStream(new File("aic.wav"));

        Buff=new BufferedOutputStream(outSTr);

        Buff.write(datav);
        Buff.flush();

        Buff.close();
    }
}
