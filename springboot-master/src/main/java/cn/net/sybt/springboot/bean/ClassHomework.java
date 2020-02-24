package cn.net.sybt.springboot.bean;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
public class ClassHomework {
    private Integer chId;
    private Date layoutTime;
    private Integer teaId;
    private Integer classId;
    private Date deadline;
    private String homeworkName;
    private String status;

    public Integer getChId() {
        return chId;
    }

    public void setChId(Integer chId) {
        this.chId = chId;
    }

    public Date getLayoutTime() {
        return layoutTime;
    }

    public void setLayoutTime(Date layoutTime) {
        this.layoutTime = layoutTime;
    }

    public Integer getTeaId() {
        return teaId;
    }

    public void setTeaId(Integer teaId) {
        this.teaId = teaId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getHomeworkName() {
        return homeworkName;
    }

    public void setHomeworkName(String homeworkName) {
        this.homeworkName = homeworkName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ClassHomework{" +
                "chId=" + chId +
                ", layoutTime=" + layoutTime +
                ", teaId=" + teaId +
                ", classId=" + classId +
                ", deadline=" + deadline +
                ", homeworkName='" + homeworkName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassHomework that = (ClassHomework) o;
        return Objects.equals(chId, that.chId) &&
                Objects.equals(layoutTime, that.layoutTime) &&
                Objects.equals(teaId, that.teaId) &&
                Objects.equals(classId, that.classId) &&
                Objects.equals(deadline, that.deadline) &&
                Objects.equals(homeworkName, that.homeworkName) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chId, layoutTime, teaId, classId, deadline, homeworkName, status);
    }
}
