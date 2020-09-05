package com.qxy.redis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Sub {
    public static void main(String[] args) throws InterruptedException {
        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(),"120.55.195.153",6379);
        ExecutorService executorService= Executors.newFixedThreadPool(50);

        Subscriber subscriber = new Subscriber("subscribeOne");

        SubThread channelOne = new SubThread(jedisPool, "channelOne", subscriber);
        executorService.submit(channelOne);

        SubThread channelTwo = new SubThread(jedisPool, "channelTwo", subscriber);
        executorService.submit(channelTwo);

        executorService.shutdown();
        executorService.awaitTermination(60, TimeUnit.SECONDS);

        Thread.sleep(60000);
        subscriber.onUnsubscribe("channelOne", 1);
    }
}
