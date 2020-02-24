package cn.net.sybt.springboot.bean;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TeaSortQuestion extends TeaQuestion{
    private String quesContent;


    public Integer getQuesId() {
        return quesId;
    }

    public void setQuesId(Integer quesId) {
        this.quesId = quesId;
    }

    public String getQuesContent() {
        return quesContent;
    }

    public void setQuesContent(String quesContent) {
        this.quesContent = quesContent;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeaSortQuestion that = (TeaSortQuestion) o;
        return Objects.equals(quesContent, that.quesContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quesContent);
    }

    @Override
    public String toString() {
        return "TeaSortQuestion{" +
                "quesContent='" + quesContent + '\'' +
                ", quesId=" + quesId +
                '}';
    }
}
