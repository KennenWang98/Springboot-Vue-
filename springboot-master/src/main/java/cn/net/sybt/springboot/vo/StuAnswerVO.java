package cn.net.sybt.springboot.vo;

import cn.net.sybt.springboot.bean.Answer;

import java.util.List;
import java.util.Objects;

public class StuAnswerVO {
    private Answer answer;
    private List<StuAnswerContentVO> stuAnswerContentVOs;
    private String stuPhoneNumber;

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public List<StuAnswerContentVO> getStuAnswerContentVOs() {
        return stuAnswerContentVOs;
    }

    public void setStuAnswerContentVOs(List<StuAnswerContentVO> stuAnswerContentVOs) {
        this.stuAnswerContentVOs = stuAnswerContentVOs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StuAnswerVO that = (StuAnswerVO) o;

        if (!Objects.equals(answer, that.answer)) return false;
        return Objects.equals(stuAnswerContentVOs, that.stuAnswerContentVOs);
    }

    @Override
    public int hashCode() {
        int result = answer != null ? answer.hashCode() : 0;
        result = 31 * result + (stuAnswerContentVOs != null ? stuAnswerContentVOs.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StuAnswerVO{" +
                "answer=" + answer +
                ", stuAnswerContentVOs=" + stuAnswerContentVOs +
                '}';
    }

    public String getStuPhoneNumber() {
        return stuPhoneNumber;
    }

    public void setStuPhoneNumber(String stuPhoneNumber) {
        this.stuPhoneNumber = stuPhoneNumber;
    }
}
