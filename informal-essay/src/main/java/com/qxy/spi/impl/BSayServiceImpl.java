package com.qxy.spi.impl;

import com.qxy.spi.service.SayService;
import org.springframework.stereotype.Service;

@Service
public class BSayServiceImpl implements SayService {
    @Override
    public void say(String word) {
        System.out.println(word + " B say: I am a girl");
    }
}
