package cn.net.sybt.springboot.bean;

import cn.net.sybt.springboot.interfaces.SolutionInterface;

import java.util.Objects;

public class PubChoiceSolution implements SolutionInterface {
    private Integer quesId;
    private Integer isTrue;
    private String choiceContent;
    private String choiceLink;

    public PubChoiceSolution() {
    }

    @Override
    public String toString() {
        return "PubChoiceSolution{" +
                "quesId=" + quesId +
                ", isTrue=" + isTrue +
                ", choiceContent=" + choiceContent +
                ", choiceLink=" + choiceLink +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PubChoiceSolution)) return false;
        PubChoiceSolution that = (PubChoiceSolution) o;
        return Objects.equals(getQuesId(), that.getQuesId()) &&
                Objects.equals(getIsTrue(), that.getIsTrue()) &&
                Objects.equals(getChoiceContent(), that.getChoiceContent()) &&
                Objects.equals(getChoiceLink(), that.getChoiceLink());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQuesId(), getIsTrue(), getChoiceContent(), getChoiceLink());
    }

    public Integer getQuesId() {
        return quesId;
    }

    @Override
    public void setQuesId(Integer quesId) {
        this.quesId = quesId;
    }

    public Integer getIsTrue() {
        return isTrue;
    }

    public void setIsTrue(Integer isTrue) {
        this.isTrue = isTrue;
    }

    public String getChoiceContent() {
        return choiceContent;
    }

    public void setChoiceContent(String choiceContent) {
        this.choiceContent = choiceContent;
    }

    public String getChoiceLink() {
        return choiceLink;
    }

    public void setChoiceLink(String choiceLink) {
        this.choiceLink = choiceLink;
    }
}
