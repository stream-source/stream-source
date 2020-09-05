package com.qxy.config;

import com.qxy.common.Do.ClassDo;
import com.qxy.utils.SpringContextUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class RunnerLoadOne implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        ClassDo classDo = SpringContextUtil.getBean(ClassDo.class);
        classDo.setClassName("Java");
        System.out.println("------------容器初始化bean之后,加载资源结束-----------");
    }
}
