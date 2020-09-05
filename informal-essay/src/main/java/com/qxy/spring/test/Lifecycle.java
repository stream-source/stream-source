package com.qxy.spring.test;

import com.qxy.common.Do.StudentDo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author:wx
 * @Date:2020/4/9 10:57
 */
public class Lifecycle {
    public static void main(String[] args) {
        //加载+创建实例化+初始化过程
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beanConfig.xml");
        //获取bean
        StudentDo student = (StudentDo)applicationContext.getBean("student");
        System.out.println(student.getAddress());
        //initializingBean

//        TestBean testBean = ( TestBean) applicationContext.getBean("test");
//        testBean.test();

    }
}
