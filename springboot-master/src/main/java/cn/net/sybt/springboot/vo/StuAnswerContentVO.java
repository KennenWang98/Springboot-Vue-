package cn.net.sybt.springboot.vo;


import java.util.List;
import java.util.Objects;

public class StuAnswerContentVO {
    private Integer hcId;
    private String quesType;
    private List<?> objects;

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

    public List<?> getObjects() {
        return objects;
    }

    public void setObjects(List<Object> objects) {
        this.objects = objects;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StuAnswerContentVO that = (StuAnswerContentVO) o;

        if (!Objects.equals(hcId, that.hcId)) return false;
        if (!Objects.equals(quesType, that.quesType)) return false;
        return Objects.equals(objects, that.objects);
    }

    @Override
    public int hashCode() {
        int result = hcId != null ? hcId.hashCode() : 0;
        result = 31 * result + (quesType != null ? quesType.hashCode() : 0);
        result = 31 * result + (objects != null ? objects.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StuAnswerContentVO{" +
                "hcId=" + hcId +
                ", quesType=" + quesType +
                ", stuAnswers=" + objects +
                '}';
    }
}
