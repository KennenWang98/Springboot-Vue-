package cn.net.sybt.springboot.bean;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class StuMatch {
    private Integer acId;
    private String leftValue;
    private String leftLink;
    private String rightValue;
    private String rightLink;
    private Integer isTrue;

    public Integer getAcId() {
        return acId;
    }

    public void setAcId(Integer acId) {
        this.acId = acId;
    }

    public String getLeftValue() {
        return leftValue;
    }

    public void setLeftValue(String leftValue) {
        this.leftValue = leftValue;
    }

    public String getLeftLink() {
        return leftLink;
    }

    public void setLeftLink(String leftLink) {
        this.leftLink = leftLink;
    }

    public String getRightValue() {
        return rightValue;
    }

    public void setRightValue(String rightValue) {
        this.rightValue = rightValue;
    }

    public String getRightLink() {
        return rightLink;
    }

    public void setRightLink(String rightLink) {
        this.rightLink = rightLink;
    }

    public Integer getIsTrue() {
        return isTrue;
    }

    public void setIsTrue(Integer isTrue) {
        this.isTrue = isTrue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StuMatch stuMatch = (StuMatch) o;
        return Objects.equals(acId, stuMatch.acId) &&
                Objects.equals(leftValue, stuMatch.leftValue) &&
                Objects.equals(leftLink, stuMatch.leftLink) &&
                Objects.equals(rightValue, stuMatch.rightValue) &&
                Objects.equals(rightLink, stuMatch.rightLink) &&
                Objects.equals(isTrue, stuMatch.isTrue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(acId, leftValue, leftLink, rightValue, rightLink, isTrue);
    }

    @Override
    public String toString() {
        return "StuMatch{" +
                "acId=" + acId +
                ", leftValue='" + leftValue + '\'' +
                ", leftLink='" + leftLink + '\'' +
                ", rightValue='" + rightValue + '\'' +
                ", rightLink='" + rightLink + '\'' +
                ", isTrue=" + isTrue +
                '}';
    }
}
