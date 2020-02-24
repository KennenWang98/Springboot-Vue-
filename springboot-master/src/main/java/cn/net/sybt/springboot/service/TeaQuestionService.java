package cn.net.sybt.springboot.service;

import cn.net.sybt.springboot.bean.*;
import cn.net.sybt.springboot.consts.DatabaseValue;
import cn.net.sybt.springboot.mapper.*;
import cn.net.sybt.springboot.utils.CheckObject;
import cn.net.sybt.springboot.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service

public class TeaQuestionService implements DatabaseValue {
    @Autowired
    TeacherQuestionsMapper teacherQuestionsMapper;
    @Autowired
    TeaChoiceQuestionMapper teaChoiceQuestionMapper;
    @Autowired
    TeaChoiceSolutionMapper teaChoiceSolutionMapper;
    @Autowired
    TeaEssayMapper teaEssayMapper;
    @Autowired
    TeaFillQuestionMapper teaFillQuestionMapper;
    @Autowired
    TeaFillSolutionMapper teaFillSolutionMapper;
    @Autowired
    TeaJudgeMapper teaJudgeMapper;
    @Autowired
    TeaMatchQuestionMapper teaMatchQuestionMapper;
    @Autowired
    TeaMatchSolutionMapper teaMatchSolutionMapper;
    @Autowired
    TeaSortQuestionMapper teaSortQuestionMapper;
    @Autowired
    TeaSortSolutionMapper teaSortSolutionMapper;
    @Autowired
    CheckObject checkObject;
    @Autowired
    HomeworkContentMapper homeworkContentMapper;
    @Autowired
    AnswerContentMapper answerContentMapper;
    @Autowired
    HomeworkService homeworkService;

    //0选择 1作文 2填空 3判断 4连线 5排序
    //返回某个老师的全部问题
    public List<TeaQuestionVO> getAllTeaQuestionById(Integer teacherId) {
        List<TeacherQuestions> all;
//        Integer start = pages*5;
        all = teacherQuestionsMapper.getAllTeacherQuestions();// 所有教师的所有题目
        List<TeaQuestionVO> result = new ArrayList<>();// 结果的返回VO
        for (TeacherQuestions temp : all) {
            if (temp.getTeaId().equals(teacherId)) {
                findQuestion(result, temp);
            }
        }
        return result;
    }

    //返回某个老师在某个班级下的全部问题
    public List<TeaQuestionVO> getAllTeaQuestionByClass(Integer teacherId, Integer clazzId) {
        List<TeacherQuestions> all;
//        Integer start = pages*5;
        all = teacherQuestionsMapper.getAllTeacherQuestions();// 所有教师的所有题目
        List<TeaQuestionVO> result = new ArrayList<>();// 结果的返回VO
        for (TeacherQuestions temp : all) {
            if (temp.getTeaId().equals(teacherId) && temp.getClassId().equals(clazzId)) {
                findQuestion(result, temp);
            }
        }
        return result;
    }

    //按照ID返回一个特定的问题
    public TeaQuestionVO getTeaQuestionById(Integer teaQuesId) {
        try {
            TeaQuestionVO result = new TeaQuestionVO();
            TeacherQuestions tqs = teacherQuestionsMapper.getTeacherQuestionsById(teaQuesId);
            findOneQuestion(result, tqs);
            return result;
        } catch (NullPointerException e) {
            return null;
        }
    }

    //根据老师题库总合表TeacherQuestion查询某个题目的具体内容和答案放在老师题目返回VO TeaQuestionVO中
    private void findOneQuestion(TeaQuestionVO result, TeacherQuestions tqs) throws NullPointerException {
        TeaQuestion teaQuestion = this.getTeaQuestionByQuesId(tqs.getTeaQuesSpecificId(), tqs.getTeaQuesType());
        List<TeaSolution> teaSolutions = this.getTeaSolutionsByQuesId(tqs.getTeaQuesSpecificId(), tqs.getTeaQuesType());
        result.setTeaId(tqs.getTeaId());
        result.setClassId(tqs.getClassId());
        result.setQuesType(tqs.getTeaQuesType());
        result.setQuesAllId(tqs.getTeaQuesId());
        result.setQuestion(teaQuestion);
        result.setSolutions(teaSolutions);
    }

    //根据老师题库总合表TeacherQuestion查出题目的具体内容和答案的VO放在result中
    private void findQuestion(List<TeaQuestionVO> result, TeacherQuestions temp) {
        TeaQuestionVO teaQuestionVo = new TeaQuestionVO();
        findOneQuestion(teaQuestionVo, temp);
        result.add(teaQuestionVo);
    }

    //根据quesId和quesType返回老师问题的抽象类
    private TeaQuestion getTeaQuestionByQuesId(Integer quesId, String quesType) {

        TeaQuestion result;
        switch (quesType) {
            case "单选题":
            case "多选题":
            case "选择题":
                result = teaChoiceQuestionMapper.getTeaChoiceQuestionById(quesId);
                break;
            case "作文题":
                result = teaEssayMapper.getTeaEssayById(quesId);
                break;
            case "填空题":
                TeaFillQuestion teaFillQuestion = teaFillQuestionMapper.getTeaFillQuestionById(quesId);
                String quesContent = teaFillQuestion.getQuesContent();
                int index = 1;
                while (quesContent.contains("¿")) {
                    String indexString = Integer.toString(index);
                    quesContent = quesContent.replaceFirst("¿", " " + indexString + "._____");
                    index++;
                }
                teaFillQuestion.setQuesContent(quesContent);
                result = teaFillQuestion;
                break;
            case "判断题":
                result = teaJudgeMapper.getTeaJudgeById(quesId);
                break;
            case "连线题":
                result = teaMatchQuestionMapper.getTeaMatchQuestionById(quesId);
                break;
            case "排序题":
                result = teaSortQuestionMapper.getTeaSortQuestionById(quesId);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + quesType);
        }
        return result;
    }

    //根据quesId和quesType返回老师问题答案的抽象类list
    private List<TeaSolution> getTeaSolutionsByQuesId(Integer quesId, String quesType) {
        List<TeaSolution> result = new ArrayList<>();
        switch (quesType) {
            case SINGLE_CHOICE:
            case MULTI_CHOICE:
            case CHOICE:
                result.addAll(teaChoiceSolutionMapper.getTeaChoiceSolutionById(quesId));
                break;
            case ESSAY:
            case JUDGE:
                result = null;
                break;
            case FILL:
                result.addAll(teaFillSolutionMapper.getTeaFillSolutionById(quesId));
                break;
            case MATCH:
                result.addAll(teaMatchSolutionMapper.getTeaMatchSolutionById(quesId));
                break;
            case SORT:
                result.addAll(teaSortSolutionMapper.getTeaSortSolutionById(quesId));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + quesType);
        }
        return result;
    }

    @Transactional
    public List<Integer> uploadTeacherChoice(TeaChoiceVO teaChoiceVo) {
        List<Integer> resultList = new ArrayList<>();
        Integer result = teaChoiceQuestionMapper.addTeaChoiceQuestion(teaChoiceVo.getTeaChoiceQuestion());
        if (result.equals(0)) {
            resultList.add(result);
            return resultList;
        }
        teaChoiceVo.getTeaChoiceSolutions().forEach(item -> item.setQuesId(teaChoiceVo.getTeaChoiceQuestion().getQuesId()));
        teaChoiceVo.getTeaChoiceSolutions().forEach(item -> teaChoiceSolutionMapper.addTeaChoiceSolution(item));
        teaChoiceVo.getTeacherQuestions().setTeaQuesSpecificId(teaChoiceVo.getTeaChoiceQuestion().getQuesId());
        teacherQuestionsMapper.addTeacherQuestions(teaChoiceVo.getTeacherQuestions());
        resultList.add(result);
        resultList.add(teaChoiceVo.getTeacherQuestions().getTeaQuesId());
        return resultList;
    }

    @Transactional
    public List<Integer> uploadTeacherEssay(TeaEssayVO teaEssayVO) {
        List<Integer> resultList = new ArrayList<>();
        Integer result = teaEssayMapper.addTeaEssay(teaEssayVO.getTeaEssay());
        if (result.equals(0)) {
            resultList.add(result);
            return resultList;
        }
        teaEssayVO.getTeacherQuestions().setTeaQuesSpecificId(teaEssayVO.getTeaEssay().getQuesId());
        teacherQuestionsMapper.addTeacherQuestions(teaEssayVO.getTeacherQuestions());
        resultList.add(result);
        resultList.add(teaEssayVO.getTeacherQuestions().getTeaQuesId());
        return resultList;
    }

    @Transactional
    public List<Integer> uploadTeacherFill(TeaFillVO teaFillVO) {
        List<Integer> resultList = new ArrayList<>();
        Integer result = teaFillQuestionMapper.addTeaFillQuestion(teaFillVO.getTeaFillQuestion());
        if (result.equals(0)) {
            resultList.add(result);
            return resultList;
        }
        teaFillVO.getTeaFillSolutions().forEach(item -> item.setQuesId(teaFillVO.getTeaFillQuestion().getQuesId()));
        teaFillVO.getTeaFillSolutions().forEach(item -> teaFillSolutionMapper.addTeaFillSolution(item));
        teaFillVO.getTeacherQuestions().setTeaQuesSpecificId(teaFillVO.getTeaFillQuestion().getQuesId());
        teacherQuestionsMapper.addTeacherQuestions(teaFillVO.getTeacherQuestions());
        resultList.add(result);
        resultList.add(teaFillVO.getTeacherQuestions().getTeaQuesId());
        return resultList;
    }

    @Transactional
    public List<Integer> uploadTeacherJudge(TeaJudgeVO teaJudgeVO) {
        List<Integer> resultList = new ArrayList<>();
        Integer result = teaJudgeMapper.addTeaJudge(teaJudgeVO.getTeaJudge());
        if (result.equals(0)) {
            resultList.add(result);
            return resultList;
        }
        teaJudgeVO.getTeacherQuestions().setTeaQuesSpecificId(teaJudgeVO.getTeaJudge().getQuesId());
        teacherQuestionsMapper.addTeacherQuestions(teaJudgeVO.getTeacherQuestions());
        resultList.add(result);
        resultList.add(teaJudgeVO.getTeacherQuestions().getTeaQuesId());
        return resultList;
    }

    @Transactional
    public Integer uploadTeacherMatch(TeaMatchVO teaMatchVO) {
        Integer result = teaMatchQuestionMapper.addTeaMatchQuestion(teaMatchVO.getTeaMatchQuestion());
        if (result.equals(0))
            return result;
        teaMatchVO.getTeaMatchSolutions().forEach(item -> item.setQuesId(teaMatchVO.getTeaMatchQuestion().getQuesId()));
        teaMatchVO.getTeaMatchSolutions().forEach(item -> teaMatchSolutionMapper.addTeaMatchSolution(item));
        teaMatchVO.getTeacherQuestions().setTeaQuesSpecificId(teaMatchVO.getTeaMatchQuestion().getQuesId());
        teacherQuestionsMapper.addTeacherQuestions(teaMatchVO.getTeacherQuestions());
        return result;
    }

    @Transactional
    public Integer uploadTeacherSort(TeaSortVO teaSortVo) {
        Integer result = teaSortQuestionMapper.addTeaSortQuestion(teaSortVo.getTeaSortQuestion());
        if (result.equals(0))
            return result;
        teaSortVo.getTeaSortSolutions().forEach(item -> item.setQuesId(teaSortVo.getTeaSortQuestion().getQuesId()));
        teaSortVo.getTeaSortSolutions().forEach(item -> teaSortSolutionMapper.addTeaSortSolution(item));
        teaSortVo.getTeacherQuestions().setTeaQuesSpecificId(teaSortVo.getTeaSortQuestion().getQuesId());
        teacherQuestionsMapper.addTeacherQuestions(teaSortVo.getTeacherQuestions());
        return result;
    }

    @Transactional
    public Integer updateTeacherChoice(TeaChoiceVO teaChoiceVo) {
        Integer result = teaChoiceQuestionMapper.updateTeaChoiceQuestion(teaChoiceVo.getTeaChoiceQuestion());
        if (result.equals(0))
            return result;
        teaChoiceVo.getTeaChoiceSolutions().forEach(item -> teaChoiceSolutionMapper.updateTeaChoiceSolution(item));
        return result;
    }

    @Transactional
    public Integer updateTeacherEssay(TeaEssayVO teaEssayVO) {
        return teaEssayMapper.updateTeaEssay(teaEssayVO.getTeaEssay());
    }

    @Transactional
    public Integer updateTeacherFill(TeaFillVO teaFillVO) {
        Integer result = teaFillQuestionMapper.updateTeaFillQuestion(teaFillVO.getTeaFillQuestion());
        if (result.equals(0))
            return result;
        teaFillVO.getTeaFillSolutions().forEach(item -> teaFillSolutionMapper.updateTeaFillSolution(item));
        return result;
    }

    @Transactional
    public Integer updateTeacherJudge(TeaJudgeVO teaJudgeVO) {
        return teaJudgeMapper.updateTeaJudge(teaJudgeVO.getTeaJudge());
    }

    @Transactional
    public Integer deleteTeacherQues(Integer quesId) {
        TeacherQuestions teacherQuestions = teacherQuestionsMapper.getTeacherQuestionsById(quesId);
        if (teacherQuestions == null)
            return 0;
        Integer SpecificId = teacherQuestions.getTeaQuesSpecificId();
        String quesType = teacherQuestions.getTeaQuesType();
        Integer result;
        switch (quesType) {
            case SINGLE_CHOICE:
            case MULTI_CHOICE:
            case CHOICE:
                result = teaChoiceQuestionMapper.deleteTeaChoiceQuestionById(SpecificId);
                if (result.equals(0))
                    return result;
                teaChoiceSolutionMapper.deleteTeaChoiceSolutionById(SpecificId);
                break;
            case ESSAY:
                result = teaEssayMapper.deleteTeaEssayById(SpecificId);
                if (result.equals(0))
                    return result;
                break;
            case JUDGE:
                result = teaJudgeMapper.deleteTeaJudgeById(SpecificId);
                if (result.equals(0))
                    return result;
                break;
            case FILL:
                result = teaFillQuestionMapper.deleteTeaFillQuestionById(SpecificId);
                if (result.equals(0))
                    return result;
                teaFillSolutionMapper.deleteTeaFillSolutionById(SpecificId);
                break;
            default:
                result = 0;
        }
        teacherQuestionsMapper.deleteTeacherQuestionsById(quesId);
        List<HomeworkContent> aims = homeworkContentMapper.getHomeworkContentByQuesId(quesId);
        if (aims.size() != 0) {
            aims.forEach(item -> {
                List<AnswerContent> aimAnswerContent = answerContentMapper.getAllAnswerContentsByHcid(item.getHcId());
                if (aimAnswerContent != null) {
                    for (AnswerContent answerContent : aimAnswerContent) {
                        homeworkService.deleteStuAnswer(answerContent.getAcId(), answerContent.getQuesType());
                        answerContentMapper.deleteAnswerContentById(answerContent.getAcId());
                    }
                }
            });
            homeworkContentMapper.deleteHomeworkContentByQuesId(quesId);
        }
        return result;
    }
}
