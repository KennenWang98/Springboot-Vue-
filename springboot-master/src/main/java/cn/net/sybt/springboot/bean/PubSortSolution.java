package cn.net.sybt.springboot.bean;

import cn.net.sybt.springboot.interfaces.SolutionInterface;

import java.util.Objects;

public class PubSortSolution implements SolutionInterface {
    private Integer quesId;
    private String solutionContent;
    private Integer sortNo;

    public PubSortSolution() {
    }

    @Override
    public String toString() {
        return "PubSortSolution{" +
                "quesId=" + quesId +
                ", solutionContent='" + solutionContent + '\'' +
                ", sortNo=" + sortNo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PubSortSolution)) return false;
        PubSortSolution that = (PubSortSolution) o;
        return Objects.equals(getQuesId(), that.getQuesId()) &&
                Objects.equals(getSolutionContent(), that.getSolutionContent()) &&
                Objects.equals(getSortNo(), that.getSortNo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQuesId(), getSolutionContent(), getSortNo());
    }

    public Integer getQuesId() {
        return quesId;
    }

    @Override
    public void setQuesId(Integer quesId) {
        this.quesId = quesId;
    }

    public String getSolutionContent() {
        return solutionContent;
    }

    public void setSolutionContent(String solutionContent) {
        this.solutionContent = solutionContent;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }
}
