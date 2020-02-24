package cn.net.sybt.springboot.mapper;

import cn.net.sybt.springboot.bean.Clazz;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ClazzMapper {
    @Select("select * from clazz where class_id = #{classId}")
    Clazz getClassById(Integer classId); //根据id查询班级信息

    @Select("select * from clazz where class_qr_code = #{classQrCode}")
    Clazz getClassByQRCode(String classQrCode); //根据二维码的uuid查询班级信息

    @Select("select stu_id from student_class where class_id = #{classId}")
    List<Integer> getAllStudentsOfClass(Integer classId); //查询当前班级的所有学生

    @Select("select count(*) from student_class where class_id = #{classId}")
    Integer getStudentsNumber(Integer classId); //查询当前班级的学生数量

    @Select("select * from clazz")
    List<Clazz> getAllClass();

    @Select("select count(*) from clazz")
    Integer getAllNumber();

    @Insert("insert into clazz(class_name, class_text, school_id, class_qr_code, class_icon, code, tea_id)" +
            "values(#{className}, #{classText}, #{schoolId}, #{classQrCode}, #{classIcon}, #{code}, #{teaId})")
    Integer addClass(Clazz clazz); //新建班级

    @Delete("delete from clazz where class_id = #{classId}")
    Integer deleteClassById(Integer classId); //根据班级id删除班级

    @Delete("delete from clazz where code = #{code}")
    Integer deleteClassByCode(String code); //根据许可码删除班级

    @Update("update clazz set class_name = #{className}, class_text = #{classText}, " +
            "school_id = #{schoolId}, class_qr_code = #{classQrCode}, class_icon = #{classIcon}, " +
            "code = #{code}, tea_id = #{teaId} " +
            "where class_id = #{classId}")
    Integer updateClass(Clazz clazz); //根据id更新班级信息

    @Select("select * from clazz where school_id = #{schoolId}")
    List<Clazz> getAllClassOfSchool(Integer schoolId); //查出某个学校里的所有班级

    //查询老师在一个学校里教的所有班级
    @Select("select * from clazz where school_id = #{schoolId} and tea_id = #{teaId}")
    List<Clazz> getAllClassOfTeacherOfSchool(Integer schoolId, Integer teaId);

    @Select("select count(*) from clazz where school_id = #{schoolId}")
    Integer getClassNumberOfSchool(Integer schoolId); //查询某个学校的班级数量

    @Select("select * from clazz where tea_id = #{teaId}")
    List<Clazz> getAllClassOfTeacher(Integer teaId); //查出某个老师教的所有班级

    @Select("select code from clazz where class_id = #{classId}")
    String getCode(Integer classId); //查出这个班级所使用的许可码

    @Delete("delete from student_class where class_id = #{classId}")
    Integer deleteAllStudentsOfClass(Integer classId); //删除某一班级中的所有学生
}
