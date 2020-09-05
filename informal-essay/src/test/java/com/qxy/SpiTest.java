package com.qxy;

import com.qxy.spi.service.SayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ServiceLoader;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpiTest {

    static ServiceLoader<SayService> services = ServiceLoader.load(SayService.class);

    @Test
    public void test1() {
        for (SayService sayService : services) {
            sayService.say("Hello");
        }
    }

}
