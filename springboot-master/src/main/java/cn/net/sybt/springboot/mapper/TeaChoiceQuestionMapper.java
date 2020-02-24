package cn.net.sybt.springboot.mapper;


import cn.net.sybt.springboot.bean.TeaChoiceQuestion;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TeaChoiceQuestionMapper {
    @Select("select * from tea_choice_question where ques_id = #{quesId}")
    TeaChoiceQuestion getTeaChoiceQuestionById(Integer teaChoiceQuestionId);

    @Select("select * from tea_choice_question")
    List<TeaChoiceQuestion> getAllTeaChoiceQuestion();

    @Delete("delete from tea_choice_question where ques_id = #{quesId}")
    Integer deleteTeaChoiceQuestionById(Integer teaChoiceQuestionId);

    @Update("update tea_choice_question set ques_content = #{quesContent}" +
            ",solution_text = #{solutionText} , solution_link = #{solutionLink}" +
            " where ques_id = #{quesId}")
    Integer updateTeaChoiceQuestion(TeaChoiceQuestion teaChoiceQuestion);

    @Options(useGeneratedKeys = true,keyProperty = "quesId")
    @Insert("insert into tea_choice_question(ques_content, solution_text,solution_link) " +
            "values( #{quesContent},#{solutionText},#{solutionLink})")
    Integer addTeaChoiceQuestion(TeaChoiceQuestion teaChoiceQuestion);
}
