package cn.net.sybt.springboot.bean;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TeaJudge extends TeaQuestion{
    private String quesContent;
    private String quesLink;
    private Integer isTrue;
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

    public String getQuesLink() {
        return quesLink;
    }

    public void setQuesLink(String quesLink) {
        this.quesLink = quesLink;
    }

    public Integer getIsTrue() {
        return isTrue;
    }

    public void setIsTrue(Integer isTrue) {
        this.isTrue = isTrue;
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
    public String toString() {
        return "TeaJudge{" +
                "quesContent='" + quesContent + '\'' +
                ", quesLink='" + quesLink + '\'' +
                ", isTrue=" + isTrue +
                ", solutionText='" + solutionText + '\'' +
                ", solutionLink='" + solutionLink + '\'' +
                ", quesId=" + quesId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeaJudge teaJudge = (TeaJudge) o;
        return Objects.equals(quesContent, teaJudge.quesContent) &&
                Objects.equals(quesLink, teaJudge.quesLink) &&
                Objects.equals(isTrue, teaJudge.isTrue) &&
                Objects.equals(solutionText, teaJudge.solutionText) &&
                Objects.equals(solutionLink, teaJudge.solutionLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quesContent, quesLink, isTrue, solutionText, solutionLink);
    }
}
