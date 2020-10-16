package com.qxy.design.strategy;

/**
 * @author wx
 * @date 2020/10/16 1:40 下午
 */
public class HuaWeiPayStrategy implements PayStrategy{
    @Override
    public void pay(double money) {
        double payMoney = money * 0.85;
        double discountMoney = money - payMoney;
        System.out.println("使用HuaWei pay成功支付" + payMoney + "元,优惠" + discountMoney + "元");
    }
}
