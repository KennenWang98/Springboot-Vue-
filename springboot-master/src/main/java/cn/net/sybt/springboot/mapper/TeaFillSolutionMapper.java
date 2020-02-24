package cn.net.sybt.springboot.mapper;


import cn.net.sybt.springboot.bean.TeaFillSolution;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface TeaFillSolutionMapper {
    @Select("select * from tea_fill_solution where ques_id = #{quesId}")
    List<TeaFillSolution> getTeaFillSolutionById(Integer teaFillSolutionId);

    @Select("select * from tea_fill_solution")
    List<TeaFillSolution> getAllTeaFillSolution();

    @Delete("delete from tea_fill_solution where ques_id = #{quesId}")
    Integer deleteTeaFillSolutionById(Integer teaFillSolutionId);

    @Update("update tea_fill_solution set solution_no = #{solutionNo}" +
            " , solution_content = #{solutionContent}" +
            " where ques_id = #{quesId}")
    Integer updateTeaFillSolution(TeaFillSolution teaEssay);

    @Insert("insert into tea_fill_solution(ques_id, solution_no, solution_content) " +
            "values( #{quesId}, #{solutionNo}, #{solutionContent})")
    Integer addTeaFillSolution(TeaFillSolution teaEssay);
}
