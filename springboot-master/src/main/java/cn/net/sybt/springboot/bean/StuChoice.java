package cn.net.sybt.springboot.bean;


import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class StuChoice extends StuAnswer {
    private String stuAnswer;
    private Integer isTrue;

    public Integer getIsTrue() {
        return isTrue;
    }

    public void setIsTrue(Integer isTrue) {
        this.isTrue = isTrue;
    }

    public String getStuAnswer() {
        return stuAnswer;
    }

    public void setStuAnswer(String stuAnswer) {
        this.stuAnswer = stuAnswer;
    }

    @Override
    public String toString() {
        return "StuChoice{" +
                "stuAnswer='" + stuAnswer + '\'' +
                ", isTrue=" + isTrue +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StuChoice stuChoice = (StuChoice) o;

        if (!Objects.equals(stuAnswer, stuChoice.stuAnswer)) return false;
        return Objects.equals(isTrue, stuChoice.isTrue);
    }

    @Override
    public int hashCode() {
        int result = stuAnswer != null ? stuAnswer.hashCode() : 0;
        result = 31 * result + (isTrue != null ? isTrue.hashCode() : 0);
        return result;
    }
}
