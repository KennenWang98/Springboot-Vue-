package cn.net.sybt.springboot.bean;

import org.springframework.stereotype.Component;

@Component
public class Book { //教材
    private Integer bookId;
    private String bookRank;
    private Integer subjectId;
    private String bookName;
    private String bookIcon;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookRank() {
        return bookRank;
    }

    public void setBookRank(String bookRank) {
        this.bookRank = bookRank;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookIcon() {
        return bookIcon;
    }

    public void setBookIcon(String bookIcon) {
        this.bookIcon = bookIcon;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookRank='" + bookRank + '\'' +
                ", subjectId=" + subjectId +
                ", bookName='" + bookName + '\'' +
                ", bookIcon='" + bookIcon + '\'' +
                '}';
    }

    public Book() {
    }

    public Book(Integer bookId, String bookRank, Integer subjectId, String bookName, String bookIcon) {
        this.bookId = bookId;
        this.bookRank = bookRank;
        this.subjectId = subjectId;
        this.bookName = bookName;
        this.bookIcon = bookIcon;
    }
}
