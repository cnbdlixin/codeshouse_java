package com.lx.codeshouse.redis.pubsub;

import com.lx.codeshouse.util.redis.JedisUtil;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * 往通道上发送消息
 * Created by lee_xin on 17/7/7.
 */
public class JedisPubSampleMain extends JedisPubSub {
    public static final String CHANNEL_NAME = "ca_test";
    private final static Logger LOGGER = Logger.getLogger(JedisPubSampleMain.class);
    public static void main(String[] args) throws Exception {
        final Jedis publisherJedis = JedisUtil.jedisPool.getResource();

        //主线程：发布消息到CHANNEL_NAME频道上
        new Publisher(publisherJedis, CHANNEL_NAME).startPublish();
        publisherJedis.close();

        //Unsubscribe
    }

}
