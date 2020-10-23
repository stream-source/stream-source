package com.qxy.common.service;

import com.qxy.common.Do.ClassDo;
import com.qxy.exception.CustomException;

/**
 * @Author:wx
 * @Date:2020/5/8 16:27
 */
public interface ClassService {
    void insertClass(ClassDo classDo);

    void insertClassByException(ClassDo classDo) throws CustomException;

    ClassDo getClassById(Integer id);
}
