package com.qxy.config;

import com.qxy.common.Do.ClassDo;
import com.qxy.utils.SpringContextUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class RunnerLoadTwo implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        ClassDo bean = SpringContextUtil.getBean(ClassDo.class);
        System.out.println("依赖预先加载的资源数据：" + bean.getClassName());
    }
}
