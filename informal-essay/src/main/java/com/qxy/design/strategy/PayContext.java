package com.qxy.design.strategy;

/**
 * @author wx
 * @date 2020/10/16 1:46 下午
 */
public class PayContext {
    //依赖策略接口属性
    private PayStrategy payStrategy;

    public void setPayStrategy(PayStrategy payStrategy) {
        this.payStrategy = payStrategy;
    }

    /**
     * 提供外部访问方法
     * @param money
     */
    public void payMoney(double money) {
        payStrategy.pay(money);
    }
}
