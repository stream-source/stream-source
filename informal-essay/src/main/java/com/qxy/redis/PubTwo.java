package com.qxy.redis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PubTwo {
    public static void main(String[] args) throws Exception {
        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(),"120.55.195.153",6379);
        ExecutorService executorService= Executors.newFixedThreadPool(50);

        PubThread pubTwoThread = new PubThread(jedisPool,"pubTwo");
        executorService.submit(pubTwoThread);
        executorService.shutdown();
        executorService.awaitTermination(60, TimeUnit.SECONDS);
    }
}
