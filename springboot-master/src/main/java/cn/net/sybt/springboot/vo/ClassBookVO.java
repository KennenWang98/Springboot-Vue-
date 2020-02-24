package cn.net.sybt.springboot.vo;

import cn.net.sybt.springboot.bean.Book;
import cn.net.sybt.springboot.bean.Clazz;

import java.util.Objects;

public class ClassBookVO {
    private Clazz clazz;
    private Book book;

    public ClassBookVO(Clazz clazz, Book book) {
        this.clazz = clazz;
        this.book = book;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassBookVO that = (ClassBookVO) o;
        return Objects.equals(clazz, that.clazz) &&
                Objects.equals(book, that.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clazz, book);
    }

    @Override
    public String toString() {
        return "ClassBookVO{" +
                "clazz=" + clazz +
                ", book=" + book +
                '}';
    }
}
