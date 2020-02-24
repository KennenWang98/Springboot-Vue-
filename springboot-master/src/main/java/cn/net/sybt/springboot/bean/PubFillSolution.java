package cn.net.sybt.springboot.bean;

import cn.net.sybt.springboot.interfaces.SolutionInterface;

import java.util.Objects;

public class PubFillSolution implements SolutionInterface {
    private Integer quesId;
    private Integer solutionNo;
    private String solutionContent;

    public PubFillSolution() {
    }

    @Override
    public String toString() {
        return "PubFillSolution{" +
                "quesId=" + quesId +
                ", solutionNo=" + solutionNo +
                ", solutionContent='" + solutionContent + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PubFillSolution)) return false;
        PubFillSolution that = (PubFillSolution) o;
        return Objects.equals(quesId, that.quesId) &&
                Objects.equals(solutionNo, that.solutionNo) &&
                Objects.equals(solutionContent, that.solutionContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quesId, solutionNo, solutionContent);
    }

    public Integer getQuesId() {
        return quesId;
    }

    @Override
    public void setQuesId(Integer quesId) {
        this.quesId = quesId;
    }

    public Integer getSolutionNo() {
        return solutionNo;
    }

    public void setSolutionNo(Integer solutionNo) {
        this.solutionNo = solutionNo;
    }

    public String getSolutionContent() {
        return solutionContent;
    }

    public void setSolutionContent(String solutionContent) {
        this.solutionContent = solutionContent;
    }
}
