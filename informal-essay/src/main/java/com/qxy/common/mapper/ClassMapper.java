package com.qxy.common.mapper;

import com.qxy.common.Do.ClassDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Primary;

/**
 * @Author:wx
 * @Date:2020/5/8 16:28
 */
@Mapper
public interface ClassMapper {

    void insertClass(ClassDo classDo);

    void updateClass(ClassDo classDo);

    ClassDo selectClassDoById(@Param("classId") Integer classId);
}
