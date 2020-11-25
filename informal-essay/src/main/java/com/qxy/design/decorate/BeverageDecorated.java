package com.qxy.design.decorate;

import java.math.BigDecimal;

/**
 * @author wx
 * @date 2020/11/25 1:21 下午
 * 饮料抽象类
 */
public abstract class BeverageDecorated {

    protected String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }


    public abstract BigDecimal cost();
}
