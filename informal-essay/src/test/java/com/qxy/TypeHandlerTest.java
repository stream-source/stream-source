package com.qxy;

import com.qxy.common.Do.ClassDo;
import com.qxy.common.mapper.ClassMapper;
import com.qxy.common.service.ClassService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wx
 * @date 2020/10/21 4:39 下午
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class TypeHandlerTest {

    @Autowired
    private ClassService classService;

    @Test
    public void testFind() {

        ClassDo classDo = classService.getClassById(1);
        System.out.println(classDo.getClassId());
    }

    @Test
    public void insert() {
        ClassDo classDo = new ClassDo();
        classDo.setClassId(5);
        classService.insertClass(classDo);
    }


}
