package com.qxy.design.decorate.decorated;

import com.qxy.design.decorate.BeverageDecorated;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author wx
 * @date 2020/11/25 1:25 下午
 */
public class DarkRoast extends BeverageDecorated {

    public DarkRoast() {
        super.description = "DarkRoast";
    }


    @Override
    public BigDecimal cost() {
        return new BigDecimal("30.0");
    }
}
