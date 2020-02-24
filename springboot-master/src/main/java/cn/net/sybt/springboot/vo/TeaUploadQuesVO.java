package cn.net.sybt.springboot.vo;


import cn.net.sybt.springboot.bean.TeacherQuestions;


public class TeaUploadQuesVO {
    private TeacherQuestions teacherQuestions;

    public TeacherQuestions getTeacherQuestions() {
        return teacherQuestions;
    }

    public void setTeacherQuestions(TeacherQuestions teacherQuestions) {
        this.teacherQuestions = teacherQuestions;
    }

    @Override
    public String toString() {
        return "TeaUploadQuesVO{" +
                "teacherQuestions=" + teacherQuestions +
                '}';
    }
}
