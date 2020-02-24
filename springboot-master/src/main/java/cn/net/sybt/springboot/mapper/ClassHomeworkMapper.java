package cn.net.sybt.springboot.mapper;

import cn.net.sybt.springboot.bean.ClassHomework;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ClassHomeworkMapper {
    @Select("select * from class_homework where ch_id = #{chId}")
    ClassHomework getClassHomeworkById(Integer chId);

    @Select("select * from class_homework")
    List<ClassHomework> getAllClassHomeworks();

    @Select("select * from class_homework where tea_id = #{teaId} and class_id = #{classId}")
    List<ClassHomework> getClassHomeworksByTeaIdAndClassId(Integer teaId, Integer classId);

    @Select("select * from class_homework where class_id = #{classId}")
    List<ClassHomework> getClassHomeworksByAndClassId(Integer classId);

    @Options(useGeneratedKeys = true, keyProperty = "chId") //封装主键
    @Insert("insert into class_homework(layout_time, tea_id, class_id, deadline, homework_name, status) " +
            "values(#{layoutTime}, #{teaId}, #{classId}, #{deadline}, #{homeworkName}, #{status})")
    Integer addClassHomework(ClassHomework classHomework);

    @Delete("delete from class_homework where ch_id = #{chId}")
    Integer deleteClassHomeworkById(Integer chId);


    @Update("update class_homework set layout_time = #{layoutTime}, tea_id = #{teaId}, " +
            "class_id = #{classId}, deadline = #{deadline}" +
            ", homework_name = #{homeworkName},  status = #{status}" +
            "where ch_id = #{chId}")
    Integer updateClassHomework(ClassHomework classHomework);
}
