package cn.net.sybt.springboot.mapper;

import cn.net.sybt.springboot.bean.AnswerContent;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface AnswerContentMapper {
    @Select("select * from answer_content where ac_id = #{acId}")
    AnswerContent getAnswerContentById(Integer acId);

    @Select("select * from answer_content")
    List<AnswerContent> getAllAnswerContents();

    @Select("select * from answer_content where hc_id = #{hcId}")
    List<AnswerContent> getAllAnswerContentsByHcid(Integer hcId);

    @Select("select * from answer_content where answer_id = #{answerId}")
    List<AnswerContent> getAnswerContentsByAnswerId(Integer answerId);

    @Options(useGeneratedKeys = true, keyProperty = "acId") //封装主键
    @Insert("insert into answer_content(answer_id, hc_id, single_ques_id, ques_type, feedback, point) " +
            "values(#{answerId}, #{hcId}, #{singleQuesId}, #{quesType}, #{feedback}, #{point})")
    Integer addAnswerContent(AnswerContent answerContent);

    @Delete("delete from answer_content where ac_id = #{acId}")
    Integer deleteAnswerContentById(Integer acId);


    @Update("update answer_content set answer_id = #{answerId}, hc_id = #{hcId}, " +
            "single_ques_id = #{singleQuesId}, ques_type = #{quesType}" +
            ", feedback = #{feedback}, point = #{point} " +
            "where ac_id = #{acId}")
    Integer updateAnswerContent(AnswerContent answerContent);
}
