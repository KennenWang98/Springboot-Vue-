package cn.net.sybt.springboot.vo;

public class FeedbackAndPointVO {
    private Integer chId;
    private Integer acId;
    private String feedback;
    private Double point;

    public Integer getAcId() {
        return acId;
    }

    public void setAcId(Integer acId) {
        this.acId = acId;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Double getPoint() {
        return point;
    }

    public void setPoint(Double point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "FeedbackAndPointVO{" +
                "acId=" + acId +
                ", feedback='" + feedback + '\'' +
                ", point=" + point +
                '}';
    }

    public Integer getChId() {
        return chId;
    }

    public void setChId(Integer chId) {
        this.chId = chId;
    }
}
