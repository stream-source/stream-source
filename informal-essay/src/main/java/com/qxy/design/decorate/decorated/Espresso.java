package com.qxy.design.decorate.decorated;

import com.qxy.design.decorate.BeverageDecorated;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author wx
 * @date 2020/11/25 1:33 下午
 */
public class Espresso extends BeverageDecorated {

    public Espresso() {
        super.description = "Espresso";
    }

    @Override
    public BigDecimal cost() {
        return new BigDecimal("25.0");
    }
}
