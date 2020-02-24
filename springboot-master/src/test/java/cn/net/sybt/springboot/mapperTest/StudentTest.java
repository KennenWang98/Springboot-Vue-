package cn.net.sybt.springboot.mapperTest;

import cn.net.sybt.springboot.bean.Student;
import cn.net.sybt.springboot.mapper.StudentMapper;
import cn.net.sybt.springboot.utils.PinyinUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class StudentTest {
    @Autowired
    private StudentMapper studentMapper;

    @Test
    public void testGetAllStudents() { //查询所有学生
        List<Student> students = studentMapper.getAllStudents();
        students.forEach(System.out::println);
    }

    @Test
    public void testGetStudentById() { //根据id查询单个学生
        Student student = studentMapper.getStudentById(1);
        System.out.println(student);
    }

    @Test
    public void testDeleteStudentById() { //根据id删除单个学生
        studentMapper.deleteStudentById(2);
    }

    @Test
    public void testAddStudent() { //添加学生
        Student student = new Student();
        student.setStuName("C罗");
        student.setStuPassword("ronaldo");
        student.setPhoneNumber("123456");
        student.setStuIcon("sb");
        studentMapper.addStudent(student);
        System.out.println(student.getStuId());
    }

    @Test
    public void testFuzzyQuery() { //模糊查询
        String name = "谷";
        List<Student> students = studentMapper.fuzzyQuery(name);
        for (Student student : students) {
            System.out.println(student.toString());
        }
    }

    @Test
    public void testUpdateStudent() { //更新学生个人信息
        Student student = new Student();
        student.setStuName("李四");
        student.setStuId(2);
        studentMapper.updateStudent(student);
    }

    @Test
    public void testPinyin() {
        String str = "测试一下怎么用";
        String str1 = PinyinUtil.getPingYin(str);
        System.out.println(str1);
    }
}
