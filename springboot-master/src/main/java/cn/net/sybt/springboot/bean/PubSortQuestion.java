package cn.net.sybt.springboot.bean;

import cn.net.sybt.springboot.interfaces.PubQuestionInterface;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Objects;

public class PubSortQuestion implements PubQuestionInterface {
    @TableId(type = IdType.AUTO)
    private Integer quesId;
    private String quesContent;
    private String solutionText;
    private String solutionLink;

    public PubSortQuestion() {
    }

    @Override
    public String toString() {
        return "PublicSortQuestion{" +
                "quesId=" + quesId +
                ", quesContent='" + quesContent + '\'' +
                ", solutionText='" + solutionText + '\'' +
                ", solutionLink='" + solutionLink + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PubSortQuestion)) return false;
        PubSortQuestion that = (PubSortQuestion) o;
        return Objects.equals(getQuesId(), that.getQuesId()) &&
                Objects.equals(getQuesContent(), that.getQuesContent()) &&
                Objects.equals(getSolutionText(), that.getSolutionText()) &&
                Objects.equals(getSolutionLink(), that.getSolutionLink());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQuesId(), getQuesContent(), getSolutionText(), getSolutionLink());
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
