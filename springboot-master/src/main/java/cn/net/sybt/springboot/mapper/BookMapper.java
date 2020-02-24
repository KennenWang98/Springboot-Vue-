package cn.net.sybt.springboot.mapper;

import cn.net.sybt.springboot.bean.Book;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BookMapper {
    @Select("select * from book where book_id = #{bookId}")
    Book getBookById(Integer bookId); //根据id查询教材

    @Select("select count(*) from book")
    Integer getBookNumbers(); //查询出总共教材的数量

    @Select("select * from book")
    List<Book> getAllBooks(); //查询出所有教材

    @Insert("insert into book(book_id, book_rank, subject_id, book_name, book_icon) " +
            "values(#{bookId}, #{bookRank}, #{subjectId}, #{bookName}, #{bookIcon})")
    Integer addBook(Book book); //添加教材

    @Delete("delete from book where book_id = #{bookId}")
    Integer deleteBookById(Integer bookId); //根据id删除教材

    @Update("update book set book_rank = #{bookRank}, subject_id = #{subjectId}, " +
            "book_name = #{bookName}, book_icon = #{bookIcon} " +
            "where book_id = #{bookId}")
    Integer updateBook(Book book); //根据id更新教材信息
}
