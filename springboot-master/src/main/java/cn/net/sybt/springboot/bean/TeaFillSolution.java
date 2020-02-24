package cn.net.sybt.springboot.bean;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TeaFillSolution extends TeaSolution{

    private Integer solutionNo;
    private String solutionContent;

    @Override
    public String toString() {
        return "TeaFillSolution{" +
                "quesId=" + quesId +
                ", solutionNo=" + solutionNo +
                ", solutionContent='" + solutionContent + '\'' +
                '}';
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeaFillSolution that = (TeaFillSolution) o;
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
