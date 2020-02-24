package cn.net.sybt.springboot.mapper;

import cn.net.sybt.springboot.bean.School;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SchoolMapper extends BaseMapper<School> {
    @Select("select * from school where school_id = #{schoolId}")
    School getSchoolById(Integer schoolId); //根据id查询学校

    @Select("select * from school where school_name = #{schoolName}")
    School getSchoolByName(String schoolName); //根据校名查询学校

    @Select("select * from school")
    List<School> getAllSchools(); //查询出所有学校

    @Insert("insert into school(school_name, principal, school_icon, school_text)" +
            "values(#{schoolName}, #{principal}, #{schoolIcon}, #{schoolText})")
    Integer addSchool(School school); //添加学校

    @Delete("delete from school where school_id = #{schoolId}")
    Integer deleteSchoolById(Integer schoolId); //根据id删除学校

    @Update("update school set school_name = #{schoolName}, principal = #{principal}, " +
            "school_icon = #{schoolIcon}, school_text = #{schoolText} " +
            "where school_id = #{schoolId}")
    Integer updateSchool(School school); //更新学校信息
}
