package cn.net.sybt.springboot.vo;

import cn.net.sybt.springboot.bean.AnswerContent;
import cn.net.sybt.springboot.bean.StuAnswer;

import java.util.List;
import java.util.Objects;

public class QuesWithStuAnswerVO {
    private QuestionVO questionAndSolutions;
    private AnswerContent answerContent;
    private List<StuAnswer> answer;

    public QuestionVO getQuestionAndSolutions() {
        return questionAndSolutions;
    }

    public void setQuestionAndSolutions(QuestionVO questionAndSolutions) {
        this.questionAndSolutions = questionAndSolutions;
    }

    public AnswerContent getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(AnswerContent answerContent) {
        this.answerContent = answerContent;
    }

    public List<StuAnswer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<StuAnswer> answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuesWithStuAnswerVO that = (QuesWithStuAnswerVO) o;

        if (!Objects.equals(questionAndSolutions, that.questionAndSolutions)) return false;
        if (!Objects.equals(answerContent, that.answerContent))
            return false;
        return Objects.equals(answer, that.answer);
    }

    @Override
    public int hashCode() {
        int result = questionAndSolutions != null ? questionAndSolutions.hashCode() : 0;
        result = 31 * result + (answerContent != null ? answerContent.hashCode() : 0);
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "QuesWithStuAnswerVO{" +
                "question=" + questionAndSolutions +
                ", answerContent=" + answerContent +
                ", answer=" + answer +
                '}';
    }
}
