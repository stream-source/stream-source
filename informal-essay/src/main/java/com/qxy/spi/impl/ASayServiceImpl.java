package com.qxy.spi.impl;

import com.qxy.spi.service.SayService;
import org.springframework.stereotype.Service;

@Service
public class ASayServiceImpl implements SayService {
    @Override
    public void say(String word) {
        System.out.println(word + " A say: I am a boy");
    }
}
