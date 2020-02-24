package cn.net.sybt.springboot.mapper;

import cn.net.sybt.springboot.bean.TeaSortQuestion;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TeaSortQuestionMapper {
    @Select("select * from tea_sort_question where ques_id = #{quesId}")
    TeaSortQuestion getTeaSortQuestionById(Integer teaSortQuestionId);

    @Select("select * from tea_sort_question")
    List<TeaSortQuestion> getAllTeaSortQuestion();

    @Delete("delete from tea_sort_question where ques_id = #{quesId}")
    Integer deleteTeaSortQuestionById(Integer teaSortQuestionId);

    @Update("update tea_sort_question set  ques_content = #{quesContent}" +
            " " +
            " where ques_id = #{quesId}")
    Integer updateTeaSortQuestion(TeaSortQuestion teaEssay);

    @Options(useGeneratedKeys = true, keyProperty = "quesId")
    @Insert("insert into tea_sort_question(ques_id, ques_content" +
            ") " +
            "values( #{quesId}, #{quesContent}  " +
            ")")
    Integer addTeaSortQuestion(TeaSortQuestion teaEssay);
}
