package cn.net.sybt.springboot.mapper;

import cn.net.sybt.springboot.bean.TeaMatchQuestion;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TeaMatchQuestionMapper {
    @Select("select * from tea_match_question where ques_id = #{quesId}")
    TeaMatchQuestion getTeaMatchQuestionById(Integer teaMatchQuestionId);

    @Select("select * from tea_match_question")
    List<TeaMatchQuestion> getAllTeaMatchQuestion();

    @Delete("delete from tea_match_question where ques_id = #{quesId}")
    Integer deleteTeaMatchQuestionById(Integer teaMatchQuestionId);

    @Update("update tea_match_question set  ques_content = #{quesContent}" +
            " , solution_text = #{solutionText},solution_link = #{solutionLink} " +
            " where ques_id = #{quesId}")
    Integer updateTeaMatchQuestion(TeaMatchQuestion teaEssay);

    @Options(useGeneratedKeys = true,keyProperty = "quesId")
    @Insert("insert into tea_match_question(ques_id, ques_content, solution_text, solution_link" +
            ") " +
            "values( #{quesId}, #{quesContent},  #{solutionText}, #{solutionLink} " +
            ")")
    Integer addTeaMatchQuestion(TeaMatchQuestion teaEssay);

}
