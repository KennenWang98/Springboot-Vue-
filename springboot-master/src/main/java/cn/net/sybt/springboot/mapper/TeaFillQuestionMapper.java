package cn.net.sybt.springboot.mapper;

import cn.net.sybt.springboot.bean.TeaFillQuestion;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TeaFillQuestionMapper {
    @Select("select * from tea_fill_question where ques_id = #{quesId}")
    TeaFillQuestion getTeaFillQuestionById(Integer teaFillQuestionId);

    @Select("select * from tea_fill_question")
    List<TeaFillQuestion> getAllTeaFillQuestion();

    @Delete("delete from tea_fill_question where ques_id = #{quesId}")
    Integer deleteTeaFillQuestionById(Integer teaFillQuestionId);

    @Update("update tea_fill_question set ques_content = #{quesContent}" +
            " ,  solution_text = #{solutionText},solution_link = #{solutionLink}" +
            " where ques_id = #{quesId}")
    Integer updateTeaFillQuestion(TeaFillQuestion teaEssay);

    @Options(useGeneratedKeys = true,keyProperty = "quesId")
    @Insert("insert into tea_fill_question(ques_id, ques_content,solution_text,solution_link) " +
            "values( #{quesId}, #{quesContent}, #{solutionText},#{solutionLink})")
    Integer addTeaFillQuestion(TeaFillQuestion teaEssay);
}
