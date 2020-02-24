package cn.net.sybt.springboot.mapper;

import cn.net.sybt.springboot.bean.PubMatchSolution;
import cn.net.sybt.springboot.interfaces.SolutionMapperInterface;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PubMatchSolutionMapper extends BaseMapper<PubMatchSolution>, SolutionMapperInterface {
    @Override
    @Select("select * from pub_match_solution where ques_id = #{quesId}")
    List<PubMatchSolution> selectByQuestionId(Integer quesId);

    @Override
    @Delete("delete from pub_match_solution where ques_id = #{quesId}")
    Integer deleteByQuestionId(Integer quesId);
}
