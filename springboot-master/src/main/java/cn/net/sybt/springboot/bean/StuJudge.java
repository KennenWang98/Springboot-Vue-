package cn.net.sybt.springboot.bean;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class StuJudge extends StuAnswer {
    private Integer stuAnswer;
    private Integer isTrue;


    public Integer getIsTrue() {
        return isTrue;
    }

    public void setIsTrue(Integer isTrue) {
        this.isTrue = isTrue;
    }

    public Integer getStuAnswer() {
        return stuAnswer;
    }

    public void setStuAnswer(Integer stuAnswer) {
        this.stuAnswer = stuAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StuJudge stuJudge = (StuJudge) o;

        if (!Objects.equals(stuAnswer, stuJudge.stuAnswer)) return false;
        return Objects.equals(isTrue, stuJudge.isTrue);
    }

    @Override
    public int hashCode() {
        int result = stuAnswer != null ? stuAnswer.hashCode() : 0;
        result = 31 * result + (isTrue != null ? isTrue.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StuJudge{" +
                "stuAnswer=" + stuAnswer +
                ", isTrue=" + isTrue +
                "} " + super.toString();
    }
}
