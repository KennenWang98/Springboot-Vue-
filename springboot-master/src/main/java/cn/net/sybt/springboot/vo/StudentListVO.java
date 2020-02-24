package cn.net.sybt.springboot.vo;

import cn.net.sybt.springboot.bean.Student;

public class StudentListVO {
    private Student student;
    private Double pointSum;

    public StudentListVO(Student student, Double pointSum) {
        this.student = student;
        this.pointSum = pointSum;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Double getPointSum() {
        return pointSum;
    }

    public void setPointSum(Double pointSum) {
        this.pointSum = pointSum;
    }

    @Override
    public String toString() {
        return "StudentListVO{" +
                "student=" + student +
                ", pointSum=" + pointSum +
                '}';
    }
}
