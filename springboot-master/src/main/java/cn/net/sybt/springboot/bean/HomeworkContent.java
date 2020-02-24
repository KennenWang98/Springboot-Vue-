package cn.net.sybt.springboot.bean;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class HomeworkContent {
    private Integer hcId;
    private Integer hcQuesId;
    private Integer hcFlag;
    private Integer chId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HomeworkContent that = (HomeworkContent) o;
        return Objects.equals(hcId, that.hcId) &&
                Objects.equals(hcQuesId, that.hcQuesId) &&
                Objects.equals(hcFlag, that.hcFlag) &&
                Objects.equals(chId, that.chId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hcId, hcQuesId, hcFlag, chId);
    }

    public Integer getHcId() {
        return hcId;
    }

    public void setHcId(Integer hcId) {
        this.hcId = hcId;
    }

    public Integer getHcQuesId() {
        return hcQuesId;
    }

    public void setHcQuesId(Integer hcQuesId) {
        this.hcQuesId = hcQuesId;
    }

    public Integer getHcFlag() {
        return hcFlag;
    }

    public void setHcFlag(Integer hcFlag) {
        this.hcFlag = hcFlag;
    }

    public Integer getChId() {
        return chId;
    }

    public void setChId(Integer chId) {
        this.chId = chId;
    }

    @Override
    public String toString() {
        return "homeworkContent{" +
                "hcId=" + hcId +
                ", hcQuesId=" + hcQuesId +
                ", hcFlag=" + hcFlag +
                ", chId=" + chId +
                '}';
    }
}
