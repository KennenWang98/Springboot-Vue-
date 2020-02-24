package cn.net.sybt.springboot.vo;

import cn.net.sybt.springboot.bean.TeaChoiceQuestion;
import cn.net.sybt.springboot.bean.TeaChoiceSolution;

import java.util.ArrayList;
import java.util.List;

public class TeaChoiceVO extends TeaUploadQuesVO {
    private TeaChoiceQuestion teaChoiceQuestion = new TeaChoiceQuestion();
    private List<TeaChoiceSolution> teaChoiceSolutions = new ArrayList<>();

    public TeaChoiceQuestion getTeaChoiceQuestion() {
        return teaChoiceQuestion;
    }

    public void setTeaChoiceQuestion(TeaChoiceQuestion teaChoiceQuestion) {
        this.teaChoiceQuestion = teaChoiceQuestion;
    }

    public List<TeaChoiceSolution> getTeaChoiceSolutions() {
        return teaChoiceSolutions;
    }

    public void setTeaChoiceSolutions(List<TeaChoiceSolution> teaChoiceSolutions) {
        this.teaChoiceSolutions = teaChoiceSolutions;
    }

    @Override
    public String toString() {
        return "TeaChoiceVo{" +
                "teaChoiceQuestion=" + teaChoiceQuestion +
                ", teaChoiceSolutions=" + teaChoiceSolutions +
                '}';
    }
}
