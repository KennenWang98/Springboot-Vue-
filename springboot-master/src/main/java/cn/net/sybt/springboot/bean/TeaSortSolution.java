package cn.net.sybt.springboot.bean;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TeaSortSolution extends TeaSolution{

    private String quesContent;
    private String sortNo;

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

    public String getSortNo() {
        return sortNo;
    }

    public void setSortNo(String sortNo) {
        this.sortNo = sortNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeaSortSolution that = (TeaSortSolution) o;
        return Objects.equals(quesId, that.quesId) &&
                Objects.equals(quesContent, that.quesContent) &&
                Objects.equals(sortNo, that.sortNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quesId, quesContent, sortNo);
    }

    @Override
    public String toString() {
        return "TeaSortSolution{" +
                "quesId=" + quesId +
                ", quesContent='" + quesContent + '\'' +
                ", sortNo='" + sortNo + '\'' +
                '}';
    }
}
