package com.qxy.design.decorate;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author wx
 * @date 2020/11/25 1:36 下午
 * 装饰者类
 */
public abstract class CondimentDecorator extends BeverageDecorated {

    protected BeverageDecorated beverageDecorated;

    public CondimentDecorator(BeverageDecorated beverageDecorated) {
        this.beverageDecorated = beverageDecorated;
    }

    /**
     * 装饰者类中需要重新定义被装饰类中【非抽象方法】即需要装饰的方法，
     */
    @Override
    public String getDescription() {
        return beverageDecorated.getDescription();
    }

    @Override
    public BigDecimal cost() {
        return beverageDecorated.cost();
    }

}
