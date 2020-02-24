package cn.net.sybt.springboot.serviceTest;


import cn.net.sybt.springboot.bean.*;
import cn.net.sybt.springboot.service.TeaQuestionService;
import cn.net.sybt.springboot.utils.CheckObject;
import cn.net.sybt.springboot.vo.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class TeaQuestionServiceTest {
    @Autowired
    TeaQuestionService teaQuestionService;
    @Autowired
    CheckObject checkObject;

    @Test
    public void getAllTeaQuestionByIdTest(){
        List<TeaQuestionVO> test = teaQuestionService.getAllTeaQuestionById(1);
        test.forEach(System.out::println);
    }

    @Test
    public void getAllTeaQuestionByClassTest(){
        List<TeaQuestionVO> test = teaQuestionService.getAllTeaQuestionByClass(1, 1);
        test.forEach(System.out::println);
    }
    @Test
    public void uploadTeacherChoiceTest(){
        TeaChoiceVO teaChoiceVo = new TeaChoiceVO();
        teaChoiceVo.getTeaChoiceQuestion().setQuesContent("测试插入题干");
        teaChoiceVo.getTeaChoiceQuestion().setSolutionText("测试插入解析文本");
        teaChoiceVo.getTeaChoiceQuestion().setSolutionLink("测试插入解析连接");
        TeaChoiceSolution solution = new TeaChoiceSolution();
        solution.setChoiceContent("测试答案选项文本");
        solution.setQuesId(teaChoiceVo.getTeaChoiceQuestion().getQuesId());
        solution.setIsTrue(1);
        solution.setChoiceLink("测试答案选项连接");
        teaChoiceVo.getTeaChoiceSolutions().add(solution);
        TeacherQuestions teacherQuestions = new TeacherQuestions();
        teacherQuestions.setTeaId(5);
        teacherQuestions.setClassId(5);
        teacherQuestions.setTeaQuesType("选择题");
        teaChoiceVo.setTeacherQuestions(teacherQuestions);
        teaQuestionService.uploadTeacherChoice(teaChoiceVo);
    }
    @Test
    public void uploadTeacherEssayTest(){
        TeaEssayVO teaEssayVO = new TeaEssayVO();
        TeaEssay teaEssay = new TeaEssay();
        teaEssay.setQuesContent("作文测试插入连接");
        teaEssay.setSolutionLink("作文测试范文连接");
        teaEssayVO.setTeaEssay(teaEssay);
        TeacherQuestions teacherQuestions = new TeacherQuestions();
        teacherQuestions.setTeaQuesType("作文题");
        teacherQuestions.setClassId(55);
        teacherQuestions.setTeaId(55);
        teaEssayVO.setTeacherQuestions(teacherQuestions);
        teaQuestionService.uploadTeacherEssay(teaEssayVO);
    }
    @Test
    public void uploadTeacherFillTest() {
        TeaFillVO teaFillVO = new TeaFillVO();
        teaFillVO.getTeaFillQuestion().setQuesContent("测试插入填空题干");
        teaFillVO.getTeaFillQuestion().setSolutionText("测试插入解析文本");
        teaFillVO.getTeaFillQuestion().setSolutionLink("测试插入解析连接");
        TeaFillSolution solution = new TeaFillSolution();
        solution.setSolutionNo(1);
        solution.setSolutionContent("填空测试插入");
        teaFillVO.getTeaFillSolutions().add(solution);
        TeacherQuestions teacherQuestions = new TeacherQuestions();
        teacherQuestions.setTeaQuesType("填空题");
        teacherQuestions.setClassId(55);
        teacherQuestions.setTeaId(55);
        teaFillVO.setTeacherQuestions(teacherQuestions);
        teaQuestionService.uploadTeacherFill(teaFillVO);

    }
    @Test
    public void uploadTeacherJudgeTest() {
        TeaJudgeVO teaJudgeVO = new TeaJudgeVO();
        TeaJudge teaJudge = new TeaJudge();
        teaJudge.setQuesContent("测试判断题题干");
        teaJudge.setQuesLink("");
        teaJudge.setIsTrue(1);
        teaJudge.setSolutionText("测试判断题解析文本");
        teaJudge.setSolutionLink("测试判断题解析连接");
        TeacherQuestions teacherQuestions = new TeacherQuestions();
        teacherQuestions.setTeaQuesType("判断题");
        teacherQuestions.setClassId(55);
        teacherQuestions.setTeaId(55);
        teaJudgeVO.setTeaJudge(teaJudge);
        teaJudgeVO.setTeacherQuestions(teacherQuestions);
        teaQuestionService.uploadTeacherJudge(teaJudgeVO);
    }
    @Test
    public void uploadTeacherMatchTest(){
        TeaMatchVO teaMatchVO = new TeaMatchVO();
        teaMatchVO.getTeaMatchQuestion().setQuesContent("");
        teaMatchVO.getTeaMatchQuestion().setSolutionText("");
        teaMatchVO.getTeaMatchQuestion().setSolutionLink("");
        TeaMatchSolution teaMatchSolution = new TeaMatchSolution();
        teaMatchSolution.setLeftValue("测试插入连线题左值");
        teaMatchSolution.setLeftLink("测试插入连线题左值连接");
        teaMatchSolution.setRightValue("测试插入连线题右值");
        teaMatchSolution.setRightLink("测试插入连线题右值连接");
        teaMatchVO.getTeaMatchSolutions().add(teaMatchSolution);
        //teaQuestionService.uploadTeacherMatch(teaMatchVO);
    }

    @Test
    public void uploadTeaSortTest() {
        TeaSortVO teaSortVo = new TeaSortVO();
        teaSortVo.getTeaSortQuestion().setQuesContent("排序题测试插入");
        TeaSortSolution teaSortSolution = new TeaSortSolution();
        teaSortSolution.setQuesContent("排序题测试插入");
        teaSortSolution.setSortNo("5");
        teaSortVo.getTeaSortSolutions().add(teaSortSolution);
        //  teaQuestionService.uploadTeacherSort(teaSortVo);
    }

    @Test
    public void deleteTeacherQuesTest() {
        Integer quesId = 59;
        System.out.println(teaQuestionService.deleteTeacherQues(quesId));
    }

    @Test
    public void getTeaQuestionByIdTest() {
        Integer quesId = 59;
        System.out.println(this.teaQuestionService.getTeaQuestionById(quesId));
    }
}
