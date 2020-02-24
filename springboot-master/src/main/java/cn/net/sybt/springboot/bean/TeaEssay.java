package cn.net.sybt.springboot.bean;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TeaEssay extends TeaQuestion {
    private String quesContent;
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
        TeaEssay teaEssay = (TeaEssay) o;
        return Objects.equals(quesContent, teaEssay.quesContent) &&
                Objects.equals(solutionLink, teaEssay.solutionLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quesContent, solutionLink);
    }

    @Override
    public String toString() {
        return "TeaEssay{" +
                "quesContent='" + quesContent + '\'' +
                ", solutionLink='" + solutionLink + '\'' +
                ", quesId=" + quesId +
                '}';
    }
}
