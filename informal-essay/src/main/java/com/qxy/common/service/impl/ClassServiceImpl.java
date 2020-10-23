package com.qxy.common.service.impl;

import com.qxy.common.Do.ClassDo;
import com.qxy.common.mapper.ClassMapper;
import com.qxy.common.service.ClassService;
import com.qxy.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author:wx
 * @Date:2020/5/8 16:27
 */
@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassMapper classMapper;

    @Override
    @Transactional(propagation = Propagation.NESTED, rollbackFor = Exception.class)
    public void insertClass(ClassDo classDo) {
        classMapper.insertClass(classDo);
        System.out.println("----------------------->Class插入成功!");
    }

    @Override
    @Transactional(propagation = Propagation.NESTED, rollbackFor = Exception.class)
    public void insertClassByException(ClassDo classDo) throws CustomException {
        classMapper.insertClass(classDo);
        throw new RuntimeException();
    }

    @Override
    public ClassDo getClassById(Integer id) {
        return classMapper.selectClassDoById(id);
    }
}
