package cn.net.sybt.springboot.mapper;

import cn.net.sybt.springboot.bean.PublisherQuestions;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

public interface PublisherQuestionsMapper extends BaseMapper<PublisherQuestions> {
    @Select("select * from publisher_questions where pub_ques_id = #{pubQuesId}")
    ArrayList<PublisherQuestions> getById(Integer pubQuesId);

    @Select("select * from publisher_questions where book_id = #{bookId}")
    ArrayList<PublisherQuestions> getByBookId(Integer bookId);

    @Select("select * from publisher_questions " +
            "where book_id = #{bookId} and pub_ques_type = #{pubQuesType}")
    ArrayList<PublisherQuestions> getByBookIdAndQuesType(Integer bookId, String pubQuesType);

    @Insert("insert into publisher_questions(book_id, pub_ques_type, pub_ques_specific_id) " +
            "values(#{bookId}, #{pubQuesType}, #{pubQuesSpecificId})")
    Integer addPublisherQuestions(PublisherQuestions publisherQuestions);

    @Update("update publisher_questions set book_id=#{newPublisherQuestions.bookId}, " +
            "pub_ques_type=#{newPublisherQuestions.pubQuesType}, " +
            "pub_ques_specific_id=#{newPublisherQuestions.pubQuesSpecificId} " +
            "where book_id=#{oldPublisherQuestions.bookId} and " +
            "pub_ques_type=#{oldPublisherQuestions.pubQuesType} and " +
            "pub_ques_specific_id=#{oldPublisherQuestions.pubQuesSpecificId}")
    Integer substPublisherQuestions(
            PublisherQuestions oldPublisherQuestions,
            PublisherQuestions newPublisherQuestions);

    @Delete("delete from publisher_questions where book_id=#{bookId}")
    Integer deletePublisherQuestionsByBookId(Integer bookId);

    @Delete("delete from publisher_questions where pub_ques_id=#{id}")
    Integer deletePublisherQuestionsById(Integer id);
}
