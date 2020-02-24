package cn.net.sybt.springboot.mapper;

import cn.net.sybt.springboot.bean.Subject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface SubjectMapper extends BaseMapper<Subject> {
    @Select("select * from subject")
    List<Subject> getAll();

    @Select("select * from subject where subject_id = #{subjectId}")
    List<Subject> getSubjectById(Integer subjectId);

    @Insert("insert into subject(subject_name) values(#{subjectName})")
    Integer addSubject(Subject subject);

    @Update("update subject set subject_name = #{subjectName} " +
            "where subject_id = #{subjectId}")
    Integer updateSubject(Subject subject);

    @Delete("delete from subject where subject_id = #{subjectId}")
    Integer deleteSubject(Integer subjectId);
}
