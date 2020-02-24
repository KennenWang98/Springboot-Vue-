package cn.net.sybt.springboot.controller;

import cn.net.sybt.springboot.bean.Book;
import cn.net.sybt.springboot.consts.Parameters;
import cn.net.sybt.springboot.service.BookService;
import cn.net.sybt.springboot.utils.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/book")
public class BookController {
    @Resource
    private CheckObject checkObject;
    @Resource
    private BookService bookService;

    @RequestMapping("/print")
    public ResultModel<List<Book>> getAllBooks() {
        return RetResponse.okResp(bookService.getAllBook());
    }

    @RequestMapping("/add")
    public ResultModel<Book> addBook(@RequestBody Book book) {
        boolean flag = checkObject.check(book);
        int a = 0;
        if (!flag) {
            if (!(book.getBookName() == null || "".equals(book.getBookName()))) {
                //添加图书起码要有书名，别的不管
                a = bookService.addBook(book);
            }
        }
        if (a == 200) { //成功
            return RetResponse.okResp();
        } else {
            return RetResponse.resp(a, ResultEnum.INSERT_ERROR.msg);
        }
    }

    @RequestMapping("/delete")
    public ResultModel<Integer> deleteBook(@RequestParam Integer bookId) {
        int code = bookService.deleteBook(bookId);
        if (code != 0) {
            return RetResponse.okResp();
        } else {
            return RetResponse.resp(ResultEnum.DELETE_ERROR.code, ResultEnum.DELETE_ERROR.msg);
        }
    }

    @RequestMapping("/change")
    public ResultModel<Integer> updateBook(@RequestBody Book book) {
        boolean flag = checkObject.check(book);
        int code = 0;
        if (!flag) {
            code = bookService.updateBook(book);
        }
        if (code == 200) {
            return RetResponse.okResp();
        } else {
            return RetResponse.resp(ResultEnum.UPDATE_ERROR.code, ResultEnum.UPDATE_ERROR.msg);
        }
    }

    @RequestMapping("/upload")
    public ResultModel<String> upload(@RequestParam("file") MultipartFile file) { //上传单个文件
        String uuid = UUID.randomUUID().toString();
        String path = "book/";
        Boolean result = FileUtil.uploadFile(file, path, uuid);
        if (result == null) {
            return RetResponse.resp(ResultEnum.INTERNAL_SERVER_ERROR.code, "上传失败，请选择文件");
        } else if (!result) {
            return RetResponse.resp(ResultEnum.INTERNAL_SERVER_ERROR.code, "上传失败");
        } else {
            return RetResponse.okResp(Parameters.IP_ADDRESS + "/public/book/" + uuid + file.getOriginalFilename());
        }
    }
}
