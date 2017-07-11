package com.lx.codeshouse.redis.pubsub;

import com.lx.codeshouse.util.redis.JedisUtil;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;

/**
 * Created by lee_xin on 17/7/11.
 */
public class SubMessageThread implements  Runnable{
    private final static Logger LOGGER = Logger.getLogger(SubMessageThread.class);
    final Jedis subscriberJedis = JedisUtil.jedisPool.getResource();
    final Subscriber subscriber = new Subscriber();
    final String CHANNEL_NAME = "ca_test";
    public void run() {
        try {
            LOGGER.info("Subscribing to \"MyChannel\". This thread will be blocked.");
            //使用subscriber订阅CHANNEL_NAME上的消息，这一句之后，线程进入订阅模式，阻塞。
            subscriberJedis.subscribe(subscriber, CHANNEL_NAME);
            //当unsubscribe()方法被调用时，才执行以下代码
            LOGGER.info("Subscription ended.");
        } catch (Exception e) {
            LOGGER.error("Subscribing failed.", e);
        }
    }
}
