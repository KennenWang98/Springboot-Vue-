package cn.net.sybt.springboot.bean;


import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class StuEssay extends StuAnswer {
    private String answerEssay;

    public String getAnswerEssay() {
        return answerEssay;
    }

    public void setAnswerEssay(String answerEssay) {
        this.answerEssay = answerEssay;
    }

    @Override
    public String toString() {
        return "StuEssay{" +
                "answerEssay='" + answerEssay + '\'' +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StuEssay stuEssay = (StuEssay) o;

        return Objects.equals(answerEssay, stuEssay.answerEssay);
    }

    @Override
    public int hashCode() {
        return answerEssay != null ? answerEssay.hashCode() : 0;
    }
}
