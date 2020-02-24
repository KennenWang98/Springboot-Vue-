package cn.net.sybt.springboot.mapper;

import cn.net.sybt.springboot.bean.TeaDocument;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TeaDocumentMapper {
    @Select("select * from tea_document where class_id = #{classId}")
    List<TeaDocument> getTeaDocumentByClassId(Integer classId); //根据班级id查询教师在班级里上传的资料

    @Select("select * from tea_document")
    List<TeaDocument> getAllTeaDocuments();

    @Delete("delete from tea_document where tea_docu_id = #{teaDocuId}")
    Integer deleteTeaDocumentById(Integer teaDocuId);

    @Insert("insert into tea_document(docu_path, docu_type, tea_id, docu_name, class_id) " +
            "values(#{docuPath}, #{docuType}, #{teaId}, #{docuName}, #{classId})")
    Integer addTeaDocument(TeaDocument teaDocument);

    @Update("update tea_document set docu_path = #{docuPath}, docu_type = #{docuType}, tea_id = #{teaId}, " +
            "docu_name = #{docuName}, class_id = #{classId} " +
            "where tea_docu_id = #{teaDocuId}")
    Integer updateTeaDocument(TeaDocument teaDocument);
}
