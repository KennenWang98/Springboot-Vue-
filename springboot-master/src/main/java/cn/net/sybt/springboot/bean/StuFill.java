package cn.net.sybt.springboot.bean;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class StuFill extends StuAnswer {
    private String stuAnswer;
    private Integer answerNo;
    private Integer isTrue;


    public String getStuAnswer() {
        return stuAnswer;
    }

    public void setStuAnswer(String stuAnswer) {
        this.stuAnswer = stuAnswer;
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

        StuFill stuFill = (StuFill) o;

        if (!Objects.equals(stuAnswer, stuFill.stuAnswer)) return false;
        if (!Objects.equals(answerNo, stuFill.answerNo)) return false;
        return Objects.equals(isTrue, stuFill.isTrue);
    }

    @Override
    public int hashCode() {
        int result = stuAnswer != null ? stuAnswer.hashCode() : 0;
        result = 31 * result + (answerNo != null ? answerNo.hashCode() : 0);
        result = 31 * result + (isTrue != null ? isTrue.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StuFill{" +
                "stuAnswer='" + stuAnswer + '\'' +
                ", answerNo=" + answerNo +
                ", isTrue=" + isTrue +
                "} " + super.toString();
    }
}
