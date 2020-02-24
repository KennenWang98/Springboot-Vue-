package cn.net.sybt.springboot.bean;

public abstract class StuAnswer {
    private Integer acId;

    public Integer getAcId() {
        return acId;
    }

    public void setAcId(Integer acId) {
        this.acId = acId;
    }

    @Override
    public String toString() {
        return "StuAnswer{" +
                "acId=" + acId +
                '}';
    }
}
