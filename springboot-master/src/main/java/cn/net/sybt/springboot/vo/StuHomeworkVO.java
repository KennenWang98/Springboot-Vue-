package cn.net.sybt.springboot.vo;

import cn.net.sybt.springboot.bean.Answer;

import java.util.List;

public class StuHomeworkVO {
    Answer answer;
    List<QuesWithStuAnswerVO> quesWithStuAnswerVOs;

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public List<QuesWithStuAnswerVO> getQuesWithStuAnswerVOs() {
        return quesWithStuAnswerVOs;
    }

    public void setQuesWithStuAnswerVOs(List<QuesWithStuAnswerVO> quesWithStuAnswerVOs) {
        this.quesWithStuAnswerVOs = quesWithStuAnswerVOs;
    }

    @Override
    public String toString() {
        return "StuHomework{" +
                "answer=" + answer +
                ", quesWithStuAnswerVOs=" + quesWithStuAnswerVOs +
                '}';
    }
}
