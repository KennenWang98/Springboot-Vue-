package cn.net.sybt.springboot.vo;

import cn.net.sybt.springboot.bean.Student;

public class StudentListWithPerVO {
    Student student;
    String percentage;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "StudentListWithPerVO{" +
                "student=" + student +
                ", percentage='" + percentage + '\'' +
                '}';
    }
}
