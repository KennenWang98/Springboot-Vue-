package cn.net.sybt.springboot.vo;

import cn.net.sybt.springboot.bean.TeaJudge;

public class TeaJudgeVO extends TeaUploadQuesVO{
    private TeaJudge teaJudge;

    public TeaJudge getTeaJudge() {
        return teaJudge;
    }

    public void setTeaJudge(TeaJudge teaJudge) {
        this.teaJudge = teaJudge;
    }

    @Override
    public String toString() {
        return "TeaJudgeVO{" +
                "teaJudge=" + teaJudge +
                '}';
    }
}
