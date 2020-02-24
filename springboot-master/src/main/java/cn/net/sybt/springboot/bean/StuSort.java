package cn.net.sybt.springboot.bean;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class StuSort {
    private Integer acId;
    private String answerContent;
    private Integer answerNo;
    private Integer isTrue;

    public Integer getAcId() {
        return acId;
    }

    public void setAcId(Integer acId) {
        this.acId = acId;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public Integer getAnswerNo() {
        return answerNo;
    }

    public void setAnswerNo(Integer answerNo) {
        this.answerNo = answerNo;
    }

    public Integer getIsTrue() {
        return isTrue;
    }

    public void setIsTrue(Integer isTrue) {
        this.isTrue = isTrue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StuSort stuSort = (StuSort) o;
        return Objects.equals(acId, stuSort.acId) &&
                Objects.equals(answerContent, stuSort.answerContent) &&
                Objects.equals(answerNo, stuSort.answerNo) &&
                Objects.equals(isTrue, stuSort.isTrue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(acId, answerContent, answerNo, isTrue);
    }

    @Override
    public String toString() {
        return "StuSort{" +
                "acId=" + acId +
                ", answerContent='" + answerContent + '\'' +
                ", answerNo=" + answerNo +
                ", isTrue=" + isTrue +
                '}';
    }
}
