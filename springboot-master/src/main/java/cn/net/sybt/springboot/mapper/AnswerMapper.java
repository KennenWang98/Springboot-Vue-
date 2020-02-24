package cn.net.sybt.springboot.mapper;


import cn.net.sybt.springboot.bean.Answer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface AnswerMapper {
    @Select("select * from answer where answer_id = #{answerId}")
    Answer getAnswerById(Integer answerId);

    @Select("select * from answer")
    List<Answer> getAllAnswers();

    @Select("select * from answer where ch_id = #{chId} and stu_Id = #{stuId}")
    Answer getStuAnswerByChIdAndStuId(Integer chId, Integer stuId);

    @Select("select * from answer where ch_id = #{chId}")
    List<Answer> getstuAnswerByChId(Integer chId);

    @Select("select * from answer where stu_id = #{stuId}")
    List<Answer> getstuAnswerBystuId(Integer stuId);

    @Select("select count(*) from answer where ch_id = #{chId}")
    Double getStuNumber(Integer chId);

    @Options(useGeneratedKeys = true, keyProperty = "answerId") //封装主键
    @Insert("insert into answer( stu_id, ch_id, percentage, upload_time) " +
            "values( #{stuId}, #{chId},#{percentage}, #{uploadTime})")
    Integer addAnswer(Answer answer);

    @Delete("delete from answer where answer_id = #{answerId}")
    Integer deleteAnswerById(Integer answerId);


    @Update("update answer set stu_id = #{stuId}, " +
            "ch_id = #{chId}, percentage = #{percentage}, upload_time = #{uploadTime} " +
            "where answer_id = #{answerId}")
    Integer updateAnswer(Answer answer);
}
