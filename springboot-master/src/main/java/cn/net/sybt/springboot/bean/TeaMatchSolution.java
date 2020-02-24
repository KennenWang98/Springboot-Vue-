package cn.net.sybt.springboot.bean;


import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TeaMatchSolution extends TeaSolution{

    private String leftValue;
    private String rightValue;
    private String leftLink;
    private String rightLink;


    public Integer getQuesId() {
        return quesId;
    }

    public void setQuesId(Integer quesId) {
        this.quesId = quesId;
    }

    public String getLeftValue() {
        return leftValue;
    }

    public void setLeftValue(String leftValue) {
        this.leftValue = leftValue;
    }

    public String getRightValue() {
        return rightValue;
    }

    public void setRightValue(String rightValue) {
        this.rightValue = rightValue;
    }

    public String getLeftLink() {
        return leftLink;
    }

    public void setLeftLink(String leftLink) {
        this.leftLink = leftLink;
    }

    public String getRightLink() {
        return rightLink;
    }

    public void setRightLink(String rightLink) {
        this.rightLink = rightLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeaMatchSolution that = (TeaMatchSolution) o;
        return Objects.equals(quesId, that.quesId) &&
                Objects.equals(leftValue, that.leftValue) &&
                Objects.equals(rightValue, that.rightValue) &&
                Objects.equals(leftLink, that.leftLink) &&
                Objects.equals(rightLink, that.rightLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quesId, leftValue, rightValue, leftLink, rightLink);
    }

    @Override
    public String toString() {
        return "teaMatchSolution{" +
                "quesId=" + quesId +
                ", leftValue='" + leftValue + '\'' +
                ", rightValue='" + rightValue + '\'' +
                ", leftLink='" + leftLink + '\'' +
                ", rightLink='" + rightLink + '\'' +
                '}';
    }
}
