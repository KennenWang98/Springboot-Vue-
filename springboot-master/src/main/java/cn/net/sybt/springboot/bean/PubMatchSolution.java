package cn.net.sybt.springboot.bean;

import cn.net.sybt.springboot.interfaces.SolutionInterface;

import java.util.Objects;

public class PubMatchSolution implements SolutionInterface {
    private Integer quesId;
    private String leftValue;
    private String leftLink;
    private String rightValue;
    private String rightLink;

    public PubMatchSolution() {
    }

    public PubMatchSolution(Integer quesId, String leftValue, String leftLink, String rightValue, String rightLink) {
        this.quesId = quesId;
        this.leftValue = leftValue;
        this.leftLink = leftLink;
        this.rightValue = rightValue;
        this.rightLink = rightLink;
    }

    @Override
    public String toString() {
        return "PubMatchSolution{" +
                "quesId=" + quesId +
                ", leftValue='" + leftValue + '\'' +
                ", leftLink='" + leftLink + '\'' +
                ", rightValue='" + rightValue + '\'' +
                ", rightLink='" + rightLink + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PubMatchSolution)) return false;
        PubMatchSolution that = (PubMatchSolution) o;
        return Objects.equals(getQuesId(), that.getQuesId()) &&
                Objects.equals(getLeftValue(), that.getLeftValue()) &&
                Objects.equals(getLeftLink(), that.getLeftLink()) &&
                Objects.equals(getRightValue(), that.getRightValue()) &&
                Objects.equals(getRightLink(), that.getRightLink());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQuesId(), getLeftValue(), getLeftLink(), getRightValue(), getRightLink());
    }

    public Integer getQuesId() {
        return quesId;
    }

    @Override
    public void setQuesId(Integer ques_id) {
        this.quesId = ques_id;
    }

    public String getLeftValue() {
        return leftValue;
    }

    public void setLeftValue(String leftValue) {
        this.leftValue = leftValue;
    }

    public String getLeftLink() {
        return leftLink;
    }

    public void setLeftLink(String leftLink) {
        this.leftLink = leftLink;
    }

    public String getRightValue() {
        return rightValue;
    }

    public void setRightValue(String rightValue) {
        this.rightValue = rightValue;
    }

    public String getRightLink() {
        return rightLink;
    }

    public void setRightLink(String rightLink) {
        this.rightLink = rightLink;
    }
}
