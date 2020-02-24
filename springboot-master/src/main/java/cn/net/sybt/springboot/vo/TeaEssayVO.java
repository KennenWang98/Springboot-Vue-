package cn.net.sybt.springboot.vo;

import cn.net.sybt.springboot.bean.TeaEssay;

public class TeaEssayVO extends TeaUploadQuesVO {
    private TeaEssay teaEssay;

    public TeaEssay getTeaEssay() {
        return teaEssay;
    }

    public void setTeaEssay(TeaEssay teaEssay) {
        this.teaEssay = teaEssay;
    }

    @Override
    public String toString() {
        return "TeaEssayVO{" +
                "teaEssay=" + teaEssay +
                '}';
    }
}
