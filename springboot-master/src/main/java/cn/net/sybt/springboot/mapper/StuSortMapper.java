package cn.net.sybt.springboot.mapper;

import cn.net.sybt.springboot.bean.StuSort;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface StuSortMapper {
    @Select("select * from stu_fill where ac_id = #{acId}")
    StuSort getStuSortById(Integer acId);

    @Select("select * from stu_fill")
    List<StuSort> getAllStuSorts();


    @Insert("insert into stu_fill(ac_id, stu_answer, answer_no, is_true) " +
            "values(#{acId}, #{stuAnswer}, #{answerNo}, #{isTrue})")
    Integer addStuSort(StuSort stuSort);

    @Delete("delete from stu_fill where ac_id = #{acId}")
    Integer deleteStuSortById(Integer acId);


    @Update("update stu_fill set ac_id = #{acId}, stu_answer = #{stuAnswer}, answer_no = #{answerNo}" +
            ",is_true = #{isTrue} " +
            "where ac_id = #{acId}")
    Integer updateStuSort(StuSort stuSort);
}
