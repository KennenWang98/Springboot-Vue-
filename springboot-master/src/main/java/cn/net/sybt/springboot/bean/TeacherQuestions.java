package cn.net.sybt.springboot.bean;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TeacherQuestions {
    private Integer teaQuesId;
    private Integer teaId;
    private Integer classId;
    private String teaQuesType;
    private Integer bookId;
    private Integer teaQuesSpecificId;

    public Integer getTeaQuesId() {
        return teaQuesId;
    }

    public void setTeaQuesId(Integer teaQuesId) {
        this.teaQuesId = teaQuesId;
    }

    public Integer getTeaId() {
        return teaId;
    }

    public void setTeaId(Integer teaId) {
        this.teaId = teaId;
    }

    public String getTeaQuesType() {
        return teaQuesType;
    }

    public void setTeaQuesType(String teaQuesType) {
        this.teaQuesType = teaQuesType;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getTeaQuesSpecificId() {
        return teaQuesSpecificId;
    }

    public void setTeaQuesSpecificId(Integer teaQuesSpecificId) {
        this.teaQuesSpecificId = teaQuesSpecificId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherQuestions that = (TeacherQuestions) o;
        return Objects.equals(teaQuesId, that.teaQuesId) &&
                Objects.equals(teaId, that.teaId) &&
                Objects.equals(classId, that.classId) &&
                Objects.equals(teaQuesType, that.teaQuesType) &&
                Objects.equals(bookId, that.bookId) &&
                Objects.equals(teaQuesSpecificId, that.teaQuesSpecificId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teaQuesId, teaId, classId, teaQuesType, bookId, teaQuesSpecificId);
    }

    @Override
    public String toString() {
        return "TeacherQuestions{" +
                "teaQuesId=" + teaQuesId +
                ", teaId=" + teaId +
                ", classId=" + classId +
                ", teaQuesType='" + teaQuesType + '\'' +
                ", bookId=" + bookId +
                ", teaQuesSpecificId=" + teaQuesSpecificId +
                '}';
    }
}
