package cn.net.sybt.springboot.bean;

import org.springframework.stereotype.Component;

@Component
public class Student { //学生
    private Integer stuId;
    private String stuName;
    private String stuPassword;
    private String phoneNumber;
    private String stuIcon;
    private String pinyin; //拼音
    private String gender;

    public Student() {
    }

    public Student(Integer stuId, String stuName, String stuPassword, String phoneNumber, String stuIcon, String pinyin) {
        this.stuId = stuId;
        this.stuName = stuName;
        this.stuPassword = stuPassword;
        this.phoneNumber = phoneNumber;
        this.stuIcon = stuIcon;
        this.pinyin = pinyin;
    }

    public Student(String stuName, String phoneNumber, String stuIcon, String pinyin, String gender) {
        this.stuName = stuName;
        this.phoneNumber = phoneNumber;
        this.stuIcon = stuIcon;
        this.pinyin = pinyin;
        this.gender = gender;
    }

    public Student(Integer stuId, String stuName, String stuPassword, String phoneNumber, String stuIcon, String pinyin, String gender) {
        this.stuId = stuId;
        this.stuName = stuName;
        this.stuPassword = stuPassword;
        this.phoneNumber = phoneNumber;
        this.stuIcon = stuIcon;
        this.pinyin = pinyin;
        this.gender = gender;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuPassword() {
        return stuPassword;
    }

    public void setStuPassword(String stuPassword) {
        this.stuPassword = stuPassword;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStuIcon() {
        return stuIcon;
    }

    public void setStuIcon(String stuIcon) {
        this.stuIcon = stuIcon;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuId=" + stuId +
                ", stuName='" + stuName + '\'' +
                ", stuPassword='" + stuPassword + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", stuIcon='" + stuIcon + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
