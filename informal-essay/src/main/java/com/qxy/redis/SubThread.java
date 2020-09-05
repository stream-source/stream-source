package com.qxy.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author xy
 */
public class SubThread extends Thread {

    private final JedisPool jedisPool;

    private String channelName;

    private Subscriber subscriber;


    public SubThread(JedisPool jedisPool, String channelName, Subscriber subscriber) {
        super();
        this.jedisPool = jedisPool;
        this.channelName = channelName;
        this.subscriber = subscriber;
    }

    @Override
    public void run() {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //通过subscribe 的api去订阅，入参是订阅者和频道名
            jedis.subscribe(subscriber, channelName);
        } catch (Exception e) {
            System.out.println(String.format("订阅频道失败, 原因为：%s", e));
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
