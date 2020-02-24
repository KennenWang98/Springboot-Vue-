package cn.net.sybt.springboot.mapper;

import cn.net.sybt.springboot.bean.StuEssay;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface StuEssayMapper {
    @Select("select * from stu_essay where ac_id = #{acId}")
    StuEssay getStuEssayById(Integer acId);

    @Select("select * from stu_essay")
    List<StuEssay> getAllStuEssays();


    @Insert("insert into stu_essay(ac_id, answer_essay) " +
            "values(#{acId}, #{answerEssay})")
    Integer addStuEssay(StuEssay stuEssay);

    @Delete("delete from stu_essay where ac_id = #{acId}")
    Integer deleteStuEssayById(Integer acId);


    @Update("update stu_essay set ac_id = #{acId}, answer_essay = #{answerEssay} " +
            "where ac_id = #{acId}")
    Integer updateStuEssay(StuEssay stuEssay);
}
