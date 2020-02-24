package cn.net.sybt.springboot.vo;

import cn.net.sybt.springboot.bean.TeaSortQuestion;
import cn.net.sybt.springboot.bean.TeaSortSolution;

import java.util.ArrayList;
import java.util.List;

public class TeaSortVO extends TeaUploadQuesVO{
    private TeaSortQuestion teaSortQuestion = new TeaSortQuestion();
    private List<TeaSortSolution> teaSortSolutions = new ArrayList<>();

    public TeaSortQuestion getTeaSortQuestion() {
        return teaSortQuestion;
    }

    public void setTeaSortQuestion(TeaSortQuestion teaSortQuestion) {
        this.teaSortQuestion = teaSortQuestion;
    }

    public List<TeaSortSolution> getTeaSortSolutions() {
        return teaSortSolutions;
    }

    public void setTeaSortSolutions(List<TeaSortSolution> teaSortSolutions) {
        this.teaSortSolutions = teaSortSolutions;
    }

    @Override
    public String toString() {
        return "TeaSortVo{" +
                "teaSortQuestion=" + teaSortQuestion +
                ", teaSortSolution=" + teaSortSolutions +
                '}';
    }
}
