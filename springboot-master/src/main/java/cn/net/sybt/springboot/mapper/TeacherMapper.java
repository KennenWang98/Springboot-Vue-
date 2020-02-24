package cn.net.sybt.springboot.mapper;

import cn.net.sybt.springboot.bean.LicenseCode;
import cn.net.sybt.springboot.bean.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TeacherMapper extends BaseMapper<Teacher> {
    @Select("select * from teacher where tea_id = #{teaId}")
    Teacher getTeacherById(Integer teaId); //根据id查询老师

    @Select("select * from teacher where tea_name = #{teaName}")
    List<Teacher> getTeacherByName(String teaName); //根据姓名查询老师

    @Select(("select * from teacher where phone_number = #{phoneNumber}"))
    Teacher getTeacherByPhone(String phoneNumber); //根据手机号查询老师

    @Select("select * from teacher")
    List<Teacher> getAllTeachers(); //查询出所有的老师

    @Delete("delete from teacher where tea_id = #{teaId}")
    Integer deleteTeacherById(Integer teaId); //根据id删除老师

    @Insert("insert into teacher(tea_name, tea_password, phone_number, tea_icon, gender)" +
            "values(#{teaName}, #{teaPassword}, #{phoneNumber}, #{teaIcon}, #{gender})")
    Integer addTeacher(Teacher teacher); //添加老师

    @Update("update teacher set tea_name = #{teaName}, tea_password = #{teaPassword}, " +
            "phone_number = #{phoneNumber}, tea_icon = #{teaIcon}, gender = #{gender} " +
            "where tea_id = #{teaId}")
    Integer updateTeacher(Teacher teacher); //更新老师个人信息
}
