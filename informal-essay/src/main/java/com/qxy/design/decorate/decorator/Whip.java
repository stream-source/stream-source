package com.qxy.design.decorate.decorator;

import com.qxy.design.decorate.BeverageDecorated;
import com.qxy.design.decorate.CondimentDecorator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author wx
 * @date 2020/11/25 2:05 下午
 */
public class Whip extends CondimentDecorator {

    public Whip(BeverageDecorated beverageDecorated) {
        super(beverageDecorated);
    }

    @Override
    public String getDescription() {
        return beverageDecorated.getDescription() +"," + whipDescription();
    }

    @Override
    public BigDecimal cost() {
        return beverageDecorated.cost().add(whipCost());
    }

    private String whipDescription() {
        return "whip";
    }

    private BigDecimal whipCost() {
        return new BigDecimal("6.0");
    }


}
