package com.qxy.common.service;


import com.qxy.common.Do.StudentDo;
import com.qxy.exception.CustomException;

/**
 * @Author:qxy
 * @Date:2020/4/7 9:45
 */
public interface StudentService {

    void insertStudent(StudentDo studentDo);

    void insertClassByException(StudentDo studentDo) throws CustomException;

    void updateStudent(StudentDo studentDo) throws CustomException;
}
