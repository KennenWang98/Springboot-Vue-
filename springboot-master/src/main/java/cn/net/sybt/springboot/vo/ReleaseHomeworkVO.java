package cn.net.sybt.springboot.vo;

import java.util.List;
import java.util.Objects;

public class ReleaseHomeworkVO {//这个VO封装了上传作业时前端给我返回的数据
    private Integer second;
    private Integer teaId;
    private Integer classId;
    private String homeworkName;
    private List<HomeworkContentVO> homeworkContentVOs;

    public Integer getSecond() {
        return second;
    }

    public void setSecond(Integer second) {
        this.second = second;
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

    public String getHomeworkName() {
        return homeworkName;
    }

    public void setHomeworkName(String homeworkName) {
        this.homeworkName = homeworkName;
    }

    public List<HomeworkContentVO> getHomeworkContentVOs() {
        return homeworkContentVOs;
    }

    public void setHomeworkContentVOs(List<HomeworkContentVO> homeworkContentVOs) {
        this.homeworkContentVOs = homeworkContentVOs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReleaseHomeworkVO that = (ReleaseHomeworkVO) o;

        if (!Objects.equals(second, that.second)) return false;
        if (!Objects.equals(teaId, that.teaId)) return false;
        if (!Objects.equals(classId, that.classId)) return false;
        if (!Objects.equals(homeworkName, that.homeworkName)) return false;
        return Objects.equals(homeworkContentVOs, that.homeworkContentVOs);
    }

    @Override
    public int hashCode() {
        int result = second != null ? second.hashCode() : 0;
        result = 31 * result + (teaId != null ? teaId.hashCode() : 0);
        result = 31 * result + (classId != null ? classId.hashCode() : 0);
        result = 31 * result + (homeworkName != null ? homeworkName.hashCode() : 0);
        result = 31 * result + (homeworkContentVOs != null ? homeworkContentVOs.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ReleaseHomeworkVO{" +
                "second=" + second +
                ", teaId=" + teaId +
                ", classId=" + classId +
                ", homeworkName='" + homeworkName + '\'' +
                ", homeworkContentVOs=" + homeworkContentVOs +
                '}';
    }
}
