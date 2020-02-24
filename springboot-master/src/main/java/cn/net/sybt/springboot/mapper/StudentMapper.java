package cn.net.sybt.springboot.mapper;

import cn.net.sybt.springboot.bean.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper {
    @Select("select * from student where stu_id = #{stuId}")
    Student getStudentById(Integer stuId); //根据id查询学生

    @Select("select * from student where phone_number = #{phoneNumber}")
    Student getStudentByPhone(String phoneNumber); //根据手机号查询学生

    @Select("select * from student where stu_name like '%${stuName}%' ")
    List<Student> fuzzyQuery(String stuName); //模糊查询

    @Select("select * from student")
    List<Student> getAllStudents(); //查询出所有学生

    @Select("select class_id from student_class where stu_id = #{stuId}")
    List<Integer> getAllClassIdOfStudent(Integer stuId); //查询这个学生加入的所有班级的id

    @Delete("delete from student where stu_id = #{stuId}")
    Integer deleteStudentById(Integer stuId); //根据id删除学生

    @Options(useGeneratedKeys = true, keyProperty = "stuId")
    @Insert("insert into student(stu_name, stu_password, phone_number, stu_icon, pinyin, gender) " +
            "values(#{stuName}, #{stuPassword}, #{phoneNumber}, #{stuIcon}, #{pinyin}, #{gender})")
    Integer addStudent(Student student); //添加学生

    @Insert(("insert into student_class(stu_id, class_id) values(#{stuId}, #{classId})"))
    Integer addStudentToClass(Integer stuId, Integer classId); //把学生添加到某一班级中

    @Delete("delete from student_class where stu_id = #{stuId} and class_id = #{classId}")
    Integer deleteStudentFromClass(Integer stuId, Integer classId); //把学生从某一班级中删除

    @Update("update student set stu_name = #{stuName}, stu_password = #{stuPassword}," +
            "phone_number = #{phoneNumber}, stu_icon = #{stuIcon}, pinyin = #{pinyin}, gender = #{gender} " +
            "where stu_id = #{stuId}")
    Integer updateStudent(Student student); //更新学生个人信息
}
