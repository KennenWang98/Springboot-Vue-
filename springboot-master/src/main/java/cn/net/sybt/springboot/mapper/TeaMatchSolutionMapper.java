package cn.net.sybt.springboot.mapper;


import cn.net.sybt.springboot.bean.TeaMatchSolution;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface TeaMatchSolutionMapper {
    @Select("select * from tea_match_solution where ques_id = #{quesId}")
    List<TeaMatchSolution> getTeaMatchSolutionById(Integer teaMatchSolutionId);

    @Select("select * from tea_match_solution")
    List<TeaMatchSolution> getAllTeaMatchSolution();

    @Delete("delete from tea_match_solution where ques_id = #{quesId}")
    Integer deleteTeaMatchSolutionById(Integer teaMatchSolutionId);

    @Update("update tea_match_solution set  left_value = #{leftValue}" +
            " , left_link = #{leftLink}, right_value = #{rightValue},  right_link = #{rightLink}" +
            " where ques_id = #{quesId}")
    Integer updateTeaMatchSolution(TeaMatchSolution teaEssay);

    
    @Insert("insert into tea_match_solution(ques_id, left_value, left_link, right_value, right_link) " +
            "values( #{quesId}, #{leftValue}, #{leftLink}, #{rightValue}, #{rightLink})")
    Integer addTeaMatchSolution(TeaMatchSolution teaEssay);
}
