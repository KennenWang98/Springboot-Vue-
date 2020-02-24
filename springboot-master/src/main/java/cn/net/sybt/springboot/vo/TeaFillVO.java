package cn.net.sybt.springboot.vo;

import cn.net.sybt.springboot.bean.TeaFillQuestion;
import cn.net.sybt.springboot.bean.TeaFillSolution;

import java.util.ArrayList;
import java.util.List;

public class TeaFillVO extends TeaUploadQuesVO{
    private TeaFillQuestion teaFillQuestion = new TeaFillQuestion();
    private List<TeaFillSolution> teaFillSolutions = new ArrayList<>();

    public TeaFillQuestion getTeaFillQuestion() {
        return teaFillQuestion;
    }

    public void setTeaFillQuestion(TeaFillQuestion teaFillQuestion) {
        this.teaFillQuestion = teaFillQuestion;
    }

    public List<TeaFillSolution> getTeaFillSolutions() {
        return teaFillSolutions;
    }

    public void setTeaFillSolutions(List<TeaFillSolution> teaFillSolutions) {
        this.teaFillSolutions = teaFillSolutions;
    }

    @Override
    public String toString() {
        return "TeaFillVO{" +
                "teaFillQuestion=" + teaFillQuestion +
                ", teaFillSolution=" + teaFillSolutions +
                '}';
    }
}
