package com.lx.codeshouse.message;


import com.lx.codeshouse.util.MessageHttpUtil;

/**
 * Created by lee_xin on 17/7/4.
 */
public class MDSmsSendMain {
    private static String operation = "/industrySMS/sendSMS";

    private static String accountSid = Config.ACCOUNT_SID;
    private static String to = "18510512189";
    private static String smsContent = "【顺丰店配系统】尊敬的用户：您的注册验证码为12345，有效期为20分钟，请勿将验证码告知他人。";

    public static void main(String[] args) {
        String url = Config.BASE_URL + operation;
        String body = "accountSid=" + accountSid + "&to=" + to + "&smsContent=" + smsContent
                + MessageHttpUtil.createCommonParam();

        // 提交请求
        String result = MessageHttpUtil.post(url, body);
        System.out.println("result:"  + result);
    }


}
