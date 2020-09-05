package com.qxy.spring.test;

/**
 * @Author:wx
 * @Date:2020/5/28 21:24
 */
public class TestBean {

    private String testStr = "testStr";

    public String getTestStr() {
        return testStr;
    }

    public void setTestStr(String testStr) {
        this.testStr = testStr;
    }

    public void test() {
        System.out.println("test...");
    }
}
