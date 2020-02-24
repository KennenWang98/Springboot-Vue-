package cn.net.sybt.springboot.mapper;

import cn.net.sybt.springboot.bean.TeaSortSolution;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TeaSortSolutionMapper {
    @Select("select * from tea_sort_solution where ques_id = #{quesId}")
    List<TeaSortSolution> getTeaSortSolutionById(Integer teaSortSolutionId);

    @Select("select * from tea_sort_solution")
    List<TeaSortSolution> getAllTeaSortSolution();

    @Delete("delete from tea_sort_solution where ques_id = #{quesId}")
    Integer deleteTeaSortSolutionById(Integer teaSortSolutionId);

    @Update("update tea_sort_solution set ques_content = #{quesContent}" +
            " , sort_no = #{sortNo}" +
            " where ques_id = #{quesId}")
    Integer updateTeaSortSolution(TeaSortSolution teaEssay);

    @Insert("insert into tea_sort_solution(ques_id, ques_content, sort_no) " +
            "values( #{quesId}, #{quesContent}, #{sortNo})")
    Integer addTeaSortSolution(TeaSortSolution teaEssay);
}
