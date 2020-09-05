package com.qxy;

import com.qxy.common.Do.ClassDo;
import com.qxy.common.Do.StudentDo;
import com.qxy.common.mapper.StudentMapper;
import com.qxy.common.service.ClassService;
import com.qxy.common.service.StudentService;
import com.qxy.exception.CustomException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @Author:wx
 * @Date:2020/5/8 16:48
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PropagationTest {

    private final static StudentDo studentDo = new StudentDo();

    private final static ClassDo classDo = new ClassDo();
    static {
        studentDo.setClassId(2);
        studentDo.setStudentName("manual_InnerException");
        studentDo.setAddress("manual_InnerException");

        classDo.setClassName("manual_InnerException");
        classDo.setClassNo("manual_InnerException");
    }
    @Autowired
    private StudentService studentService;

    @Autowired
    private ClassService classService;


    @Test
    public void insertTest() {
        studentService.insertStudent(studentDo);
        classService.insertClass(classDo);
        throw new RuntimeException();

    }

    @Test
    public void insertExceptionTest() throws CustomException {
        studentService.insertStudent(studentDo);
        classService.insertClassByException(classDo);
    }

    /**
     * 无论内部方法是否被事务传播行为所修饰，外部方法存在异常，内部方法均被回滚。
     * 因为外部方法开启事务，内部方法以当前事务执行。
     */
    @Test
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insertOutExceptionTest() {
        studentService.insertStudent(studentDo);
        classService.insertClass(classDo);
        throw new RuntimeException();
    }

    @Autowired
    private StudentMapper studentMapper;

    @Test
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insertInnerExceptionThrowsTest() throws CustomException {
//        studentService.insertStudent(studentDo);
        studentMapper.insertStudent(studentDo);
        classService.insertClassByException(classDo);
    }

    /**
     * 内部方法发生异常情况，外部方法即使捕获处理该异常，依然数据会被回滚
     */
    @Test
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insertInnerExceptionTest() {
        studentService.insertStudent(studentDo);
        try {
            classService.insertClassByException(classDo);
        } catch (CustomException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertExceptionManualRollbackTest() {
        studentService.insertStudent(studentDo);
        try {
            classService.insertClassByException(classDo);
        } catch (CustomException e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }






}
