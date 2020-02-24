package cn.net.sybt.springboot.bean;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TeaChoiceSolution extends TeaSolution{
    private Integer isTrue;
    private String choiceContent;
    private String choiceLink;

    @Override
    public String toString() {
        return "TeaChoiceSolution{" +
                "quesId=" + quesId +
                ", isTrue=" + isTrue +
                ", choiceContent='" + choiceContent + '\'' +
                ", choiceLink='" + choiceLink + '\'' +
                '}';
    }

    public Integer getQuesId() {
        return quesId;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeaChoiceSolution that = (TeaChoiceSolution) o;
        return Objects.equals(quesId, that.quesId) &&
                Objects.equals(isTrue, that.isTrue) &&
                Objects.equals(choiceContent, that.choiceContent) &&
                Objects.equals(choiceLink, that.choiceLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quesId, isTrue, choiceContent, choiceLink);
    }
}
