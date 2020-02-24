package cn.net.sybt.springboot.mapper;

import cn.net.sybt.springboot.bean.PubChoiceSolution;
import cn.net.sybt.springboot.interfaces.SolutionMapperInterface;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PubChoiceSolutionMapper extends BaseMapper<PubChoiceSolution>, SolutionMapperInterface {
    @Override
    @Select("select * from pub_choice_solution where ques_id = #{quesId}")
    List<PubChoiceSolution> selectByQuestionId(Integer quesId);

    @Override
    @Delete("delete from pub_choice_solution where ques_id = #{quesId}")
    Integer deleteByQuestionId(Integer quesId);
}
