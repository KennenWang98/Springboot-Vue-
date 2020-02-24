package cn.net.sybt.springboot.service;

import cn.net.sybt.springboot.bean.*;
import cn.net.sybt.springboot.consts.DatabaseValue;
import cn.net.sybt.springboot.consts.Parameters;
import cn.net.sybt.springboot.mapper.*;
import cn.net.sybt.springboot.utils.DateToString;
import cn.net.sybt.springboot.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;


@Transactional
@Service
public class HomeworkService implements DatabaseValue, Parameters {
    @Autowired
    ClassHomeworkMapper classHomeworkMapper;
    @Autowired
    HomeworkContentMapper homeworkContentMapper;
    @Autowired
    AnswerMapper answerMapper;
    @Autowired
    AnswerContentMapper answerContentMapper;
    @Autowired
    StuChoiceMapper stuChoiceMapper;
    @Autowired
    StuJudgeMapper stuJudgeMapper;
    @Autowired
    StuEssayMapper stuEssayMapper;
    @Autowired
    StuFillMapper stuFillMapper;
    @Autowired
    PublisherQuestionsService publisherQuestionsService;
    @Autowired
    TeaQuestionService teaQuestionService;
    @Autowired
    BookService bookService;
    @Autowired
    ClazzService clazzService;
    @Autowired
    StudentService studentService;


    //发布作业的逻辑
    // 点击作业->显示两边的题库(AbsoluteQuesVO)->勾选一些题返回给我
    // (我需要前端{VO class_homework对象以及一些题目的ID以及题目来自于哪里  }，这些信息在之前已经包含在题目里返回给了前端)
    // 我插入class_homework，然后拿到ch_id以及前端给我的flag和ques_id插入hc表，并且加上状态的处理
    //发布作业
    //todo 对前端给我的的题目和chId做校验
    public List<ClassHomeworkVO> releaseHomework(ReleaseHomeworkVO releaseHomeworkVO) {
        ClassHomework classHomework = new ClassHomework();
        Date layoutTime = new Date();
        classHomework.setLayoutTime(layoutTime);
        classHomework.setTeaId(releaseHomeworkVO.getTeaId());
        classHomework.setClassId(releaseHomeworkVO.getClassId());
        Date deadline = new Date();
        deadline.setTime(layoutTime.getTime() + releaseHomeworkVO.getSecond() * 1000);
        classHomework.setDeadline(deadline);
        classHomework.setHomeworkName(releaseHomeworkVO.getHomeworkName());
        classHomework.setStatus("未批改");
        classHomeworkMapper.addClassHomework(classHomework);
        for (HomeworkContentVO item : releaseHomeworkVO.getHomeworkContentVOs()) {
            Integer hcFlag = item.getFlag();
            Integer hcQuesId = item.getQuesId();
            QuestionVO questionVO;
            if (hcFlag.equals(Parameters.PUB_FLAG)) {
                questionVO = publisherQuestionsService.selectOneFormatted(hcQuesId);
            } else {
                questionVO = teaQuestionService.getTeaQuestionById(hcQuesId);
            }
            if (questionVO == null)
                return null;
            HomeworkContent homeworkContent = new HomeworkContent();
            homeworkContent.setChId(classHomework.getChId());
            homeworkContent.setHcFlag(hcFlag);
            homeworkContent.setHcQuesId(hcQuesId);
            homeworkContentMapper.addHomeworkContent(homeworkContent);
        }
        return this.getClassHomework(releaseHomeworkVO.getTeaId(), releaseHomeworkVO.getClassId());
    }

    //显示发布作业时的分出版社和老师的题库
    public DifferedQuesVO getReleaseQues(Integer teacherId, Integer clazzId) {
        DifferedQuesVO differedQuesVO = new DifferedQuesVO();
        Integer bookId = bookService.getBookByClazz(clazzId);
        List<TeaQuestionVO> teaQuestionVOs = teaQuestionService.getAllTeaQuestionByClass(teacherId, clazzId);
        teaQuestionVOs.forEach(item -> item.setFlag(Parameters.TEACHER_FLAG));
        List<PubVO> pubVOs = publisherQuestionsService.selectAllFormatted(bookId);
        pubVOs.forEach((item -> item.setFlag(Parameters.PUB_FLAG)));
        differedQuesVO.setTeaQuestionVOs(teaQuestionVOs);
        differedQuesVO.setPubVOs(pubVOs);
        return differedQuesVO;
    }

    //显示当前班级的所有作业
    // 计算完成率一并返回
    //     显示作业是未批改还是已批改：根据point是否有空判断是否批改
    //     完成率：拿到当前班级的学生人数，同时拿着chID查answer表中的记录，计算百分比返回。
    public List<ClassHomeworkVO> getClassHomework(Integer teaId, Integer classId) {
        List<ClassHomework> classHomeworks = classHomeworkMapper
                .getClassHomeworksByTeaIdAndClassId(teaId, classId);
        Integer stuNumber = clazzService.getStudentsNumber(classId);
        return classHomeworks.stream()
                .map(item -> new ClassHomeworkVO(item,
                        answerMapper.getStuNumber(item.getChId()) / stuNumber * 100,
                        DateToString.parseTime(item.getLayoutTime())
                        , DateToString.parseTime(item.getDeadline())
                        , item.getDeadline().before(new Date()) ? CUT_OFF : NO_CUT_OFF))
                .collect(Collectors.toList());
    }

    //显示某个作业中的题目
    public List<QuestionVO> getHomeworkQues(Integer chId) {
        List<QuestionVO> result = new ArrayList<>();
        List<HomeworkContent> allHomeworkContent = homeworkContentMapper.getHomeworkContentBychId(chId);
        allHomeworkContent.forEach(item -> {
            QuestionVO questionVO;
            if (item.getHcFlag().equals(Parameters.PUB_FLAG)) {
                questionVO = publisherQuestionsService.selectOneFormatted(item.getHcQuesId());
                questionVO.setHcId(item.getHcId());
                result.add(questionVO);
            } else if (item.getHcFlag().equals(Parameters.TEACHER_FLAG)) {
                questionVO = teaQuestionService.getTeaQuestionById(item.getHcQuesId());
                questionVO.setHcId(item.getHcId());
                questionVO.setFlag(Parameters.TEACHER_FLAG);
                result.add(questionVO);
            }
        });
        return result;
    }

    //学生端完成作业逻辑
//     学生首先应该查看当前班级的作业(调getClassHomework方法)->显示作业的题目(逻辑和老师一样)(要求前端给我保存hcId)
//     然后学生答题，前端给我{VO  answer表的对象(chId,stuId)还有答题的题目对象answerContent(hcId,quesType)还有学生的答案list }
//     我先插入answerContent表，然后把这个答案分题型插入到各个答案表里面
    public StuHomeworkVO uploadStuAnswer(StuAnswerVO stuAnswerVO) {
        Integer stuId = studentService.getStudentIdByPhone(stuAnswerVO.getStuPhoneNumber());
        stuAnswerVO.getAnswer().setUploadTime(new Date());
        stuAnswerVO.getAnswer().setStuId(stuId);
        Answer answer = stuAnswerVO.getAnswer();
        if (answerMapper.getStuAnswerByChIdAndStuId(
                answer.getChId(), answer.getStuId()) != null) {
            return null;
        }
        answerMapper.addAnswer(stuAnswerVO.getAnswer());
        for (StuAnswerContentVO temp : stuAnswerVO.getStuAnswerContentVOs()) {
            AnswerContent answerContent = new AnswerContent();
            answerContent.setAnswerId(stuAnswerVO.getAnswer().getAnswerId());
            switch (temp.getQuesType()) {
                case SINGLE_CHOICE:
                case MULTI_CHOICE:
                case CHOICE:
                    answerContent.setHcId(temp.getHcId());
                    answerContent.setQuesType(temp.getQuesType());
                    answerContentMapper.addAnswerContent(answerContent);
                    List<LinkedHashMap> choiceMap = (List) temp.getObjects();
                    choiceMap.forEach(item -> {
                        StuChoice stuChoice = new StuChoice();
                        stuChoice.setStuAnswer(item.get("stuAnswer") + "");
                        stuChoice.setIsTrue((Integer) item.get("isTrue"));
                        stuChoice.setAcId(answerContent.getAcId());
                        stuChoiceMapper.addStuChoice(stuChoice);
                    });
                    break;
                case FILL:
                    answerContent.setHcId(temp.getHcId());
                    answerContent.setQuesType(temp.getQuesType());
                    answerContentMapper.addAnswerContent(answerContent);
                    List<LinkedHashMap> fillMap = (List) temp.getObjects();
                    fillMap.forEach(item -> {
                        StuFill stuFill = new StuFill();
                        stuFill.setIsTrue((Integer) item.get("isTrue"));
                        stuFill.setAnswerNo((Integer) item.get("answerNo"));
                        stuFill.setStuAnswer((String) item.get("stuAnswer"));
                        stuFill.setAcId(answerContent.getAcId());
                        stuFillMapper.addStuFill(stuFill);
                    });
                    break;
                case JUDGE:
                    answerContent.setHcId(temp.getHcId());
                    answerContent.setQuesType(temp.getQuesType());
                    answerContentMapper.addAnswerContent(answerContent);
                    List<LinkedHashMap> judgeMap = (List) temp.getObjects();
                    judgeMap.forEach(item -> {
                        StuJudge stuJudge = new StuJudge();
                        stuJudge.setIsTrue((Integer) item.get("isTrue"));
                        stuJudge.setAcId(answerContent.getAcId());
                        stuJudge.setStuAnswer((Integer) item.get("stuAnswer"));
                        stuJudgeMapper.addStuJudge(stuJudge);
                    });
                    break;
                case ESSAY:
                    answerContent.setHcId(temp.getHcId());
                    answerContent.setQuesType(temp.getQuesType());
                    answerContentMapper.addAnswerContent(answerContent);
                    List<LinkedHashMap> essayMap = (List) temp.getObjects();
                    essayMap.forEach(item -> {
                        StuEssay stuEssay = new StuEssay();
                        stuEssay.setAcId(answerContent.getAcId());
                        stuEssay.setAnswerEssay((String) item.get("answerEssay"));
                        stuEssayMapper.addStuEssay(stuEssay);
                    });
                    break;
            }
        }
        this.markObjective(answer.getChId(), answer.getStuId());
        return this.getQuesWithStuAnswerList(answer.getChId(), answer.getStuId());
    }

    // 老师删除发布过的作业
    public List<ClassHomeworkVO> deleteHomework(Integer chId) {
        ClassHomework toDelete = classHomeworkMapper.getClassHomeworkById(chId);
        if (toDelete == null)
            return null;
        List<Answer> answers = answerMapper.getstuAnswerByChId(chId);
        if (answers.size() > 0) {
            answers.forEach(item -> {
                Integer answerId = item.getAnswerId();
                List<AnswerContent> answerContents = answerContentMapper.getAnswerContentsByAnswerId(answerId);
                if (answerContents.size() > 0) {
                    answerContents.forEach(temp -> {
                        this.deleteStuAnswer(temp.getAcId(), temp.getQuesType());
                        answerContentMapper.deleteAnswerContentById(temp.getAcId());
                    });
                }
                answerMapper.deleteAnswerById(answerId);
            });
        }
        homeworkContentMapper.deleteHomeworkContentBychId(chId);
        Integer teaId = toDelete.getTeaId();
        Integer classId = toDelete.getClassId();
        classHomeworkMapper.deleteClassHomeworkById(chId);
        return this.getClassHomework(teaId, classId);
    }

    //老师点击去批改添加评语
    public boolean updateFeedbackAndPoint(List<FeedbackAndPointVO> feedbackAndPointVOs) {
        if (feedbackAndPointVOs.size() == 0)
            return false;
        for (FeedbackAndPointVO item : feedbackAndPointVOs) {
            AnswerContent answerContent = answerContentMapper.getAnswerContentById(item.getAcId());
            if (answerContent == null)
                return false;
        }
        for (FeedbackAndPointVO item : feedbackAndPointVOs) {
            AnswerContent answerContent = answerContentMapper.getAnswerContentById(item.getAcId());
            answerContent.setFeedback(item.getFeedback());
            if (item.getPoint() != null)
                answerContent.setPoint(item.getPoint() / 100);
            answerContentMapper.updateAnswerContent(answerContent);
        }
        ClassHomework toUpdate = classHomeworkMapper.getClassHomeworkById(feedbackAndPointVOs.get(0).getChId());
        toUpdate.setStatus("已批改");
        classHomeworkMapper.updateClassHomework(toUpdate);
        FeedbackAndPointVO temp = feedbackAndPointVOs.get(0);
        Answer answer = answerMapper.getAnswerById(answerContentMapper.getAnswerContentById(temp.getAcId()).getAnswerId());
        List<AnswerContent> answerContents = answerContentMapper.getAnswerContentsByAnswerId(answer.getAnswerId());
        double newPoint = this.getPoint(answerContents);
        Double percentage = newPoint / answerContents.size() * 100;
        DecimalFormat df = new DecimalFormat("#.00");
        answer.setPercentage(Double.parseDouble(df.format(percentage)));
        answerMapper.updateAnswer(answer);
        return true;
    }

    //老师在截止日期之后点击去批改，显示带每个学生正确率的学生列表
    public List<StudentListWithPerVO> studentListWithPercentage(Integer classId, Integer chId) {
        List<StudentListWithPerVO> resultList = new ArrayList<>();
        List<Student> students = clazzService.getStudentByclassId(classId);
        if (students.size() != 0) {
            students.forEach(item -> {
                StudentListWithPerVO studentListWithPerVO = new StudentListWithPerVO();
                Answer answers = answerMapper.getStuAnswerByChIdAndStuId(chId, item.getStuId());
                studentListWithPerVO.setStudent(item);
                if (answers == null)
                    studentListWithPerVO.setPercentage("该学生还未完成作业！");
                else
                    studentListWithPerVO.setPercentage(answers.getPercentage().toString());
                resultList.add(studentListWithPerVO);
            });
            resultList.sort(Comparator.comparingDouble(item ->
                    Double.parseDouble(item.getPercentage()
                            .equals("该学生还未完成作业！") ? "0.0" : item.getPercentage())));
            Collections.reverse(resultList);
            return resultList;
        } else
            return null;
    }

    //老师点击学生列表里的学生，和学生在截止日期之后点击某一次作业，显示该次作业的所有题目，自己的答案，老师显示按钮
    //     加评语or判作文题，学生显示老师的评语
    public StuHomeworkVO getQuesWithStuAnswerList(Integer chId, Integer stuId) {
        StuHomeworkVO result = new StuHomeworkVO();
        Answer answer = answerMapper.getStuAnswerByChIdAndStuId(chId, stuId);
        if (answer == null)
            return null;
        result.setAnswer(answer);
        List<QuesWithStuAnswerVO> quesWithStuAnswerVOs = new ArrayList<>();
        Integer answerId = answer.getAnswerId();
        List<AnswerContent> answerContents = answerContentMapper.getAnswerContentsByAnswerId(answerId);
        List<QuestionVO> questions = this.getHomeworkQues(chId);
        for (QuestionVO temp : questions) {
            QuesWithStuAnswerVO quesWithStuAnswerVO = new QuesWithStuAnswerVO();
            quesWithStuAnswerVO.setQuestionAndSolutions(temp);
            for (AnswerContent thisAnswerContent : answerContents) {
                if (thisAnswerContent.getHcId().equals(temp.getHcId())) {
                    quesWithStuAnswerVO.setAnswerContent(thisAnswerContent);
                    Integer acId = thisAnswerContent.getAcId();
                    String quesType = thisAnswerContent.getQuesType();
                    quesWithStuAnswerVO.setAnswer(getStuAnswerList(acId, quesType));
                    break;
                }
            }
            quesWithStuAnswerVOs.add(quesWithStuAnswerVO);
        }
        result.setQuesWithStuAnswerVOs(quesWithStuAnswerVOs);
        return result;
    }

    //返回班级学生列表的总分排序
    public List<StudentListVO> getStudentListWithTotalPoint(Integer classId) {
        List<Student> students = clazzService.getStudentByclassId(classId);
        List<StudentListVO> resultList = students.stream().map(item -> {
            Integer stuId = item.getStuId();
            List<Answer> stuAnswers = answerMapper.getstuAnswerBystuId(stuId);
            Double totalPoints;
            if (stuAnswers.size() > 0)
                totalPoints = stuAnswers.stream().map(Answer::getPercentage).reduce(Double::sum).get();
            else
                totalPoints = 0.0;
            return new StudentListVO(item, totalPoints / stuAnswers.size());
        }).collect(Collectors.toList());
        resultList.sort(Comparator.comparingDouble(StudentListVO::getPointSum));
        Collections.reverse(resultList);
        return resultList;
    }

    public void deleteStuAnswer(Integer acId, String quesType) {
        switch (quesType) {
            case SINGLE_CHOICE:
            case MULTI_CHOICE:
            case CHOICE:
                stuChoiceMapper.deleteStuChoiceById(acId);
                break;
            case FILL:
                stuFillMapper.deleteStuFillById(acId);
                break;
            case JUDGE:
                stuJudgeMapper.deleteStuJudgeById(acId);
                break;
            case ESSAY:
                stuEssayMapper.deleteStuEssayById(acId);
                break;
        }
    }

    public void deleteClassHomework(Integer classId) {
        List<ClassHomework> todelete = classHomeworkMapper.getClassHomeworksByAndClassId(classId);
        if (todelete.size() != 0)
            todelete.forEach(item -> {
                this.deleteHomework(item.getChId());
            });
    }

    private List<StuAnswer> getStuAnswerList(Integer acId, String quesType) {
        List<StuAnswer> result = new ArrayList<>();
        switch (quesType) {
            case CHOICE:
            case SINGLE_CHOICE:
            case MULTI_CHOICE:
                result.addAll(stuChoiceMapper.getStuChoiceById(acId));
                break;
            case FILL:
                result.addAll(stuFillMapper.getStuFillById(acId));
                break;
            case JUDGE:
                result.add(stuJudgeMapper.getStuJudgeById(acId));
                break;
            case ESSAY:
                result.add(stuEssayMapper.getStuEssayById(acId));
                break;
        }
        return result;
    }

    // 自动批改客观题 调用时机：学生提交答案之后，我立即对学生的作业批改，并返回该学生的作答情况
    //     拿到chId，查class_homework表，拿到这项作业->查hc表，用chId拿到每道题的具体信息(答案)
    //     拿到chId，查answer表，得到做了这个作业的学生->用answerId查answer_content表，得到list<acId>
    //     ->list<acId>查到各个表中的学生答案和上面的答案作比对，将正确与否记在各个表中，结束。
    //     是否应该在answer_content表中同样记录正误：加一个字段 写了
    //     是否在answer表中记录该学生的正确率：加一个字段 写了
    private void markObjective(Integer chId, Integer stuId) {
        //获取学生作答
        //获取了该作业所有的学生作答记录
        Answer studentAnswer = answerMapper.getStuAnswerByChIdAndStuId(chId, stuId);
        //该学生作答记录的answerId
        Integer stuAnswerId = studentAnswer.getAnswerId();
        //该学生作答记录中有哪些题目
        List<AnswerContent> answerContents = answerContentMapper.getAnswerContentsByAnswerId(stuAnswerId);
        //分每一道题进行处理
        answerContents.forEach(answerContent -> {
            //获取到了对应的HC表记录，从而可以拿到quesId和quesType
            HomeworkContent homeworkContent = homeworkContentMapper.getHomeworkContentById(answerContent.getHcId());
            answerContent.setPoint(1.0);//默认该道题为满分
            Integer quesId = homeworkContent.getHcQuesId();
            Integer hcFlag = homeworkContent.getHcFlag();
            Integer acId = answerContent.getAcId();
            //获取题目内容
            QuestionVO questionVO;
            if (hcFlag.equals(Parameters.PUB_FLAG)) {
                questionVO = publisherQuestionsService.selectOneFormatted(quesId);
                switch (questionVO.getQuesType()) {
                    case SINGLE_CHOICE:
                    case MULTI_CHOICE:
                    case CHOICE:
                        List<PubChoiceSolution> choiceSolutions = questionVO.getSolutions();//题目答案
                        List<StuChoice> choiceAnswers = stuChoiceMapper.getStuChoiceById(acId);//学生答案
                        choiceAnswers.forEach(stuChoiceAnswer -> {
                            int stuAnswer = stuChoiceAnswer.getStuAnswer().charAt(0) & 0xf;
                            Integer isTrue = choiceSolutions.get(stuAnswer).getIsTrue();
                            if (isTrue.equals(0))//有选错的则0分
                                answerContent.setPoint(0.0);
                            stuChoiceAnswer.setIsTrue(isTrue);
                            stuChoiceMapper.updateStuChoice(stuChoiceAnswer);
                        });
                        break;
                    case FILL:
                        List<PubFillSolution> fillSolutions = questionVO.getSolutions();//题目答案
                        List<StuFill> fillAnswers = stuFillMapper.getStuFillById(acId);//学生答案
                        StuFill stuFillAnswer;
                        for (int i = 0; i < fillAnswers.size(); i++) {
                            stuFillAnswer = fillAnswers.get(i);
                            String stuAnswer = stuFillAnswer.getStuAnswer();
                            if (stuAnswer.equals(fillSolutions.get(i).getSolutionContent()))
                                stuFillAnswer.setIsTrue(1);
                            else {
                                stuFillAnswer.setIsTrue(0);
                                answerContent.setPoint(0.0);
                            }
                            stuFillMapper.updateStuFill(stuFillAnswer);
                        }
                        break;
                    case JUDGE:
                        PubJudge pubJudge = (PubJudge) questionVO.getQuestion();
                        StuJudge stuJudgeAnswer = stuJudgeMapper.getStuJudgeById(acId);
                        if (stuJudgeAnswer.getStuAnswer().equals(pubJudge.getIsTrue()))
                            stuJudgeAnswer.setIsTrue(1);
                        else {
                            stuJudgeAnswer.setIsTrue(0);
                            answerContent.setPoint(0.0);
                        }
                        stuJudgeMapper.updateStuJudge(stuJudgeAnswer);
                        break;
                    case ESSAY:
                        answerContent.setPoint(0.0);
                        break;
                }
                answerContentMapper.updateAnswerContent(answerContent);
            } else if (hcFlag.equals(Parameters.TEACHER_FLAG)) {
                questionVO = teaQuestionService.getTeaQuestionById(quesId);
                switch (questionVO.getQuesType()) {
                    case SINGLE_CHOICE:
                    case MULTI_CHOICE:
                    case CHOICE:
                        List<TeaChoiceSolution> choiceSolutions = questionVO.getSolutions();//题目答案
                        List<StuChoice> choiceAnswers = stuChoiceMapper.getStuChoiceById(acId);//学生答案
                        choiceAnswers.forEach(stuChoiceAnswer -> {
                            int stuAnswer = stuChoiceAnswer.getStuAnswer().charAt(0) & 0xf;
                            Integer isTrue = choiceSolutions.get(stuAnswer).getIsTrue();
                            if (isTrue.equals(0))//有选错的则0分
                                answerContent.setPoint(0.0);
                            stuChoiceAnswer.setIsTrue(isTrue);
                            Integer result = stuChoiceMapper.updateStuChoice(stuChoiceAnswer);
                            System.out.println(result);
                        });
                        break;
                    case FILL:
                        List<TeaFillSolution> fillSolutions = questionVO.getSolutions();//题目答案
                        List<StuFill> fillAnswers = stuFillMapper.getStuFillById(acId);//学生答案
                        StuFill stuFillAnswer;
                        for (int i = 0; i < fillAnswers.size(); i++) {
                            stuFillAnswer = fillAnswers.get(i);
                            String stuAnswer = stuFillAnswer.getStuAnswer();
                            if (stuAnswer.equals(fillSolutions.get(i).getSolutionContent()))
                                stuFillAnswer.setIsTrue(1);
                            else {
                                stuFillAnswer.setIsTrue(0);
                                answerContent.setPoint(0.0);
                            }
                            Integer result = stuFillMapper.updateStuFill(stuFillAnswer);
                            System.out.println(result);
                        }
                        break;
                    case JUDGE:
                        TeaJudge teaJudge = (TeaJudge) questionVO.getQuestion();
                        StuJudge stuJudgeAnswer = stuJudgeMapper.getStuJudgeById(acId);
                        if (stuJudgeAnswer.getStuAnswer().equals(teaJudge.getIsTrue()))
                            stuJudgeAnswer.setIsTrue(1);
                        else {
                            stuJudgeAnswer.setIsTrue(0);
                            answerContent.setPoint(0.0);
                        }
                        stuJudgeMapper.updateStuJudge(stuJudgeAnswer);
                        break;
                    case ESSAY:
                        answerContent.setPoint(0.0);
                        break;
                }
                answerContentMapper.updateAnswerContent(answerContent);
            }
        });
        Double points = this.getPoint(answerContents);
        Double percentage = points / answerContents.size() * 100;
        DecimalFormat df = new DecimalFormat("#.00");
        studentAnswer.setPercentage(Double.parseDouble(df.format(percentage)));
        answerMapper.updateAnswer(studentAnswer);
    }

    private Double getPoint(List<AnswerContent> answerContents) {
        return answerContents.stream()
                .map(AnswerContent::getPoint)
                .reduce(Double::sum)
                .get();
    }
}
