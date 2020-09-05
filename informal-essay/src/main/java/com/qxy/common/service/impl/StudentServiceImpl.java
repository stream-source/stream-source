package com.qxy.common.service.impl;

import com.qxy.common.Do.StudentDo;
import com.qxy.common.mapper.StudentMapper;
import com.qxy.common.service.ClassService;
import com.qxy.common.service.StudentService;
import com.qxy.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author:qxy
 * @Date:2020/4/7 9:46
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ClassService classService;

    @Override
//    @Transactional(propagation = Propagation.NESTED, rollbackFor = Exception.class)
    public void insertStudent(StudentDo studentDo) {
        studentMapper.insertStudent(studentDo);
        System.out.println("----------------------->Student插入成功!");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insertClassByException(StudentDo studentDo) throws CustomException {
        studentMapper.insertStudent(studentDo);
        throw new CustomException();
    }

    @Override
    public void updateStudent(StudentDo studentDo) throws CustomException {
        studentMapper.insertStudent(studentDo);
        throw new CustomException();
    }
}
