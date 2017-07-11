package com.lx.codeshouse.redis.pubsub;

import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lee_xin on 17/7/7.
 */
public class Publisher {
    private static final Logger LOGGER = Logger.getLogger(Publisher.class);
    private final Jedis publisherJedis;
    private final String channel;

    public Publisher(Jedis publisherJedis, String channel) {
        this.publisherJedis = publisherJedis;
        this.channel = channel;
    }

    /**
     * 不停的读取输入，然后发布到channel上面，遇到quit则停止发布。
     */
    public void startPublish() {
        LOGGER.info("Type your message (quit for terminate)");
        try {
            for(int i=1;i<100;i++){
                Thread.sleep(1000);
                Date date = new Date();
                SimpleDateFormat myFmt=new SimpleDateFormat("yy年MM月dd日 HH时mm分");
                String dateStr = myFmt.format(date);
                publisherJedis.publish(channel, dateStr);
            }

        } catch (Exception e) {
            LOGGER.error("IO failure while reading input", e);
        }
    }
}
