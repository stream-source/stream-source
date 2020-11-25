package com.qxy.design.decorate.decorator;

import com.qxy.design.decorate.BeverageDecorated;
import com.qxy.design.decorate.CondimentDecorator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author wx
 * @date 2020/11/25 2:02 下午
 */
public class Soy extends CondimentDecorator {

    public Soy(BeverageDecorated beverageDecorated) {
        super(beverageDecorated);
    }

    @Override
    public String getDescription() {
        return beverageDecorated.getDescription() +"," + soyDescription();
    }

    @Override
    public BigDecimal cost() {
        return beverageDecorated.cost().add(soyCost());
    }

    private String soyDescription() {
        return "soy";
    }

    private BigDecimal soyCost() {
        return new BigDecimal("3.0");
    }
}
