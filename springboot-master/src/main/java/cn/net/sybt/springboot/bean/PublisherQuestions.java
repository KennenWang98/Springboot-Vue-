package cn.net.sybt.springboot.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Objects;

public class PublisherQuestions {
    @TableId(type = IdType.AUTO)
    private Integer pubQuesId;
    private Integer bookId;
    private String pubQuesType;
    private Integer pubQuesSpecificId;

    public PublisherQuestions(Integer pubQuesId, Integer bookId, String pubQuesType, Integer pubQuesSpecificId) {
        this.pubQuesId = pubQuesId;
        this.bookId = bookId;
        this.pubQuesType = pubQuesType;
        this.pubQuesSpecificId = pubQuesSpecificId;
    }

    public PublisherQuestions() {
    }

    @Override
    public String toString() {
        return "PublisherQuestions{" +
                "pubQuesId=" + pubQuesId +
                ", bookId=" + bookId +
                ", pubQuesType='" + pubQuesType + '\'' +
                ", pubQuesSpecificId=" + pubQuesSpecificId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PublisherQuestions)) return false;
        PublisherQuestions that = (PublisherQuestions) o;
        return Objects.equals(getPubQuesId(), that.getPubQuesId()) &&
                Objects.equals(getBookId(), that.getBookId()) &&
                Objects.equals(getPubQuesType(), that.getPubQuesType()) &&
                Objects.equals(getPubQuesSpecificId(), that.getPubQuesSpecificId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPubQuesId(), getBookId(), getPubQuesType(), getPubQuesSpecificId());
    }

    public Integer getPubQuesId() {
        return pubQuesId;
    }

    public void setPubQuesId(Integer pubQuesId) {
        this.pubQuesId = pubQuesId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getPubQuesType() {
        return pubQuesType;
    }

    public void setPubQuesType(String pubQuesType) {
        this.pubQuesType = pubQuesType;
    }

    public Integer getPubQuesSpecificId() {
        return pubQuesSpecificId;
    }

    public void setPubQuesSpecificId(Integer pubQuesSpecificId) {
        this.pubQuesSpecificId = pubQuesSpecificId;
    }
}
