package cn.net.sybt.springboot.bean;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
public class Moment {
    private Integer classId;
    private String pictureLink;
    private Date uploadTime;
    private String phoneNumber;

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Moment() {
    }

    public Moment(Integer classId, String pictureLink, Date uploadTime, String phoneNumber) {
        this.classId = classId;
        this.pictureLink = pictureLink;
        this.uploadTime = uploadTime;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Moment moment = (Moment) o;
        return Objects.equals(classId, moment.classId) &&
                Objects.equals(pictureLink, moment.pictureLink) &&
                Objects.equals(uploadTime, moment.uploadTime) &&
                Objects.equals(phoneNumber, moment.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classId, pictureLink, uploadTime, phoneNumber);
    }

    @Override
    public String toString() {
        return "Moment{" +
                "classId=" + classId +
                ", pictureLink='" + pictureLink + '\'' +
                ", uploadTime=" + uploadTime +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

