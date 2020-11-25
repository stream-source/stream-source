package com.qxy.design.decorate.decorator;

import com.qxy.design.decorate.BeverageDecorated;
import com.qxy.design.decorate.CondimentDecorator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author wx
 * @date 2020/11/25 1:51 下午
 * 具体装饰者之一
 */
public class Mocha extends CondimentDecorator {

    public Mocha(BeverageDecorated beverageDecorated) {
        super(beverageDecorated);
    }

    @Override
    public String getDescription() {
        return beverageDecorated.getDescription() +"," + mochaDescription();
    }

    @Override
    public BigDecimal cost() {
        return beverageDecorated.cost().add(mochaCost());
    }

    private String mochaDescription() {
        return "mocha";
    }

    private BigDecimal mochaCost() {
        return new BigDecimal("5.0");
    }

}
