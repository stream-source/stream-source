package com.qxy.design.strategy;

/**
 * @author wx
 * @date 2020/10/16 1:41 下午
 */
public class ApplePayStrategy implements PayStrategy{
    @Override
    public void pay(double money) {
        double payMoney = money * 0.82;
        double discountMoney = money - payMoney;
        System.out.println("使用Apple pay成功支付" + payMoney + "元,优惠" + discountMoney + "元");
    }
}
