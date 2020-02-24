package cn.net.sybt.springboot.vo;

import java.util.List;
import java.util.Objects;

public class QuestionVO<Q, S> {
    private String quesType;
    private Integer flag;
    private Integer hcId;
    private Q question;
    private List<S> solutions;

    public Integer getHcId() {
        return hcId;
    }

    public void setHcId(Integer hcId) {
        this.hcId = hcId;
    }

    public String getQuesType() {
        return quesType;
    }

    public void setQuesType(String quesType) {
        this.quesType = quesType;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Q getQuestion() {
        return question;
    }

    public void setQuestion(Q question) {
        this.question = question;
    }

    public List<S> getSolutions() {
        return solutions;
    }

    public void setSolutions(List<S> solutions) {
        this.solutions = solutions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionVO<?, ?> that = (QuestionVO<?, ?>) o;

        if (!Objects.equals(quesType, that.quesType)) return false;
        if (!Objects.equals(flag, that.flag)) return false;
        if (!Objects.equals(hcId, that.hcId)) return false;
        if (!Objects.equals(question, that.question)) return false;
        return Objects.equals(solutions, that.solutions);
    }

    @Override
    public int hashCode() {
        int result = quesType != null ? quesType.hashCode() : 0;
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        result = 31 * result + (hcId != null ? hcId.hashCode() : 0);
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (solutions != null ? solutions.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "QuestionVO{" +
                "quesType='" + quesType + '\'' +
                ", flag=" + flag +
                ", hcId=" + hcId +
                ", question=" + question +
                ", solutions=" + solutions +
                '}';
    }
}
