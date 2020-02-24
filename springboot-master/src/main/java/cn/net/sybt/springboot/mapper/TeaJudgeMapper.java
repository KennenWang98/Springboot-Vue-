package cn.net.sybt.springboot.mapper;

import cn.net.sybt.springboot.bean.TeaJudge;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TeaJudgeMapper {
    @Select("select * from tea_judge where ques_id = #{quesId}")
    TeaJudge getTeaJudgeById(Integer teaJudgeId);

    @Select("select * from tea_judge")
    List<TeaJudge> getAllTeaJudge();

    @Delete("delete from tea_judge where ques_id = #{quesId}")
    Integer deleteTeaJudgeById(Integer teaJudgeId);

    @Update("update tea_judge set ques_content = #{quesContent}" +
            " , ques_link = #{quesLink},is_true = #{isTrue},solution_text = #{solutionText}," +
            "solution_link = #{solutionLink}" +
            " where ques_id = #{quesId}")
    Integer updateTeaJudge(TeaJudge teaEssay);

    @Options(useGeneratedKeys = true,keyProperty = "quesId")
    @Insert("insert into tea_judge(ques_id, ques_content, ques_link, is_true, solution_text, solution_link" +
            ") " +
            "values( #{quesId}, #{quesContent}, #{quesLink}, #{isTrue}, #{solutionText}, #{solutionLink} " +
            ")")
    Integer addTeaJudge(TeaJudge teaEssay);
    
}
