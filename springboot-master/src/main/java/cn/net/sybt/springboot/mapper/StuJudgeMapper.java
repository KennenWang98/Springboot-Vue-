package cn.net.sybt.springboot.mapper;

import cn.net.sybt.springboot.bean.StuJudge;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface StuJudgeMapper {
    @Select("select * from stu_judge where ac_id = #{acId}")
    StuJudge getStuJudgeById(Integer acId);

    @Select("select * from stu_judge")
    List<StuJudge> getAllStuJudges();


    @Insert("insert into stu_judge(ac_id, is_true, stu_answer) " +
            "values(#{acId}, #{isTrue}, #{stuAnswer})")
    Integer addStuJudge(StuJudge stuJudge);

    @Delete("delete from stu_judge where ac_id = #{acId}")
    Integer deleteStuJudgeById(Integer acId);


    @Update("update stu_judge set ac_id = #{acId}, is_true = #{isTrue}, stu_answer = #{stuAnswer} " +
            "where ac_id = #{acId}")
    Integer updateStuJudge(StuJudge stuJudge);
}
