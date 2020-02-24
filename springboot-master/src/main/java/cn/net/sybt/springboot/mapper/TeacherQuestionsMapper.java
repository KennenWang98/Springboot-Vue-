package cn.net.sybt.springboot.mapper;

import cn.net.sybt.springboot.bean.TeacherQuestions;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface TeacherQuestionsMapper {
    @Select("select * from teacher_questions where tea_ques_id = #{teaQuesId}")
    TeacherQuestions getTeacherQuestionsById(Integer teacherQuestionsId);

//    @Select("select * from teacher_questions LIMIT #{start},5")
//    List<TeacherQuestions> getAllTeacherQuestions(Integer start);

    @Select("select * from teacher_questions ")
    List<TeacherQuestions> getAllTeacherQuestions();

    @Delete("delete from teacher_questions where tea_ques_id = #{teaQuesId}")
    Integer deleteTeacherQuestionsById(Integer teacherQuestionsId);

    @Update("update teacher_questions set tea_ques_id = #{teaQuesId} , tea_id = #{teaId}" +
            " , class_id = #{classId}, tea_ques_type = #{teaQuesType} ,book_id = #{bookId}, tea_ques_specific_id = #{teaQuesSpecificId}" +
            " where tea_ques_id = #{teaQuesId}")
    Integer updateTeacherQuestions(TeacherQuestions teaEssay);

    @Options(useGeneratedKeys = true, keyProperty = "teaQuesId") //封装主键
    @Insert("insert into teacher_questions(tea_id, class_id,tea_ques_type,book_id,tea_ques_specific_id) " +
            "values(  #{teaId}, #{classId},#{teaQuesType},#{bookId},#{teaQuesSpecificId})")
    Integer addTeacherQuestions(TeacherQuestions teaEssay);

    @Delete("delete from teacher_questions where tea_ques_specific_id = #{teaQuesSpecificId} and tea_ques_type = #{teaQuesType}")
    Integer deleteTeacherQuestionsBySpecificId(Integer teaQuesSpecificId, String teaQuesType);
}
