package cn.net.sybt.springboot.bean;


import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TeaChoiceQuestion extends TeaQuestion{
    private String quesContent;
    private String solutionText;
    private String solutionLink;


    public Integer getQuesId() {
        return quesId;
    }

    public void setQuesId(Integer quesId) {
        this.quesId = quesId;
    }

    public String getQuesContent() {
        return quesContent;
    }

    public void setQuesContent(String quesContent) {
        this.quesContent = quesContent;
    }

    public String getSolutionText() {
        return solutionText;
    }

    public void setSolutionText(String solutionText) {
        this.solutionText = solutionText;
    }

    public String getSolutionLink() {
        return solutionLink;
    }

    public void setSolutionLink(String solutionLink) {
        this.solutionLink = solutionLink;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeaChoiceQuestion that = (TeaChoiceQuestion) o;
        return Objects.equals(quesContent, that.quesContent) &&
                Objects.equals(solutionText, that.solutionText) &&
                Objects.equals(solutionLink, that.solutionLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quesContent, solutionText, solutionLink);
    }

    @Override
    public String toString() {
        return "TeaChoiceQuestion{" +
                "quesContent='" + quesContent + '\'' +
                ", solutionText='" + solutionText + '\'' +
                ", solutionLink='" + solutionLink + '\'' +
                ", quesId=" + quesId +
                '}';
    }
}
