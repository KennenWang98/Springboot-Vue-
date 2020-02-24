package cn.net.sybt.springboot.vo;

import java.util.List;

public class DifferedQuesVO {
    private List<TeaQuestionVO> teaQuestionVOs;
    private List<PubVO> pubVOs;

    public List<TeaQuestionVO> getTeaQuestionVOs() {
        return teaQuestionVOs;
    }

    public void setTeaQuestionVOs(List<TeaQuestionVO> teaQuestionVOs) {
        this.teaQuestionVOs = teaQuestionVOs;
    }

    public List<PubVO> getPubVOs() {
        return pubVOs;
    }

    public void setPubVOs(List<PubVO> pubVOs) {
        this.pubVOs = pubVOs;
    }

    @Override
    public String toString() {
        return "DifferedQuesVO{" +
                "teaQuestionVOs=" + teaQuestionVOs +
                ", pubVOs=" + pubVOs +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DifferedQuesVO that = (DifferedQuesVO) o;

        if (teaQuestionVOs != null ? !teaQuestionVOs.equals(that.teaQuestionVOs) : that.teaQuestionVOs != null)
            return false;
        return pubVOs != null ? pubVOs.equals(that.pubVOs) : that.pubVOs == null;
    }

    @Override
    public int hashCode() {
        int result = teaQuestionVOs != null ? teaQuestionVOs.hashCode() : 0;
        result = 31 * result + (pubVOs != null ? pubVOs.hashCode() : 0);
        return result;
    }
}
