package com.qxy.redis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PubOne {
    public static void main(String[] args) throws Exception {
        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(),"120.55.195.153",6379);
        ExecutorService executorService= Executors.newFixedThreadPool(50);

        PubThread pubOneThread = new PubThread(jedisPool,"pubOne");
        executorService.submit(pubOneThread);
        executorService.shutdown();
        executorService.awaitTermination(60, TimeUnit.SECONDS);
    }
}
