package cn.net.sybt.springboot.bean;

import cn.net.sybt.springboot.interfaces.PubQuestionInterface;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Objects;

public class PubEssay implements PubQuestionInterface {
    @TableId(type = IdType.AUTO)
    private Integer quesId;
    private String quesContent;
    private String solutionLink;

    public PubEssay() {
    }

    @Override
    public String toString() {
        return "PubEssay{" +
                "quesId=" + quesId +
                ", quesContent='" + quesContent + '\'' +
                ", solutionLink='" + solutionLink + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PubEssay)) return false;
        PubEssay pubEssay = (PubEssay) o;
        return Objects.equals(getQuesId(), pubEssay.getQuesId()) &&
                Objects.equals(getQuesContent(), pubEssay.getQuesContent()) &&
                Objects.equals(getSolutionLink(), pubEssay.getSolutionLink());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQuesId(), getQuesContent(), getSolutionLink());
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

    public String getSolutionLink() {
        return solutionLink;
    }

    public void setSolutionLink(String solutionLink) {
        this.solutionLink = solutionLink;
    }
}
