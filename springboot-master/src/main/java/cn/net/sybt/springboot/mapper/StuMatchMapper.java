package cn.net.sybt.springboot.mapper;

import cn.net.sybt.springboot.bean.StuMatch;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface StuMatchMapper {
    @Select("select * from stu_match where ac_id = #{acId}")
    StuMatch getStuMatchById(Integer acId);

    @Select("select * from stu_match")
    List<StuMatch> getAllStuMatchs();


    @Insert("insert into stu_match(ac_id, left_value, left_link, right_value, right_link, is_true) " +
            "values(#{acId}, #{leftValue}, #{leftLink}, #{rightValue}, #{rightLink}, #{isTrue}) ")
    Integer addStuMatch(StuMatch stuMatch);

    @Delete("delete from stu_match where ac_id = #{acId}")
    Integer deleteStuMatchById(Integer acId);


    @Update("update stu_match set ac_id = #{acId}, left_value = #{leftValue}, left_link = #{leftLink}" +
            ",right_value = #{rightValue}, right_link = #{rightLink}, is_true = #{isTrue} " +
            "where ac_id = #{acId}")
    Integer updateStuMatch(StuMatch stuMatch);
}
