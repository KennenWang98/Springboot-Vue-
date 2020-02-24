package cn.net.sybt.springboot.serviceTest;


import cn.net.sybt.springboot.service.HomeworkService;
import cn.net.sybt.springboot.vo.FeedbackAndPointVO;
import cn.net.sybt.springboot.vo.HomeworkContentVO;
import cn.net.sybt.springboot.vo.ReleaseHomeworkVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
public class HomeworkServiceTest {
    @Autowired
    HomeworkService homeworkService;

    @Test
    public void getHomeworkQuesTest() {
        System.out.println(homeworkService.getHomeworkQues(1));
    }

//    @Test
//    public void uploadStuAnswerTest() {
//        StuAnswerVO stuAnswerVO = new StuAnswerVO();
//        Answer answer = new Answer();
//        answer.setChId(1021);
//        answer.setStuId(9626);
//        stuAnswerVO.setAnswer(answer);
//        List<StuAnswerContentVO> stuAnswerContentVOs = new ArrayList<>();
//
//
//        StuAnswerContentVO a = new StuAnswerContentVO();
//        a.setHcId(1432);
//        a.setQuesType("选择题");
//        List<Object> stuChoices = new ArrayList<>();
//        StuChoice stuChoice = new StuChoice();
//        stuChoice.setIsTrue(1);
//        stuChoices.add(stuChoice);
//        a.setObjects(stuChoices);
//        stuAnswerContentVOs.add(a);
//
//        StuAnswerContentVO b = new StuAnswerContentVO();
//        b.setQuesType("填空题");
//        List<Object> stuFills = new ArrayList<>();
//        StuFill stuFill = new StuFill();
//        stuFill.setAnswerNo(1);
//        stuFill.setIsTrue(1);
//        stuFill.setStuAnswer("学生填空题答案");
//        stuFills.add(stuFill);
//        b.setObjects(stuFills);
//        stuAnswerContentVOs.add(b);
//
//        StuAnswerContentVO c = new StuAnswerContentVO();
//        c.setQuesType("作文题");
//        List<Object> stuEssays = new ArrayList<>();
//        StuEssay stuEssay = new StuEssay();
//        stuEssay.setAnswerEssay("测试学生输入的作文");
//        stuEssays.add(stuEssay);
//        c.setObjects(stuEssays);
//        stuAnswerContentVOs.add(c);
//
//        StuAnswerContentVO d = new StuAnswerContentVO();
//        d.setQuesType("判断题");
//        List<Object> stuJudes = new ArrayList<>();
//        StuJudge stuJudge = new StuJudge();
//        stuJudge.setStuAnswer(1);
//        stuJudge.setIsTrue(1);
//        stuJudes.add(stuJudge);
//        d.setObjects(stuJudes);
//        stuAnswerContentVOs.add(d);
//
//        stuAnswerVO.setStuAnswerContentVOs(stuAnswerContentVOs);
//        homeworkService.uploadStuAnswer(stuAnswerVO);
//    }

    @Test
    public void releaseHomeworkTest() {
        ReleaseHomeworkVO releaseHomeworkVO = new ReleaseHomeworkVO();
        releaseHomeworkVO.setClassId(10);
        releaseHomeworkVO.setHomeworkName("测试发布作业");
        releaseHomeworkVO.setSecond(86400);
        releaseHomeworkVO.setTeaId(10);
        List<HomeworkContentVO> homeworkContentVOs = new ArrayList<>();
        HomeworkContentVO a = new HomeworkContentVO();
        a.setFlag(1);
        a.setQuesId(1021);
        HomeworkContentVO b = new HomeworkContentVO();
        b.setFlag(1);
        b.setQuesId(9626);
        homeworkContentVOs.add(a);
        homeworkContentVOs.add(b);
        releaseHomeworkVO.setHomeworkContentVOs(homeworkContentVOs);
        homeworkService.releaseHomework(releaseHomeworkVO);
    }


    @Test
    public void getClassHomeworkTest() {
        homeworkService.getClassHomework(3, 4).forEach(System.out::println);
    }
    @Test
    public void studentListWithPercentageTest() {
        homeworkService.studentListWithPercentage(24, 60);
    }

//    @Test
//    public void getQuesWithStuAnswerListTest() {
//        homeworkService.getQuesWithStuAnswerList(60, 99);
//    }

    @Test
    public void deleteHomeworkTest() {
        homeworkService.deleteHomework(102);
    }

    @Test
    public void updateFeedbackAndPointTest() {
        List<FeedbackAndPointVO> feedbackAndPointVOs = new ArrayList<>();
        FeedbackAndPointVO feedbackAndPointVO = new FeedbackAndPointVO();
        feedbackAndPointVO.setAcId(33);
        feedbackAndPointVO.setChId(60);
        feedbackAndPointVO.setFeedback("测试评语");
        feedbackAndPointVO.setPoint(56.0);
        feedbackAndPointVOs.add(feedbackAndPointVO);
        homeworkService.updateFeedbackAndPoint(feedbackAndPointVOs);
    }

    @Test
    public void getStudentListWithTotalPointTest() {
        homeworkService.getStudentListWithTotalPoint(23);
    }
}
