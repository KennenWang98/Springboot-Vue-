package cn.net.sybt.springboot.mapper;

import cn.net.sybt.springboot.bean.PubSortSolution;
import cn.net.sybt.springboot.interfaces.SolutionMapperInterface;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PubSortSolutionMapper extends BaseMapper<PubSortSolution>, SolutionMapperInterface {
    @Override
    @Select("select * from pub_sort_solution where ques_id = #{quesId}")
    List<PubSortSolution> selectByQuestionId(Integer quesId);

    @Override
    @Delete("delete from pub_sort_solution where ques_id = #{quesId}")
    Integer deleteByQuestionId(Integer quesId);
}
