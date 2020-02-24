package cn.net.sybt.springboot.controller;


import cn.net.sybt.springboot.service.HomeworkService;
import cn.net.sybt.springboot.utils.ResultEnum;
import cn.net.sybt.springboot.utils.ResultModel;
import cn.net.sybt.springboot.utils.RetResponse;
import cn.net.sybt.springboot.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/homework")
public class HomeworkController {
    @Autowired
    HomeworkService homeworkService;

    @RequestMapping("/get-class-homework")
    public ResultModel<List<ClassHomeworkVO>> getClassHomework(Integer teaId, Integer classId) {
        return RetResponse.okResp(homeworkService.getClassHomework(teaId, classId));
    }

    @RequestMapping("/get-homework-ques")
    public ResultModel<List<QuestionVO>> getHomeworkQues(Integer chId) {
        if (homeworkService.getHomeworkQues(chId).size() == 0)
            return RetResponse.resp(ResultEnum.GET_HOMEWORK_QUESTION_ERROR.code, ResultEnum.GET_HOMEWORK_QUESTION_ERROR.msg);
        return RetResponse.okResp(homeworkService.getHomeworkQues(chId));
    }

    @RequestMapping("/get-release-ques")
    public ResultModel<DifferedQuesVO> getReleaseQues(Integer teacherId, Integer clazzId) {
        DifferedQuesVO result = homeworkService.getReleaseQues(teacherId, clazzId);
        if (result.getTeaQuestionVOs().size() == 0 && result.getTeaQuestionVOs().size() != 0)
            return RetResponse.resp(ResultEnum.NOT_FOUND.code, "未找到老师题库", result);
        else if (result.getTeaQuestionVOs().size() != 0 && result.getTeaQuestionVOs().size() == 0)
            return RetResponse.resp(ResultEnum.NOT_FOUND.code, "未找到出版社题库", result);
        else if (result.getTeaQuestionVOs().size() == 0)
            return RetResponse.okResp(result);
        else
            return RetResponse.okResp(result);
    }

    @RequestMapping("/release-homework")
    public ResultModel<List<ClassHomeworkVO>> releaseHomework(@RequestBody ReleaseHomeworkVO releaseHomeworkVO) {
        List<ClassHomeworkVO> result = homeworkService.releaseHomework(releaseHomeworkVO);
        if (result == null)
            return RetResponse.resp(ResultEnum.NOT_FOUND.code, "题库中没有部分题目");
        return RetResponse.okResp(result);
    }

    @RequestMapping("/upload-stu-answer")
    public ResultModel<StuHomeworkVO> uploadStuAnswer(@RequestBody StuAnswerVO stuAnswerVO) {
        StuHomeworkVO result = homeworkService.uploadStuAnswer(stuAnswerVO);
        if (result == null)
            return RetResponse.resp(ResultEnum.ANSWER_EXIST.code, ResultEnum.ANSWER_EXIST.msg);
        return RetResponse.okResp(result);
    }

    @RequestMapping("/student-list-with-percentage")
    public ResultModel<List<StudentListWithPerVO>> studentListWithPercentage(Integer classId, Integer chId) {
        List<StudentListWithPerVO> result = homeworkService.studentListWithPercentage(classId, chId);
        if (result == null)
            return RetResponse.resp(ResultEnum.NOT_FOUND_STUDENT.code, ResultEnum.NOT_FOUND_STUDENT.msg);
        else
            return RetResponse.okResp(result);
    }

    @RequestMapping("/get-ques-with-stu-answer-list")
    public ResultModel<StuHomeworkVO> getQuesWithStuAnswerList(Integer chId, Integer stuId) {
        StuHomeworkVO result = homeworkService.getQuesWithStuAnswerList(chId, stuId);
        if (result == null)
            return RetResponse.resp(ResultEnum.NOT_FOUND.code, "学生未上传作答或该作业不存在！");
        else
            return RetResponse.okResp(result);
    }

    @RequestMapping("/delete-homework")
    public ResultModel<List<ClassHomeworkVO>> deleteHomework(Integer chId) {
        List<ClassHomeworkVO> result = homeworkService.deleteHomework(chId);
        if (result == null)
            return RetResponse.resp(ResultEnum.NOT_FOUND.code, "没有找到该次作业");
        return RetResponse.okResp(result);
    }

    @RequestMapping("/update-feedback-and-point")
    public ResultModel updateFeedbackAndPoint(@RequestBody List<FeedbackAndPointVO> feedbackAndPointVO) {
        Boolean result = homeworkService.updateFeedbackAndPoint(feedbackAndPointVO);
        if (result)
            return RetResponse.okResp();
        else
            return RetResponse.resp(ResultEnum.NOT_FOUND.code, "找不到部分作答信息");
    }

    @RequestMapping("/get-student-list-with-total-point")
    public ResultModel<List<StudentListVO>> getStudentListWithTotalPoint(Integer classId) {
        return RetResponse.okResp(homeworkService.getStudentListWithTotalPoint(classId));
    }

}
