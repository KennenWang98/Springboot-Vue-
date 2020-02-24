package cn.net.sybt.springboot.mapper;

import cn.net.sybt.springboot.bean.HomeworkContent;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface HomeworkContentMapper {
    @Select("select * from homework_content where hc_id = #{hcId}")
    HomeworkContent getHomeworkContentById(Integer hcId);

    @Select("select * from homework_content")
    List<HomeworkContent> getAllHomeworkContents();

    @Select("select * from homework_content where ch_id = #{chId}")
    List<HomeworkContent> getHomeworkContentBychId(Integer chId);

    @Select("select * from homework_content where hc_ques_id = #{quesId} and hc_flag = 1")
    List<HomeworkContent> getHomeworkContentByQuesId(Integer quesId);

    @Options(useGeneratedKeys = true, keyProperty = "hcId") //封装主键
    @Insert("insert into homework_content(hc_ques_id, hc_flag, ch_id) " +
            "values(#{hcQuesId}, #{hcFlag}, #{chId})")
    Integer addHomeworkContent(HomeworkContent homeworkContent);

    @Delete("delete from homework_content where hc_id = #{hcId}")
    Integer deleteHomeworkContentById(Integer hcId);

    @Delete("delete from homework_content where ch_id = #{chId}")
    Integer deleteHomeworkContentBychId(Integer chId);

    @Delete("delete from homework_content where hc_ques_id = #{quesId} and hc_flag = 1")
    Integer deleteHomeworkContentByQuesId(Integer quesId);

    @Update("update homework_content set hc_ques_id = #{hcQuesId}, hc_flag = #{hcFlag}, ch_id = #{chId} " +
            "where hc_id = #{hcId}")
    Integer updateHomeworkContent(HomeworkContent homeworkContent);
}
