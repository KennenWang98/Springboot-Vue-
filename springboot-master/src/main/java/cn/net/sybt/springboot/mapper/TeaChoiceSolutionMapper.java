package cn.net.sybt.springboot.mapper;


import cn.net.sybt.springboot.bean.TeaChoiceSolution;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TeaChoiceSolutionMapper {
    @Select("select * from tea_choice_solution where ques_id = #{quesId}")
    List<TeaChoiceSolution> getTeaChoiceSolutionById(Integer teaChoiceSolutionId);

    @Select("select * from tea_choice_solution")
    List<TeaChoiceSolution> getAllTeaChoiceSolution();

    @Delete("delete from tea_choice_solution where ques_id = #{quesId}")
    Integer deleteTeaChoiceSolutionById(Integer teaChoiceSolutionId);

    @Update("update tea_choice_solution set  is_true = #{isTrue}" +
            " , choice_content = #{choiceContent} ,choice_link = #{choiceLink} " +
            " where ques_id = #{quesId}")
    Integer updateTeaChoiceSolution(TeaChoiceSolution teaChoiceSolution);

    @Insert("insert into tea_choice_solution(ques_id, is_true, choice_content,choice_link) " +
            "values( #{quesId}, #{isTrue}, #{choiceContent},#{choiceLink})")
    Integer addTeaChoiceSolution(TeaChoiceSolution teaChoiceSolution);
}
