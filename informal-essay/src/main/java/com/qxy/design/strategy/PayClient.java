package com.qxy.design.strategy;

/**
 * @author wx
 * @date 2020/10/16 1:50 下午
 */
public class PayClient {
    public static void main(String[] args) {
        //创建支付上下文
        PayContext payContext = new PayContext();
        //创建支付策略
        PayStrategy miPayStrategy = new MiPayStrategy();
        payContext.setPayStrategy(miPayStrategy);
        payContext.payMoney(100);
        System.out.println("-------改变支付策略--------");
        PayStrategy applePayStrategy = new ApplePayStrategy();
        payContext.setPayStrategy(applePayStrategy);
        payContext.payMoney(100);

    }
}
