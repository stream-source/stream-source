package com.qxy.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author xy
 *  发布者
 */
public class PubThread extends Thread {

    private final JedisPool jedisPool;
    /**发布频道名称*/
    private String channelName;

    private static final String QUIT = "quit";

    public PubThread(JedisPool jedisPool, String channelName) {
        super();
        this.jedisPool = jedisPool;
        this.channelName = channelName;
    }

    @Override
    public void run() {
        //定义字符缓冲区，从控制台输入发布内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Jedis jedis = jedisPool.getResource();
        while (true) {
            String line = null;
            try {
                line = reader.readLine();
                if (!QUIT.equals(line)) {
                    //发布消息
                    jedis.publish(channelName, line);
                    System.out.println("publish success,channelName: " + channelName);
                } else {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
