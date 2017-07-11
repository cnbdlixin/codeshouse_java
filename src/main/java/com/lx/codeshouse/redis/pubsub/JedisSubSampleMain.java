package com.lx.codeshouse.redis.pubsub;

import com.lx.codeshouse.util.redis.JedisUtil;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * 在通道上做侦听，跑了一个单独线程
 * Created by lee_xin on 17/7/7.
 */
public class JedisSubSampleMain extends JedisPubSub {
    public static final String CHANNEL_NAME = "ca_test";
    private final static Logger LOGGER = Logger.getLogger(JedisSubSampleMain.class);
    public static void main(String[] args) throws Exception {
        final Jedis subscriberJedis = JedisUtil.jedisPool.getResource();
        final Subscriber subscriber = new Subscriber();
        LOGGER.info("start-----");
        SubMessageThread smt = new SubMessageThread();
        SubMessageThread smt2 = new SubMessageThread();
        new Thread(smt).start();
        new Thread(smt2).start();

        subscriber.unsubscribe();
        subscriberJedis.close();
    }

}
