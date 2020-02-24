package cn.net.sybt.springboot.mapper;

import cn.net.sybt.springboot.bean.StuChoice;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface StuChoiceMapper {
    @Select("select * from stu_choice where ac_id = #{acId}")
    List<StuChoice> getStuChoiceById(Integer acId);

    @Select("select * from stu_choice")
    List<StuChoice> getAllStuChoices();


    @Insert("insert into stu_choice(ac_id, is_true, stu_answer) " +
            "values(#{acId}, #{isTrue}, #{stuAnswer})")
    Integer addStuChoice(StuChoice stuChoice);

    @Delete("delete from stu_choice where ac_id = #{acId}")
    Integer deleteStuChoiceById(Integer acId);


    @Update("update stu_choice set ac_id = #{acId}, is_true = #{isTrue}, stu_answer = #{stuAnswer} " +
            "where ac_id = #{acId} and stu_answer = #{stuAnswer}")
    Integer updateStuChoice(StuChoice stuChoice);
}
