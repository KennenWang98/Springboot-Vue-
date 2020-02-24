package cn.net.sybt.springboot.bean;

import org.springframework.stereotype.Component;

@Component
public class Clazz { //班级
    private Integer classId;
    private String className;
    private String classText;
    private Integer schoolId;
    private String classQrCode;
    private String classIcon;
    private String code; //许可码
    private Integer teaId;

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassText() {
        return classText;
    }

    public void setClassText(String classText) {
        this.classText = classText;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getClassQrCode() {
        return classQrCode;
    }

    public void setClassQrCode(String classQrCode) {
        this.classQrCode = classQrCode;
    }

    public String getClassIcon() {
        return classIcon;
    }

    public void setClassIcon(String classIcon) {
        this.classIcon = classIcon;
    }

    public Integer getTeaId() {
        return teaId;
    }

    public void setTeaId(Integer teaId) {
        this.teaId = teaId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Clazz{" +
                "classId=" + classId +
                ", className='" + className + '\'' +
                ", classText='" + classText + '\'' +
                ", schoolId=" + schoolId +
                ", classQrCode='" + classQrCode + '\'' +
                ", classIcon='" + classIcon + '\'' +
                ", code='" + code + '\'' +
                ", teaId=" + teaId +
                '}';
    }

    public Clazz() {
    }

    public Clazz(Integer classId, String className, String classText, Integer schoolId,
                 String classQrCode, String classIcon, String code, Integer teaId) {
        this.classId = classId;
        this.className = className;
        this.classText = classText;
        this.schoolId = schoolId;
        this.classQrCode = classQrCode;
        this.classIcon = classIcon;
        this.code = code;
        this.teaId = teaId;
    }
}
