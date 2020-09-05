package com.qxy.common.mapper;

import com.qxy.common.Do.StudentDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author:qxy
 * @Date:2020/4/7 9:46
 */
@Mapper
public interface StudentMapper {

    void insertStudent(StudentDo studentDo);

    void updateStudent(StudentDo studentDo);

    List<StudentDo> selectStudentList();
}
