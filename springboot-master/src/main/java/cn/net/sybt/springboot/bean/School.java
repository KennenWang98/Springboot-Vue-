package cn.net.sybt.springboot.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import org.springframework.stereotype.Component;

@Component
public class School { //学校
    @TableId(type = IdType.AUTO)
    private Integer schoolId;
    private String schoolName;
    private String principal;
    private String schoolIcon;
    private String schoolText;

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getSchoolIcon() {
        return schoolIcon;
    }

    public void setSchoolIcon(String schoolIcon) {
        this.schoolIcon = schoolIcon;
    }

    public String getSchoolText() {
        return schoolText;
    }

    public void setSchoolText(String schoolText) {
        this.schoolText = schoolText;
    }

    @Override
    public String toString() {
        return "School{" +
                "schoolId=" + schoolId +
                ", schoolName='" + schoolName + '\'' +
                ", principal='" + principal + '\'' +
                ", schoolIcon='" + schoolIcon + '\'' +
                ", schoolText='" + schoolText + '\'' +
                '}';
    }

    public School() {
    }

    public School(Integer schoolId, String schoolName, String principal, String schoolIcon, String schoolText) {
        this.schoolId = schoolId;
        this.schoolName = schoolName;
        this.principal = principal;
        this.schoolIcon = schoolIcon;
        this.schoolText = schoolText;
    }
}
