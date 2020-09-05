package com.qxy.common.Do;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Author:wx
 * @Date:2020/5/8 16:25
 */
@Configuration
public class ClassDo {
    private int classId;

    private String className;

    private String classNo;

    private int studentCount;

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }
}
