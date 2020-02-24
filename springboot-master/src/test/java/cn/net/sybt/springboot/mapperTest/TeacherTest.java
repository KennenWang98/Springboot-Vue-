package cn.net.sybt.springboot.mapperTest;

import cn.net.sybt.springboot.bean.Teacher;
import cn.net.sybt.springboot.mapper.TeacherMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class TeacherTest {
    @Autowired
    private TeacherMapper teacherMapper;

    @Test
    public void testGetTeacherById() { //根据id查询单个老师
        Teacher teacher = teacherMapper.getTeacherById(1);
        System.out.println(teacher);
    }

    @Test
    public void testGetAllTeachers() { //查询所有老师
        List<Teacher> teachers = teacherMapper.getAllTeachers();
        teachers.forEach(System.out::println);
    }

    @Test
    public void testDeleteTeacherById() { //根据id删除老师
        teacherMapper.deleteTeacherById(3);
    }

    @Test
    public void testAddTeacher() { //添加老师
        Teacher teacher = new Teacher();
        teacher.setTeaName("李老师");
        teacher.setTeaPassword("abcdefg");
        teacher.setPhoneNumber("18235789999");
        teacher.setTeaIcon("beauty");
        teacherMapper.addTeacher(teacher);
    }

    @Test
    public void testUpdateTeacher() { //更新老师信息
        Teacher teacher = new Teacher();
        teacher.setTeaName("王老师");
        teacher.setTeaPassword("不告诉你");
        teacher.setPhoneNumber("还是不告诉你");
        teacher.setTeaIcon("头像");
        teacher.setTeaId(4);
        teacherMapper.updateTeacher(teacher);
    }

}
