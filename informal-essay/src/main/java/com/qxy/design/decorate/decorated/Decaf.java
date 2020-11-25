package com.qxy.design.decorate.decorated;

import com.qxy.design.decorate.BeverageDecorated;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author wx
 * @date 2020/11/25 1:29 下午
 */
public class Decaf extends BeverageDecorated {

    public Decaf() {
        super.description = "Decaf";
    }

    @Override
    public BigDecimal cost() {
        return new BigDecimal("35.0");
    }
}
