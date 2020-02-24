package cn.net.sybt.springboot.vo;

import cn.net.sybt.springboot.bean.ClassHomework;

public class ClassHomeworkVO {
    private ClassHomework classHomework;
    private Double percentage;
    private String layoutTimeString;
    private String deadlineString;
    private String status;

    public ClassHomeworkVO(ClassHomework classHomework
            , Double percentage, String layoutTimeString
            , String deadlineString, String status) {
        this.classHomework = classHomework;
        this.percentage = percentage;
        this.layoutTimeString = layoutTimeString;
        this.deadlineString = deadlineString;
        this.status = status;
    }


    public ClassHomework getClassHomework() {
        return classHomework;
    }


    public void setClassHomework(ClassHomework classHomework) {
        this.classHomework = classHomework;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public String getLayoutTimeString() {
        return layoutTimeString;
    }

    public void setLayoutTimeString(String layoutTimeString) {
        this.layoutTimeString = layoutTimeString;
    }

    public String getDeadlineString() {
        return deadlineString;
    }

    public void setDeadlineString(String deadlineString) {
        this.deadlineString = deadlineString;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ClassHomeworkVO{" +
                "classHomework=" + classHomework +
                ", percentage=" + percentage +
                ", layoutTimeString='" + layoutTimeString + '\'' +
                ", deadlineString='" + deadlineString + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
