package cn.net.sybt.springboot.bean;

import java.util.Date;
import java.util.Objects;

public class Answer {
    private Integer answerId;
    private Integer stuId;
    private Integer chId;
    private Double percentage;
    private Date uploadTime;

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Integer getChId() {
        return chId;
    }

    public void setChId(Integer chId) {
        this.chId = chId;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answerId=" + answerId +
                ", stuId=" + stuId +
                ", chId=" + chId +
                ", percentage=" + percentage +
                ", uploadTime=" + uploadTime +
                '}';
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Answer answer = (Answer) o;

        if (!Objects.equals(answerId, answer.answerId)) return false;
        if (!Objects.equals(stuId, answer.stuId)) return false;
        if (!Objects.equals(chId, answer.chId)) return false;
        if (!Objects.equals(percentage, answer.percentage)) return false;
        return Objects.equals(uploadTime, answer.uploadTime);
    }

    @Override
    public int hashCode() {
        int result = answerId != null ? answerId.hashCode() : 0;
        result = 31 * result + (stuId != null ? stuId.hashCode() : 0);
        result = 31 * result + (chId != null ? chId.hashCode() : 0);
        result = 31 * result + (percentage != null ? percentage.hashCode() : 0);
        result = 31 * result + (uploadTime != null ? uploadTime.hashCode() : 0);
        return result;
    }
}
