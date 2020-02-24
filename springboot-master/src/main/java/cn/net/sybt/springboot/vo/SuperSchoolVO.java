package cn.net.sybt.springboot.vo;

import cn.net.sybt.springboot.bean.School;

import java.util.Objects;

public class SuperSchoolVO extends School {
    private Integer classNum; //班级数量
    private Integer teacherNum; //老师数量
    private Integer studentNum; //学生数量
    private Integer codeNum; //许可码数量

    public Integer getClassNum() {
        return classNum;
    }

    public void setClassNum(Integer classNum) {
        this.classNum = classNum;
    }

    public Integer getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(Integer studentNum) {
        this.studentNum = studentNum;
    }

    public Integer getTeacherNum() {
        return teacherNum;
    }

    public void setTeacherNum(Integer teacherNum) {
        this.teacherNum = teacherNum;
    }

    public Integer getCodeNum() {
        return codeNum;
    }

    public void setCodeNum(Integer codeNum) {
        this.codeNum = codeNum;
    }

    public SuperSchoolVO(Integer classNum, Integer teacherNum, Integer studentNum, Integer codeNum) {
        this.classNum = classNum;
        this.teacherNum = teacherNum;
        this.studentNum = studentNum;
        this.codeNum = codeNum;
    }

    public SuperSchoolVO(School school, Integer classNum, Integer teacherNum, Integer studentNum, Integer codeNum) {
        super(school.getSchoolId(), school.getSchoolName(), school.getPrincipal(),
                school.getSchoolIcon(), school.getSchoolText());
        this.classNum = classNum;
        this.teacherNum = teacherNum;
        this.studentNum = studentNum;
        this.codeNum = codeNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SuperSchoolVO schoolVO = (SuperSchoolVO) o;
        return Objects.equals(classNum, schoolVO.classNum) &&
                Objects.equals(teacherNum, schoolVO.teacherNum) &&
                Objects.equals(studentNum, schoolVO.studentNum) &&
                Objects.equals(codeNum, schoolVO.codeNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classNum, teacherNum, studentNum, codeNum);
    }

    @Override
    public String toString() {
        return "SuperSchoolVO{" +
                "classNum=" + classNum +
                ", teacherNum=" + teacherNum +
                ", studentNum=" + studentNum +
                ", codeNum=" + codeNum +
                '}';
    }
}
