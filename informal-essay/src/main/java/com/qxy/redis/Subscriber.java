package com.qxy.redis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

/**
 * @author xy
 * 订阅者类
 */
public class Subscriber extends JedisPubSub {

    /**订阅者名字*/
    private String subName;

    public Subscriber(String subName) {
        this.subName = subName;
    }

    /**
     * 打印接收的消息内容
     * @param channel
     * @param message
     */
    @Override
    public void onMessage(String channel, String message) {
        super.onMessage(channel, message);
        System.out.println(String.format("订阅者: %s, 成功订阅[%s]频道, 内容为： %s", subName, channel, message));
    }

    /**
     * 订阅指定channel
     * @param channel
     * @param subscribedChannels
     */
    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.println(String.format("订阅者: %s, 成功订阅[%s]频道, 订阅频道数：%d", subName, channel, subscribedChannels));
    }

    /**
     * 取消订阅指定channel
     * @param channel
     * @param subscribedChannels
     */
    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        System.out.println(String.format("订阅者: %s, 成功取消订阅[%s]频道, 频道数：%d", subName, channel, subscribedChannels));
    }
}
