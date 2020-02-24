package cn.net.sybt.springboot.service;

import cn.net.sybt.springboot.bean.Book;
import cn.net.sybt.springboot.mapper.BookMapper;
import cn.net.sybt.springboot.mapper.ClazzMapper;
import cn.net.sybt.springboot.mapper.LicenseCodeMapper;
import cn.net.sybt.springboot.utils.CheckObject;
import cn.net.sybt.springboot.utils.ResultEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class BookService {
    @Resource
    private BookMapper bookMapper;
    @Resource
    private CheckObject checkObject;
    @Resource
    private ClazzMapper clazzMapper;
    @Resource
    private LicenseCodeMapper licenseCodeMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer addBook(Book book) {
        boolean flag = checkObject.check(book);
        int code = 0;
        if (!flag) {
            code = bookMapper.addBook(book);
        }
        if (code != 0) {
            return ResultEnum.SUCCESS.code;
        } else {
            return ResultEnum.INSERT_ERROR.code;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer deleteBook(Integer bookId) {
        return bookMapper.deleteBookById(bookId);
    }

    public List<Book> getAllBook() {
        return bookMapper.getAllBooks();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer updateBook(Book book) {
        Book book1 = bookMapper.getBookById(book.getBookId());
        if (!(book.getBookName() == null || "".equals(book.getBookName()))) {
            book1.setBookName(book.getBookName());
        }
        if (!(book.getBookIcon() == null || "".equals(book.getBookIcon()))) {
            book1.setSubjectId(book.getSubjectId());
        }
        if (!(book.getBookRank() == null)) {
            book1.setBookRank(book.getBookRank());
        }
        int code = bookMapper.updateBook(book1);
        if (code != 0) {
            return ResultEnum.SUCCESS.code;
        } else {
            return ResultEnum.UPDATE_ERROR.code;
        }
    }

    public Integer getBookByClazz(Integer classId) {
        String code = clazzMapper.getCode(classId);
        return licenseCodeMapper.getLicenseCodeByCode(code).getBookId();
    }
}
