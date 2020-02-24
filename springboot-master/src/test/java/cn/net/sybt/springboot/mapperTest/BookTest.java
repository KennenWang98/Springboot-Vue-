package cn.net.sybt.springboot.mapperTest;

import cn.net.sybt.springboot.bean.Book;
import cn.net.sybt.springboot.mapper.BookMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class BookTest {
    @Autowired
    BookMapper bookMapper;

    @Test
    public void testGetBookById(){
        Book book = bookMapper.getBookById(1);
        System.out.println(book);
    }

    @Test
    public void testGetAllBooks() {
        List<Book> books = bookMapper.getAllBooks();
        books.forEach(System.out::println);
    }

    @Test
    public void testAddBook(){
        Book book = new Book();
        book.setBookName("化学");
        book.setBookRank("初三");
        book.setSubjectId(1);
        book.setBookIcon("1");
        int a = bookMapper.addBook(book);
        System.out.println(a);
    }

    @Test
    public void testDeleteBookById(){
        int a = bookMapper.deleteBookById(3);
        System.out.println(a);
    }

    @Test
    public void testUpdateBook(){
        Book book = new Book();
        book.setBookIcon("sd");
        book.setBookName("语文");
        book.setBookId(2);
        System.out.println(bookMapper.updateBook(book));
    }
}
