package cn.net.sybt.springboot.service;

import cn.net.sybt.springboot.bean.*;
import cn.net.sybt.springboot.consts.DatabaseValue;
import cn.net.sybt.springboot.consts.Parameters;
import cn.net.sybt.springboot.interfaces.PubQuestionInterface;
import cn.net.sybt.springboot.interfaces.SolutionMapperInterface;
import cn.net.sybt.springboot.mapper.*;
import cn.net.sybt.springboot.utils.PublisherQuestionsFormatter;
import cn.net.sybt.springboot.vo.PubVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PublisherQuestionsService implements DatabaseValue {
    private static final HashMap<String, BaseMapper> QUESTION_MAP = new HashMap<>();
    private static final HashMap<BaseMapper, SolutionMapperInterface> SOLUTION_MAP = new HashMap<>();
    @Resource
    private PublisherQuestionsMapper publisherQuestionsMapper;
    @Resource
    private PubChoiceQuestionMapper pubChoiceQuestionMapper;
    @Resource
    private PubChoiceSolutionMapper pubChoiceSolutionMapper;
    @Resource
    private PubFillQuestionMapper pubFillQuestionMapper;
    @Resource
    private PubFillSolutionMapper pubFillSolutionMapper;
    @Resource
    private PubMatchQuestionMapper pubMatchQuestionMapper;
    @Resource
    private PubMatchSolutionMapper pubMatchSolutionMapper;
    @Resource
    private PubSortQuestionMapper pubSortQuestionMapper;
    @Resource
    private PubSortSolutionMapper pubSortSolutionMapper;
    @Resource
    private PubJudgeMapper pubJudgeMapper;
    @Resource
    private PubEssayMapper pubEssayMapper;

    private void initTable() {
        QUESTION_MAP.put(CHOICE, pubChoiceQuestionMapper);
        QUESTION_MAP.put(SINGLE_CHOICE, pubChoiceQuestionMapper);
        QUESTION_MAP.put(MULTI_CHOICE, pubChoiceQuestionMapper);
        QUESTION_MAP.put(FILL, pubFillQuestionMapper);
        QUESTION_MAP.put(MATCH, pubMatchQuestionMapper);
        QUESTION_MAP.put(SORT, pubSortQuestionMapper);
        QUESTION_MAP.put(JUDGE, pubJudgeMapper);
        QUESTION_MAP.put(ESSAY, pubEssayMapper);

        SOLUTION_MAP.put(pubChoiceQuestionMapper, pubChoiceSolutionMapper);
        SOLUTION_MAP.put(pubFillQuestionMapper, pubFillSolutionMapper);
        SOLUTION_MAP.put(pubMatchQuestionMapper, pubMatchSolutionMapper);
        SOLUTION_MAP.put(pubSortQuestionMapper, pubSortSolutionMapper);
    }

    /**
     * 返回一个格式化作业题目
     *
     * @param dispId 总题库题目id
     * @return 一个格式化的题目
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public PubVO selectOneFormatted(Integer dispId) {
        List<PubVO> result = PublisherQuestionsFormatter.format(
                selectOne(dispId));
        return result == null ? null : result.get(0);
    }

    /**
     * 返回一本书内所有的格式化的题目
     *
     * @param bookId 书籍id
     * @return 这本书的所有题目列表
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<PubVO> selectAllFormatted(Integer bookId) {
        HashMap result;
        QueryWrapper<PublisherQuestions> wrapper = new QueryWrapper<>();
        wrapper.eq("book_id", bookId);
        List<PublisherQuestions> questionDisps = publisherQuestionsMapper.selectList(wrapper);
        return questionDisps.stream()
                .map(PublisherQuestions::getPubQuesId)
                .map(this::selectOneFormatted)
                .collect(Collectors.toList());
    }

    /**
     * 移除一个题目
     *
     * @param dispId 总题库题目id
     * @return 是否移除成功
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean remove(Integer dispId) {
        if (QUESTION_MAP.isEmpty()) {
            initTable();
        }
        PublisherQuestions question = publisherQuestionsMapper.selectById(dispId);
        BaseMapper questionMapper = QUESTION_MAP.get(question.getPubQuesType());
        Integer quesId = ((PubQuestionInterface) QUESTION_MAP.get(question.getPubQuesType())
                .selectById(question.getPubQuesSpecificId())).getQuesId();
        SolutionMapperInterface solutionMapper = SOLUTION_MAP.get(questionMapper);

        if (solutionMapper != null) {
            return (solutionMapper.deleteByQuestionId(quesId) > 0) &&
                    (questionMapper.deleteById(quesId) == 1) &&
                    (publisherQuestionsMapper.deleteById(dispId) == 1);
        } else {
            return (questionMapper.deleteById(quesId) == 1) &&
                    (publisherQuestionsMapper.deleteById(dispId) == 1);
        }
    }

    /**
     * 移除一个序列的题目
     *
     * @param dispIds 总题库题目id列表
     * @return 是否移除成功
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean removeList(List<Integer> dispIds) {
        return dispIds.stream()
                .map(this::remove)
                .reduce((l, r) -> (l && r))
                .get();
    }

    /**
     * 选择一个题目
     *
     * @param dispId 总题库题目id
     * @return 一个题目内容和相关信息
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public HashMap<PublisherQuestions, Object> selectOne(Integer dispId) {
        if (QUESTION_MAP.isEmpty()) {
            initTable();
        }
        PublisherQuestions pubQuestion = publisherQuestionsMapper.selectById(dispId);
        if (pubQuestion == null) {
            return null;
        }
        Integer quesId = ((PubQuestionInterface) QUESTION_MAP.get(pubQuestion.getPubQuesType())
                .selectById(pubQuestion.getPubQuesSpecificId())).getQuesId();
        if (quesId == null) {
            return null;
        }

        BaseMapper questionMapper = QUESTION_MAP.get(pubQuestion.getPubQuesType());
        SolutionMapperInterface solutionMapper = SOLUTION_MAP.get(questionMapper);
        HashMap<PublisherQuestions, Object> result = new HashMap<>();
        if (solutionMapper == null) {
            result.put(pubQuestion, questionMapper.selectById(quesId));
        } else {
            HashMap<Object, List> question = new HashMap<>();
            Object ques = questionMapper.selectById(quesId);
            if (ques instanceof PubFillQuestion) {
                ((PubFillQuestion) ques).setQuesContent(
                        convertFillQuestionContent(
                                ((PubFillQuestion) ques).getQuesContent()));
            }
            question.put(ques,
                    solutionMapper.selectByQuestionId(quesId));
            result.put(pubQuestion, question);
        }
        return result;
    }

    /**
     * 分页选择一本书的题目
     *
     * @param bookId      一本书的id
     * @param currentPage 当前页码
     * @return 一个题目列表
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public HashMap<PublisherQuestions, Object> selectAllByPage(Integer bookId, Integer currentPage) {
        QueryWrapper<PublisherQuestions> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("book_id", bookId);
        Page<PublisherQuestions> questionsPage = new Page<>(currentPage, Parameters.PAGE_SIZE);
        Page<PublisherQuestions> iPage = publisherQuestionsMapper.selectPage(questionsPage, queryWrapper);

        if (currentPage <= iPage.getPages()) {
            HashMap<PublisherQuestions, Object> questions = new HashMap<>();
            iPage.getRecords().forEach(record ->
                    questions.putAll(selectOne(record.getPubQuesId())));
            return questions;
        } else {
            return null;
        }
    }

    /**
     * 选择一本书的选择题
     *
     * @param bookId 一本书的id
     * @return 一些选择题及相关信息
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public HashMap<PublisherQuestions, HashMap<PubChoiceQuestion, List<PubChoiceSolution>>> selectChoice(
            Integer bookId) {
        HashMap<PublisherQuestions, HashMap<PubChoiceQuestion, List<PubChoiceSolution>>> questions =
                new HashMap<>();
        publisherQuestionsMapper.getByBookIdAndQuesType(bookId, CHOICE)
                .forEach(questionDisp -> {
                    HashMap<PubChoiceQuestion, List<PubChoiceSolution>> question = new HashMap<>();
                    PubChoiceQuestion ques = pubChoiceQuestionMapper.selectById(questionDisp.getPubQuesSpecificId());
                    question.put(ques, pubChoiceSolutionMapper.selectByQuestionId(ques.getQuesId()));
                    questions.put(questionDisp, question);
                });
        return questions.isEmpty() ? null : questions;
    }

    /**
     * 选择一本书的单选题
     *
     * @param bookId 一本书的id
     * @return 一些选择题及相关信息
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public HashMap<PublisherQuestions, HashMap<PubChoiceQuestion, List<PubChoiceSolution>>> selectSingleChoice(
            Integer bookId) {
        HashMap<PublisherQuestions, HashMap<PubChoiceQuestion, List<PubChoiceSolution>>> questions =
                new HashMap<>();
        publisherQuestionsMapper.getByBookIdAndQuesType(bookId, SINGLE_CHOICE)
                .forEach(questionDisp -> {
                    HashMap<PubChoiceQuestion, List<PubChoiceSolution>> question = new HashMap<>();
                    PubChoiceQuestion ques = pubChoiceQuestionMapper.selectById(questionDisp.getPubQuesSpecificId());
                    question.put(ques, pubChoiceSolutionMapper.selectByQuestionId(ques.getQuesId()));
                    questions.put(questionDisp, question);
                });
        return questions.isEmpty() ? null : questions;
    }

    /**
     * 选择一本书的多选题
     *
     * @param bookId 一本书的id
     * @return 一些选择题及相关信息
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public HashMap<PublisherQuestions, HashMap<PubChoiceQuestion, List<PubChoiceSolution>>> selectMultiChoice(
            Integer bookId) {
        HashMap<PublisherQuestions, HashMap<PubChoiceQuestion, List<PubChoiceSolution>>> questions =
                new HashMap<>();
        publisherQuestionsMapper.getByBookIdAndQuesType(bookId, MULTI_CHOICE)
                .forEach(questionDisp -> {
                    HashMap<PubChoiceQuestion, List<PubChoiceSolution>> question = new HashMap<>();
                    PubChoiceQuestion ques = pubChoiceQuestionMapper.selectById(questionDisp.getPubQuesSpecificId());
                    question.put(ques, pubChoiceSolutionMapper.selectByQuestionId(ques.getQuesId()));
                    questions.put(questionDisp, question);
                });
        return questions.isEmpty() ? null : questions;
    }

    /**
     * 选择一本书的填空题
     *
     * @param bookId 一本书的id
     * @return 一些填空题及相关信息
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public HashMap<PublisherQuestions, HashMap<PubFillQuestion, List<PubFillSolution>>> selectFill(Integer bookId) {
        HashMap<PublisherQuestions, HashMap<PubFillQuestion, List<PubFillSolution>>> questions = new HashMap<>();
        publisherQuestionsMapper.getByBookIdAndQuesType(bookId, FILL)
                .forEach(questionDisp -> {
                    HashMap<PubFillQuestion, List<PubFillSolution>> question = new HashMap<>();
                    PubFillQuestion ques = pubFillQuestionMapper.selectById(questionDisp.getPubQuesSpecificId());
                    ques.setQuesContent(
                            convertFillQuestionContent(
                                    ques.getQuesContent()));
                    question.put(ques, pubFillSolutionMapper.selectByQuestionId(ques.getQuesId()));
                    questions.put(questionDisp, question);
                });
        return questions.isEmpty() ? null : questions;
    }

    private String convertFillQuestionContent(String solutionContent) {
        int i = 1;
        String backupContent;
        do {
            backupContent = solutionContent;
            solutionContent = solutionContent.replaceFirst("¿", " " + i + "._____");
            i++;
        } while ((!solutionContent.equals(backupContent)));
        return solutionContent;
    }

    /**
     * 选择一本书的连线题
     *
     * @param bookId 一本书的id
     * @return 一些连线题及相关信息
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public HashMap<PublisherQuestions, HashMap<PubMatchQuestion, List<PubMatchSolution>>> selectMatch(Integer bookId) {
        HashMap<PublisherQuestions, HashMap<PubMatchQuestion, List<PubMatchSolution>>> questions = new HashMap<>();
        publisherQuestionsMapper.getByBookIdAndQuesType(bookId, MATCH)
                .forEach(questionDisp -> {
                    HashMap<PubMatchQuestion, List<PubMatchSolution>> question = new HashMap<>();
                    PubMatchQuestion ques = pubMatchQuestionMapper.selectById(questionDisp.getPubQuesSpecificId());
                    question.put(ques, pubMatchSolutionMapper.selectByQuestionId(ques.getQuesId()));
                    questions.put(questionDisp, question);
                });
        return questions.isEmpty() ? null : questions;
    }

    /**
     * 选择一本书的排序题
     *
     * @param bookId 一本书的id
     * @return 一些排序题及相关信息
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public HashMap<PublisherQuestions, HashMap<PubSortQuestion, List<PubSortSolution>>> selectSort(Integer bookId) {
        HashMap<PublisherQuestions, HashMap<PubSortQuestion, List<PubSortSolution>>> questions = new HashMap<>();
        publisherQuestionsMapper.getByBookIdAndQuesType(bookId, SORT)
                .forEach(questionDisp -> {
                    HashMap<PubSortQuestion, List<PubSortSolution>> question = new HashMap<>();
                    PubSortQuestion ques = pubSortQuestionMapper.selectById(questionDisp.getPubQuesSpecificId());
                    question.put(ques, pubSortSolutionMapper.selectByQuestionId(ques.getQuesId()));
                    questions.put(questionDisp, question);
                });
        return questions.isEmpty() ? null : questions;
    }

    /**
     * 选择一本书的判断题
     *
     * @param bookId 一本书的id
     * @return 一些判断题及相关信息
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public HashMap<PublisherQuestions, PubJudge> selectJudge(Integer bookId) {
        HashMap<PublisherQuestions, PubJudge> questions = new HashMap<>();
        publisherQuestionsMapper.getByBookIdAndQuesType(bookId, JUDGE)
                .forEach(questionDisp -> questions.put(
                        questionDisp, pubJudgeMapper.selectById(questionDisp.getPubQuesSpecificId())));
        return questions.isEmpty() ? null : questions;
    }

    /**
     * 选择一本书的作文题
     *
     * @param bookId 一本书的id
     * @return 一些作文题及相关信息
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public HashMap<PublisherQuestions, PubEssay> selectEssay(Integer bookId) {
        HashMap<PublisherQuestions, PubEssay> questions = new HashMap<>();
        publisherQuestionsMapper.getByBookIdAndQuesType(bookId, ESSAY)
                .forEach(questionDisp -> questions.put(
                        questionDisp, pubEssayMapper.selectById(questionDisp.getPubQuesSpecificId())));
        return questions.isEmpty() ? null : questions;
    }

    /**
     * 保存一道选择题
     *
     * @param bookId    题目所属书籍id
     * @param question  问题
     * @param solutions 问题的选项及答案
     * @return 是否保存成功
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean saveChoice(Integer bookId, PubChoiceQuestion question, List<PubChoiceSolution> solutions) {
        pubChoiceQuestionMapper.insert(question);
        Integer quesId = question.getQuesId();
        PublisherQuestions questionRecord = new PublisherQuestions();
        questionRecord.setPubQuesSpecificId(quesId);
        questionRecord.setPubQuesType(CHOICE);
        questionRecord.setBookId(bookId);
        publisherQuestionsMapper.addPublisherQuestions(questionRecord);
        return solutions.stream()
                .peek(solution -> solution.setQuesId(quesId))
                .noneMatch(solution -> pubChoiceSolutionMapper.insert(solution) != 1);
    }

    /**
     * 保存一道单选题
     *
     * @param bookId    题目所属书籍id
     * @param question  问题
     * @param solutions 问题的选项及答案
     * @return 是否保存成功
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean saveSingleChoice(Integer bookId, PubChoiceQuestion question, List<PubChoiceSolution> solutions) {
        pubChoiceQuestionMapper.insert(question);
        Integer quesId = question.getQuesId();
        PublisherQuestions questionRecord = new PublisherQuestions();
        questionRecord.setPubQuesSpecificId(quesId);
        questionRecord.setPubQuesType(SINGLE_CHOICE);


        questionRecord.setBookId(bookId);
        publisherQuestionsMapper.addPublisherQuestions(questionRecord);
        return solutions.stream()
                .peek(solution -> solution.setQuesId(quesId))
                .noneMatch(solution -> pubChoiceSolutionMapper.insert(solution) != 1);
    }

    /**
     * 保存一道多选题
     *
     * @param bookId    题目所属书籍id
     * @param question  问题
     * @param solutions 问题的选项及答案
     * @return 是否保存成功
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean saveMultiChoice(Integer bookId, PubChoiceQuestion question, List<PubChoiceSolution> solutions) {
        pubChoiceQuestionMapper.insert(question);
        Integer quesId = question.getQuesId();
        PublisherQuestions questionRecord = new PublisherQuestions();
        questionRecord.setPubQuesSpecificId(quesId);
        questionRecord.setPubQuesType(MULTI_CHOICE);
        questionRecord.setBookId(bookId);
        publisherQuestionsMapper.addPublisherQuestions(questionRecord);
        return solutions.stream()
                .peek(solution -> solution.setQuesId(quesId))
                .noneMatch(solution -> pubChoiceSolutionMapper.insert(solution) != 1);
    }

    /**
     * 保存一道填空题
     *
     * @param bookId    题目所属书籍id
     * @param question  问题
     * @param solutions 问题的答案
     * @return 是否保存成功
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean saveFill(Integer bookId, PubFillQuestion question, List<PubFillSolution> solutions) {
        pubFillQuestionMapper.insert(question);
        Integer quesId = question.getQuesId();
        PublisherQuestions questionRecord = new PublisherQuestions();
        questionRecord.setPubQuesSpecificId(quesId);
        questionRecord.setPubQuesType(FILL);
        questionRecord.setBookId(bookId);
        publisherQuestionsMapper.addPublisherQuestions(questionRecord);
        return solutions.stream()
                .peek(solution -> solution.setQuesId(quesId))
                .noneMatch(solution -> pubFillSolutionMapper.insert(solution) != 1);
    }

    /**
     * 保存一道连线题
     *
     * @param bookId    题目所属书籍id
     * @param question  问题
     * @param solutions 问题的答案
     * @return 是否保存成功
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean saveMatch(Integer bookId, PubMatchQuestion question, List<PubMatchSolution> solutions) {
        pubMatchQuestionMapper.insert(question);
        Integer quesId = question.getQuesId();
        PublisherQuestions questionRecord = new PublisherQuestions();
        questionRecord.setPubQuesSpecificId(quesId);
        questionRecord.setPubQuesType(MATCH);
        questionRecord.setBookId(bookId);
        publisherQuestionsMapper.addPublisherQuestions(questionRecord);
        return solutions.stream()
                .peek(solution -> solution.setQuesId(quesId))
                .noneMatch(solution -> pubMatchSolutionMapper.insert(solution) != 1);
    }

    /**
     * 保存一道排序题
     *
     * @param bookId    题目所属书籍id
     * @param question  问题
     * @param solutions 问题的答案
     * @return 是否保存成功
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean saveSort(Integer bookId, PubSortQuestion question, List<PubSortSolution> solutions) {
        if (pubSortQuestionMapper.insert(question) != 1) {
            return false;
        }
        Integer quesId = question.getQuesId();
        if (quesId == null) {
            return false;
        }
        PublisherQuestions questionRecord = new PublisherQuestions();
        questionRecord.setPubQuesSpecificId(quesId);
        questionRecord.setPubQuesType(SORT);
        questionRecord.setBookId(bookId);
        if (publisherQuestionsMapper.addPublisherQuestions(questionRecord) != 1) {
            return false;
        }
        return solutions.stream()
                .peek(solution -> solution.setQuesId(quesId))
                .noneMatch(solution -> pubSortSolutionMapper.insert(solution) != 1);
    }

    /**
     * 保存一道判断题
     *
     * @param bookId   题目所属书籍id
     * @param question 问题和答案
     * @return 是否保存成功
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean saveJudge(Integer bookId, PubJudge question) {
        if (pubJudgeMapper.insert(question) != 1) {
            return false;
        }
        Integer quesId = question.getQuesId();
        if (quesId == null) {
            return false;
        }
        PublisherQuestions questionRecord = new PublisherQuestions();
        questionRecord.setPubQuesSpecificId(quesId);
        questionRecord.setPubQuesType(JUDGE);
        questionRecord.setBookId(bookId);
        return publisherQuestionsMapper.addPublisherQuestions(questionRecord) == 1;
    }

    /**
     * 保存一道判断题
     *
     * @param bookId   题目所属书籍id
     * @param question 问题和范文
     * @return 是否保存成功
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean saveEssay(Integer bookId, PubEssay question) {
        if (pubEssayMapper.insert(question) != 1) {
            return false;
        }
        Integer quesId = question.getQuesId();
        if (quesId == null) {
            return false;
        }
        PublisherQuestions questionRecord = new PublisherQuestions();
        questionRecord.setPubQuesSpecificId(quesId);
        questionRecord.setPubQuesType(ESSAY);
        questionRecord.setBookId(bookId);
        return publisherQuestionsMapper.addPublisherQuestions(questionRecord) == 1;
    }
}
