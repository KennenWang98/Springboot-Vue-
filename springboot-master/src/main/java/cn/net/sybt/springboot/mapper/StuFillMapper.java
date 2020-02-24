package cn.net.sybt.springboot.mapper;

import cn.net.sybt.springboot.bean.StuFill;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface StuFillMapper {
    @Select("select * from stu_fill where ac_id = #{acId}")
    List<StuFill> getStuFillById(Integer acId);

    @Select("select * from stu_fill")
    List<StuFill> getAllStuFills();


    @Insert("insert into stu_fill(ac_id, stu_answer, answer_no, is_true) " +
            "values(#{acId}, #{stuAnswer}, #{answerNo}, #{isTrue})")
    Integer addStuFill(StuFill stuFill);

    @Delete("delete from stu_fill where ac_id = #{acId}")
    Integer deleteStuFillById(Integer acId);


    @Update("update stu_fill set ac_id = #{acId}, stu_answer = #{stuAnswer}, answer_no = #{answerNo}" +
            ",is_true = #{isTrue} " +
            "where ac_id = #{acId} and stu_answer = #{stuAnswer} ")
    Integer updateStuFill(StuFill stuFill);
}
