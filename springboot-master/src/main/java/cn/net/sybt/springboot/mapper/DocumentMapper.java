package cn.net.sybt.springboot.mapper;

import cn.net.sybt.springboot.bean.Document;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DocumentMapper {
    @Select("select * from document where docu_id = #{docuId}")
    Document getDocumentById(Integer docuId); //根据id查询E-learning 资料

    @Select("select * from document")
    List<Document> getAllDocuments();

    @Insert("insert into document(docu_path, docu_type, book_id, docu_name)" +
            "values(#{docuPath}, #{docuType}, #{bookId}, #{docuName})")
    Integer addDocument(Document document);

    @Delete("delete from document where docu_id = #{docuId}")
    Integer deleteDocumentById(Integer docuId);

    @Update("update document set docu_path = #{docuPath}, docu_type = #{docuType}, " +
            "book_id = #{bookId}, docu_name = #{docuName} " +
            "where docu_id = #{docuId}")
    Integer updateDocument(Document document);
}
