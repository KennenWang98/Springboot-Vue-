package cn.net.sybt.springboot.bean;

import org.springframework.stereotype.Component;

@Component
public class TeaDocument { //教师自己在班级里上传的资料
    private Integer teaDocuId;
    private String docuPath;
    private String docuType;
    private Integer teaId;
    private String docuName;
    private Integer classId;

    public Integer getTeaDocuId() {
        return teaDocuId;
    }

    public void setTeaDocuId(Integer teaDocuId) {
        this.teaDocuId = teaDocuId;
    }

    public String getDocuPath() {
        return docuPath;
    }

    public void setDocuPath(String docuPath) {
        this.docuPath = docuPath;
    }

    public String getDocuType() {
        return docuType;
    }

    public void setDocuType(String docuType) {
        this.docuType = docuType;
    }

    public Integer getTeaId() {
        return teaId;
    }

    public void setTeaId(Integer teaId) {
        this.teaId = teaId;
    }

    public String getDocuName() {
        return docuName;
    }

    public void setDocuName(String docuName) {
        this.docuName = docuName;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    @Override
    public String toString() {
        return "TeaDocument{" +
                "teaDocuId=" + teaDocuId +
                ", docuPath='" + docuPath + '\'' +
                ", docuType='" + docuType + '\'' +
                ", teaId=" + teaId +
                ", docuName='" + docuName + '\'' +
                ", classId=" + classId +
                '}';
    }

    public TeaDocument() {
    }

    public TeaDocument(Integer teaDocuId, String docuPath, String docuType, Integer teaId, String docuName, Integer classId) {
        this.teaDocuId = teaDocuId;
        this.docuPath = docuPath;
        this.docuType = docuType;
        this.teaId = teaId;
        this.docuName = docuName;
        this.classId = classId;
    }
}
