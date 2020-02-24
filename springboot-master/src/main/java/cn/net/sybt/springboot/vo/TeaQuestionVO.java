package cn.net.sybt.springboot.vo;

import cn.net.sybt.springboot.bean.TeaQuestion;
import cn.net.sybt.springboot.bean.TeaSolution;

import java.util.Objects;


public class TeaQuestionVO extends QuestionVO<TeaQuestion, TeaSolution> {
    private Integer teaId;
    private Integer classId;
    private Integer quesAllId;

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

    public Integer getQuesAllId() {
        return quesAllId;
    }

    public void setQuesAllId(Integer quesAllId) {
        this.quesAllId = quesAllId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TeaQuestionVO that = (TeaQuestionVO) o;

        if (!Objects.equals(teaId, that.teaId)) return false;
        if (!Objects.equals(classId, that.classId)) return false;
        return Objects.equals(quesAllId, that.quesAllId);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (teaId != null ? teaId.hashCode() : 0);
        result = 31 * result + (classId != null ? classId.hashCode() : 0);
        result = 31 * result + (quesAllId != null ? quesAllId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TeaQuestionVO{" +
                "teaId=" + teaId +
                ", classId=" + classId +
                ", quesAllId=" + quesAllId +
                "} " + super.toString();
    }
}
