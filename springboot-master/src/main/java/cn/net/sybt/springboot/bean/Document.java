package cn.net.sybt.springboot.bean;

import org.springframework.stereotype.Component;

@Component
public class Document { //E-learning 资料
    private Integer docuId;
    private String docuPath;
    private String docuType;
    private Integer bookId;
    private String docuName;

    public Integer getDocuId() {
        return docuId;
    }

    public void setDocuId(Integer docuId) {
        this.docuId = docuId;
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

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getDocuName() {
        return docuName;
    }

    public void setDocuName(String docuName) {
        this.docuName = docuName;
    }

    @Override
    public String toString() {
        return "Document{" +
                "docuId=" + docuId +
                ", docuPath='" + docuPath + '\'' +
                ", docuType='" + docuType + '\'' +
                ", bookId=" + bookId +
                ", docuName='" + docuName + '\'' +
                '}';
    }

    public Document() {
    }

    public Document(Integer docuId, String docuPath, String docuType, Integer bookId, String docuName) {
        this.docuId = docuId;
        this.docuPath = docuPath;
        this.docuType = docuType;
        this.bookId = bookId;
        this.docuName = docuName;
    }
}
