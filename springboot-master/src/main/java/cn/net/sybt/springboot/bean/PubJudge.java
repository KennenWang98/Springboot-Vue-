package cn.net.sybt.springboot.bean;

import cn.net.sybt.springboot.interfaces.PubQuestionInterface;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Objects;

public class PubJudge implements PubQuestionInterface {
    @TableId(type = IdType.AUTO)
    private Integer quesId;
    private String quesContent;
    private String quesLink;
    private Integer isTrue;
    private String solutionText;
    private String solutionLink;

    public PubJudge() {
    }

    public PubJudge(String quesContent, String quesLink, Integer isTrue, String solutionText, String solutionLink) {
        this.quesContent = quesContent;
        this.quesLink = quesLink;
        this.isTrue = isTrue;
        this.solutionText = solutionText;
        this.solutionLink = solutionLink;
    }

    @Override
    public String toString() {
        return "pubJudge{" +
                "quesId=" + quesId +
                ", quesContent='" + quesContent + '\'' +
                ", quesLink='" + quesLink + '\'' +
                ", isTrue=" + isTrue +
                ", solutionText='" + solutionText + '\'' +
                ", solutionLink='" + solutionLink + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PubJudge)) return false;
        PubJudge pubJudge = (PubJudge) o;
        return Objects.equals(getQuesId(), pubJudge.getQuesId()) &&
                Objects.equals(getQuesContent(), pubJudge.getQuesContent()) &&
                Objects.equals(getQuesLink(), pubJudge.getQuesLink()) &&
                Objects.equals(getIsTrue(), pubJudge.getIsTrue()) &&
                Objects.equals(getSolutionText(), pubJudge.getSolutionText()) &&
                Objects.equals(getSolutionLink(), pubJudge.getSolutionLink());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQuesId(), getQuesContent(), getQuesLink(), getIsTrue(), getSolutionText(), getSolutionLink());
    }

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
}
