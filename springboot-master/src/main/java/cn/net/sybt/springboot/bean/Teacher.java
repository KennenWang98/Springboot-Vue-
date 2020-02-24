package cn.net.sybt.springboot.bean;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Teacher { //老师
    private Integer teaId;
    private String teaName;
    private String teaPassword;
    private String phoneNumber;
    private String teaIcon;
    private String gender;

    public Integer getTeaId() {
        return teaId;
    }

    public void setTeaId(Integer teaId) {
        this.teaId = teaId;
    }

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName;
    }

    public String getTeaPassword() {
        return teaPassword;
    }

    public void setTeaPassword(String teaPassword) {
        this.teaPassword = teaPassword;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTeaIcon() {
        return teaIcon;
    }

    public void setTeaIcon(String teaIcon) {
        this.teaIcon = teaIcon;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Teacher() {
    }

    public Teacher(Integer teaId, String teaName, String teaPassword, String phoneNumber, String teaIcon) {
        this.teaId = teaId;
        this.teaName = teaName;
        this.teaPassword = teaPassword;
        this.phoneNumber = phoneNumber;
        this.teaIcon = teaIcon;
    }

    public Teacher(String teaName, String phoneNumber, String teaIcon, String gender) {
        this.teaName = teaName;
        this.phoneNumber = phoneNumber;
        this.teaIcon = teaIcon;
        this.gender = gender;
    }

    public Teacher(Integer teaId, String teaName, String teaPassword, String phoneNumber, String teaIcon, String gender) {
        this.teaId = teaId;
        this.teaName = teaName;
        this.teaPassword = teaPassword;
        this.phoneNumber = phoneNumber;
        this.teaIcon = teaIcon;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teaId=" + teaId +
                ", teaName='" + teaName + '\'' +
                ", teaPassword='" + teaPassword + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", teaIcon='" + teaIcon + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(teaId, teacher.teaId) &&
                Objects.equals(teaName, teacher.teaName) &&
                Objects.equals(teaPassword, teacher.teaPassword) &&
                Objects.equals(phoneNumber, teacher.phoneNumber) &&
                Objects.equals(teaIcon, teacher.teaIcon) &&
                Objects.equals(gender, teacher.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teaId, teaName, teaPassword, phoneNumber, teaIcon, gender);
    }
}
