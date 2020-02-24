package cn.net.sybt.springboot.bean;

public abstract class TeaQuestion {
    protected Integer quesId;

    public Integer getQuesId() {
        return quesId;
    }

    public void setQuesId(Integer quesId) {
        this.quesId = quesId;
    }


    @Override
    public String toString() {
        return "TeaQuestion{" +
                "quesId=" + quesId +
                '}';
    }
}
