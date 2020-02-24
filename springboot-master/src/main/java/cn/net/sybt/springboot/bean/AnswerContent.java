package cn.net.sybt.springboot.bean;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AnswerContent {
    private Integer acId;
    private Integer answerId;
    private Integer hcId;
    private Integer singleQuesId;
    private String quesType;
    private String feedback;
    private Double point;

    public Integer getAcId() {
        return acId;
    }

    public void setAcId(Integer acId) {
        this.acId = acId;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Integer getHcId() {
        return hcId;
    }

    public void setHcId(Integer hcId) {
        this.hcId = hcId;
    }

    public Integer getSingleQuesId() {
        return singleQuesId;
    }

    public void setSingleQuesId(Integer singleQuesId) {
        this.singleQuesId = singleQuesId;
    }

    public String getQuesType() {
        return quesType;
    }

    public void setQuesType(String quesType) {
        this.quesType = quesType;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerContent that = (AnswerContent) o;
        return Objects.equals(acId, that.acId) &&
                Objects.equals(answerId, that.answerId) &&
                Objects.equals(hcId, that.hcId) &&
                Objects.equals(singleQuesId, that.singleQuesId) &&
                Objects.equals(quesType, that.quesType) &&
                Objects.equals(feedback, that.feedback);
    }

    @Override
    public int hashCode() {
        return Objects.hash(acId, answerId, hcId, singleQuesId, quesType, feedback);
    }

    public Double getPoint() {
        return point;
    }

    public void setPoint(Double point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "AnswerContent{" +
                "acId=" + acId +
                ", answerId=" + answerId +
                ", hcId=" + hcId +
                ", singleQuesId=" + singleQuesId +
                ", quesType='" + quesType + '\'' +
                ", feedback='" + feedback + '\'' +
                ", point=" + point +
                '}';
    }
}
