package com.qxy.common.Do;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.Date;

/**
 * @Author:qxy
 * @Date:2020/4/7 9:41
 */
public class StudentDo implements InitializingBean, BeanPostProcessor {
    private ClassDo classDo;
    private Integer studentId;

    private String studentName;

    private Integer studentAge;

    private Integer classId;

    private String mobile;

    private String address;

    private Date createTime;

    private Date operateTime;

    public ClassDo getClassDo() {
        return classDo;
    }

    public void setClassDo(ClassDo classDo) {
        this.classDo = classDo;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(Integer studentAge) {
        this.studentAge = studentAge;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.setClassId(11111);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        this.setAddress("beanPostProcessor");
        return bean;
    }
}
