package cn.net.sybt.springboot.vo;

public class HomeworkContentVO {//这个VO封装了发布作业时前端给我返回的每到题的ID和flag
    private Integer flag;
    private Integer quesId;

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getQuesId() {
        return quesId;
    }

    public void setQuesId(Integer quesId) {
        this.quesId = quesId;
    }

    @Override
    public String toString() {
        return "HomeworkContentVO{" +
                "flag=" + flag +
                ", quesId=" + quesId +
                '}';
    }
}
