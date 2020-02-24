package cn.net.sybt.springboot.mapper;

import cn.net.sybt.springboot.bean.TeaEssay;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TeaEssayMapper {
    @Select("select * from tea_essay where ques_id = #{quesId}")
    TeaEssay getTeaEssayById(Integer teaEssayId);

    @Select("select * from tea_essay")
    List<TeaEssay> getAllTeaEssay();

    @Delete("delete from tea_essay where ques_id = #{quesId}")
    Integer deleteTeaEssayById(Integer teaEssayId);

    @Update("update tea_essay set ques_content = #{quesContent}" +
            " , solution_link = #{solutionLink} " +
            " where ques_id = #{quesId}")
    Integer updateTeaEssay(TeaEssay teaEssay);

    @Options(useGeneratedKeys = true,keyProperty = "quesId")
    @Insert("insert into tea_essay(ques_id, ques_content, solution_link) " +
            "values( #{quesId}, #{quesContent}, #{solutionLink})")
    Integer addTeaEssay(TeaEssay teaEssay);
}
