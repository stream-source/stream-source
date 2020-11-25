package com.qxy.design.decorate;

import com.qxy.design.decorate.BeverageDecorated;
import com.qxy.design.decorate.decorated.DarkRoast;
import com.qxy.design.decorate.decorated.Decaf;
import com.qxy.design.decorate.decorator.Whip;

/**
 * @author wx
 * @date 2020/11/25 2:12 下午
 */
public class Client {
    public static void main(String[] args) {

        BeverageDecorated darkRoast = new DarkRoast();
        System.out.println(darkRoast.getDescription() + ":" + darkRoast.cost());
        System.out.println("---------");
        //客户端调用装饰者对象和方法
        BeverageDecorated decaf = new Decaf();
        //转换为父类对象
        Whip whip = new Whip(decaf);
        System.out.println(whip.getDescription() + ":" + whip.cost());


    }
}
