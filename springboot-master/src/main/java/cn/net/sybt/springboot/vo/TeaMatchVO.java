package cn.net.sybt.springboot.vo;

import cn.net.sybt.springboot.bean.TeaMatchQuestion;
import cn.net.sybt.springboot.bean.TeaMatchSolution;

import java.util.ArrayList;
import java.util.List;

public class TeaMatchVO extends TeaUploadQuesVO{
    private TeaMatchQuestion teaMatchQuestion = new TeaMatchQuestion();
    private List<TeaMatchSolution> teaMatchSolutions = new ArrayList<>();

    public TeaMatchQuestion getTeaMatchQuestion() {
        return teaMatchQuestion;
    }

    public void setTeaMatchQuestion(TeaMatchQuestion teaMatchQuestion) {
        this.teaMatchQuestion = teaMatchQuestion;
    }

    public List<TeaMatchSolution> getTeaMatchSolutions() {
        return teaMatchSolutions;
    }

    public void setTeaMatchSolutions(List<TeaMatchSolution> teaMatchSolutions) {
        this.teaMatchSolutions = teaMatchSolutions;
    }

    @Override
    public String toString() {
        return "TeaMatchVO{" +
                "teaMatchQuestion=" + teaMatchQuestion +
                ", teaMatchSolution=" + teaMatchSolutions +
                '}';
    }
}
