package cn.net.sybt.springboot.controller;

import cn.net.sybt.springboot.bean.*;
import cn.net.sybt.springboot.service.PublisherQuestionsService;
import cn.net.sybt.springboot.utils.PublisherQuestionsFormatter;
import cn.net.sybt.springboot.utils.ResultEnum;
import cn.net.sybt.springboot.utils.ResultModel;
import cn.net.sybt.springboot.vo.PubVO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

import static cn.net.sybt.springboot.utils.RetResponse.resp;

@RestController
public class PublisherQuestionsController {
    private final static List<Function<Integer, HashMap>> SELECT_TABLE = new ArrayList<>();
    String msg;

    @Resource
    PublisherQuestionsService publisherQuestionsService;

    private void initTable() {
        SELECT_TABLE.add(publisherQuestionsService::selectChoice);
        SELECT_TABLE.add(publisherQuestionsService::selectSingleChoice);
        SELECT_TABLE.add(publisherQuestionsService::selectMultiChoice);
        SELECT_TABLE.add(publisherQuestionsService::selectEssay);
        SELECT_TABLE.add(publisherQuestionsService::selectFill);
        SELECT_TABLE.add(publisherQuestionsService::selectJudge);
        SELECT_TABLE.add(publisherQuestionsService::selectMatch);
        SELECT_TABLE.add(publisherQuestionsService::selectSort);
    }

    private ResultModel<List<PubVO>> getSelectResult(List<PubVO> questionTable) {
        if (questionTable == null || questionTable.isEmpty()) {
            msg = "所查询的题库正在建设中...";
            return resp(ResultEnum.NOT_FOUND.code, msg);
        } else {
            msg = "已找到" + questionTable.size() + "个题目";
            return resp(ResultEnum.SUCCESS.code, msg, questionTable);
        }
    }

    /**
     * 选择一本书所有的题目，具体题目格式见选择各个题型部分
     *
     * @param bookIdContainer 用于接收前端传来的bookId
     * @return 封装好的题目列表
     * @see cn.net.sybt.springboot.vo.PubVO
     * response code: 200
     * * msg: "已找到" + 问题数量 + "个题目"
     * response code: 404
     * * msg: "所查询的题库正在建设中..."
     */
    @RequestMapping({
            "/teacher/publisher-questions/get-all",
            "/admin/publisher-questions/get-all"})
    public ResultModel<List<PubVO>> selectAll(@RequestBody PublisherQuestions bookIdContainer) {
        if (SELECT_TABLE.isEmpty()) {
            initTable();
        }
        HashMap questionTable = new HashMap<>();
        SELECT_TABLE.stream().forEach(selectFunc -> {
            HashMap table = selectFunc.apply(bookIdContainer.getBookId());
            if (table != null) {
                questionTable.putAll(table);
            }
        });
        return getSelectResult(
                PublisherQuestionsFormatter.format(questionTable));
    }

    /**
     * 分页获取一本书所有的题目id，具体题目格式见选择各个题型部分
     *
     * @param bookPage key为bookId，value为页码
     * @return 封装好的题目列表
     * @see cn.net.sybt.springboot.vo.PubVO
     * response code: 200
     * * msg: "已找到" + 问题数量 + "个题目"
     * response code: 404
     * * msg: "所查询的题库正在建设中..."
     */
    @RequestMapping({
            "/teacher/publisher-questions/get-all-by-page",
            "/admin/publisher-questions/get-all-by-page"})
    public ResultModel<List<PubVO>> selectAllByPage(@RequestBody HashMap<Integer, Integer> bookPage) {
        final HashMap[] questionTable = new HashMap[1];
        bookPage.forEach((bookId, page) ->
                questionTable[0] = publisherQuestionsService.selectAllByPage(bookId, page));
        if (questionTable[0] == null) {
            msg = "已经是最后一页了";
            return resp(ResultEnum.NOT_FOUND_PAGES.code, msg);
        } else {
            return getSelectResult(
                    PublisherQuestionsFormatter.format(questionTable[0]));
        }
    }

    /**
     * 根据总题库id选择一个题目，具体题目格式见选择各个题型部分
     *
     * @param quesDispIdContainer 用于接收前端传来的总题库的id
     * @return 封装好的题目
     * @see cn.net.sybt.springboot.vo.PubVO
     * response code: 200
     * * msg: "已找到"
     * response code: 404
     * * msg: "题目丢失了..."
     */
    @RequestMapping({
            "/teacher/publisher-questions/get-one",
            "/student/publisher-questions/get-one"})
    public ResultModel<PubVO> selectOne(@RequestBody PublisherQuestions quesDispIdContainer) {
        PubVO result = PublisherQuestionsFormatter.format(
                publisherQuestionsService.selectOne(quesDispIdContainer.getPubQuesId())).get(0);
        if (result == null) {
            msg = "题目丢失了...";
            return resp(ResultEnum.NOT_FOUND.code, msg);
        } else {
            msg = "已找到";
            return resp(ResultEnum.SUCCESS.code, msg, result);
        }
    }

    /**
     * 选择一个题目列表，具体题目格式见选择各个题型部分
     *
     * @param bookIds 用于接收前端传来的总题库的id列表
     * @return 封装好的题目列表
     * @see cn.net.sybt.springboot.vo.PubVO
     * response code: 200
     * * msg: "已找到"
     * response code: 404
     * * msg: "题目丢失了..."
     */
    @RequestMapping({
            "/teacher/publisher-questions/get-list",
            "/student/publisher-questions/get-list"})
    public ResultModel<List<PubVO>> selectList(@RequestBody List<Integer> bookIds) {
        HashMap result = bookIds.stream()
                .map(bookId -> publisherQuestionsService.selectOne(bookId))
                .reduce((left, right) -> {
                    left.putAll(right);
                    return left;
                }).get();
        if (result.isEmpty()) {
            msg = "题目丢失了...";
            return resp(ResultEnum.NOT_FOUND.code, msg);
        } else {
            msg = "已找到";
            return resp(ResultEnum.SUCCESS.code, msg,
                    PublisherQuestionsFormatter.format(result));
        }
    }

    /**
     * 选择一本书所有的选择题
     *
     * @param bookIdContainer 用于接收前端传来的bookId
     * @return 封装好的题目列表
     * @see cn.net.sybt.springboot.vo.PubVO
     * question:
     * * quesContent: String
     * * quesId: Integer
     * * solutionLink: String
     * * solutionText: String
     * solutions: List
     * * choiceContent: String
     * * choiceLink: String
     * * isTrue: Integer
     * @see cn.net.sybt.springboot.consts.DatabaseValue
     * * quesId: Integer
     *
     * response code: 200
     * * msg: "已找到" + 问题数量 + "个题目"
     * response code: 404
     * * msg: "所查询的题库正在建设中..."
     */
    @RequestMapping({
            "/teacher/publisher-questions/get-choice",
            "/admin/publisher-questions/get-choice"})
    public ResultModel<List<PubVO>> selectChoice(@RequestBody PublisherQuestions bookIdContainer) {
        return getSelectResult(
                PublisherQuestionsFormatter.format(
                        publisherQuestionsService.selectChoice(bookIdContainer.getBookId())));
    }

    /**
     * 选择一本书所有的单选题
     *
     * @param bookIdContainer 用于接收前端传来的bookId
     * @return 封装好的题目列表
     * @see cn.net.sybt.springboot.vo.PubVO
     * question:
     * * quesContent: String
     * * quesId: Integer
     * * solutionLink: String
     * * solutionText: String
     * solutions: List
     * * choiceContent: String
     * * choiceLink: String
     * * isTrue: Integer
     * @see cn.net.sybt.springboot.consts.DatabaseValue
     * * quesId: Integer
     * <p>
     * response code: 200
     * * msg: "已找到" + 问题数量 + "个题目"
     * response code: 404
     * * msg: "所查询的题库正在建设中..."
     */
    @RequestMapping({
            "/teacher/publisher-questions/get-single-choice",
            "/admin/publisher-questions/get-single-choice"})
    public ResultModel<List<PubVO>> selectSingleChoice(@RequestBody PublisherQuestions bookIdContainer) {
        return getSelectResult(
                PublisherQuestionsFormatter.format(
                        publisherQuestionsService.selectSingleChoice(bookIdContainer.getBookId())));
    }

    /**
     * 选择一本书所有的多选题
     *
     * @param bookIdContainer 用于接收前端传来的bookId
     * @return 封装好的题目列表
     * @see cn.net.sybt.springboot.vo.PubVO
     * question:
     * * quesContent: String
     * * quesId: Integer
     * * solutionLink: String
     * * solutionText: String
     * solutions: List
     * * choiceContent: String
     * * choiceLink: String
     * * isTrue: Integer
     * @see cn.net.sybt.springboot.consts.DatabaseValue
     * * quesId: Integer
     * <p>
     * response code: 200
     * * msg: "已找到" + 问题数量 + "个题目"
     * response code: 404
     * * msg: "所查询的题库正在建设中..."
     */
    @RequestMapping({
            "/teacher/publisher-questions/get-multi-choice",
            "/admin/publisher-questions/get-multi-choice"})
    public ResultModel<List<PubVO>> selectMultiChoice(@RequestBody PublisherQuestions bookIdContainer) {
        return getSelectResult(
                PublisherQuestionsFormatter.format(
                        publisherQuestionsService.selectMultiChoice(bookIdContainer.getBookId())));
    }

    /**
     * 选择一本书所有的作文题
     *
     * @param bookIdContainer 用于接收前端传来的bookId
     * @return 封装好的题目列表
     * @see cn.net.sybt.springboot.vo.PubVO
     * question:
     * * quesId: Integer
     * * quesLink: String
     * * solutionLink: String
     *
     * response code: 200
     * * msg: "已找到" + 问题数量 + "个题目"
     * response code: 404
     * * msg: "所查询的题库正在建设中..."
     */
    @RequestMapping({
            "/teacher/publisher-questions/get-essay",
            "/admin/publisher-questions/get-essay"})
    public ResultModel<List<PubVO>> selectEssay(@RequestBody PublisherQuestions bookIdContainer) {
        return getSelectResult(
                PublisherQuestionsFormatter.format(
                        publisherQuestionsService.selectEssay(bookIdContainer.getBookId())));
    }

    /**
     * 选择一本书所有的填空题
     *
     * @param bookIdContainer 用于接收前端传来的bookId
     * @return 封装好的题目列表
     * @see cn.net.sybt.springboot.vo.PubVO
     * question:
     * * quesContent: String
     * * quesId: Integer
     * * solutionLink: String
     * * solutionText: String
     * solutions: List
     * * quesId: Integer
     * * solutionContent: String
     * * solutionNo: Integer
     *
     * response code: 200
     * * msg: "已找到" + 问题数量 + "个题目"
     * response code: 404
     * * msg: "所查询的题库正在建设中..."
     */
    @RequestMapping({
            "/teacher/publisher-questions/get-fill",
            "/admin/publisher-questions/get-fill"})
    public ResultModel<List<PubVO>> selectFill(@RequestBody PublisherQuestions bookIdContainer) {
        return getSelectResult(
                PublisherQuestionsFormatter.format(
                        publisherQuestionsService.selectFill(bookIdContainer.getBookId())));
    }

    /**
     * 选择一本书所有的判断题
     *
     * @param bookIdContainer 用于接收前端传来的bookId
     * @return 封装好的题目列表
     * @see cn.net.sybt.springboot.vo.PubVO
     * question:
     * * isTrue: Integer
     * * quesContent: String
     * * quesId: Integer
     * * quesLink: String
     * * solutionLink: String
     * * solutionText: String
     *
     * response code: 200
     * * msg: "已找到" + 问题数量 + "个题目"
     * response code: 404
     * * msg: "所查询的题库正在建设中..."
     */
    @RequestMapping({
            "/teacher/publisher-questions/get-judge",
            "/admin/publisher-questions/get-judge"})
    public ResultModel<List<PubVO>> selectJudge(@RequestBody PublisherQuestions bookIdContainer) {
        return getSelectResult(
                PublisherQuestionsFormatter.format(
                        publisherQuestionsService.selectJudge(bookIdContainer.getBookId())));
    }

    /**
     * 选择一本书所有的连线题
     *
     * @param bookIdContainer 用于接收前端传来的bookId
     * @return 封装好的题目列表
     * @see cn.net.sybt.springboot.vo.PubVO
     * question:
     * * quesContent: String
     * * quesId: Integer
     * * solutionLink: String
     * * solutionText: String
     * solutions: List
     * * quesId: Integer
     * * leftLink: String
     * * leftValue: String
     * * rightLink: String
     * * rightValue: String
     *
     * response code: 200
     * * msg: "已找到" + 问题数量 + "个题目"
     * response code: 404
     * * msg: "所查询的题库正在建设中..."
     */
    @RequestMapping({
            "/teacher/publisher-questions/get-match",
            "/admin/publisher-questions/get-match"})
    public ResultModel<List<PubVO>> selectMatch(@RequestBody PublisherQuestions bookIdContainer) {
        return getSelectResult(
                PublisherQuestionsFormatter.format(
                        publisherQuestionsService.selectMatch(bookIdContainer.getBookId())));
    }

    /**
     * 选择一本书所有的排序题
     *
     * @param bookIdContainer 用于接收前端传来的bookId
     * @return 封装好的题目列表
     * @see cn.net.sybt.springboot.vo.PubVO
     * question:
     * * quesContent: String
     * * quesId: Integer
     * * solutionLink: String
     * * solutionText: String
     * solutions: List
     * * quesId: Integer
     * * solutionContent: String
     * * sortNo: Integer
     *
     * response code: 200
     * * msg: "已找到" + 问题数量 + "个题目"
     * response code: 404
     * * msg: "所查询的题库正在建设中..."
     */
    @RequestMapping({
            "/teacher/publisher-questions/get-sort",
            "/admin/publisher-questions/get-sort"})
    public ResultModel<List<PubVO>> selectSort(@RequestBody PublisherQuestions bookIdContainer) {
        return getSelectResult(
                PublisherQuestionsFormatter.format(
                        publisherQuestionsService.selectSort(bookIdContainer.getBookId())));
    }

    private ResultModel<Boolean> getAddResult(@RequestBody Boolean result) {
        if (result == null || result == false) {
            msg = "数据库出错了，没有保存成功...";
            return resp(ResultEnum.INTERNAL_SERVER_ERROR.code, msg);
        } else {
            msg = "已添加";
            return resp(ResultEnum.SUCCESS.code, msg, result);
        }
    }

    /**
     * 添加一道选择题
     *
     * @param question 用于接收前端传来的题目内容和相关信息
     * @return 是否添加成功
     * question:
     * * quesContent: String
     * * quesId: Integer
     * * solutionLink: String
     * * solutionText: String
     * solutions: List
     * * choiceContent: String
     * * choiceLink: String
     * * isTrue: Integer
     * @see cn.net.sybt.springboot.consts.DatabaseValue
     * * quesId: Integer
     * @see cn.net.sybt.springboot.vo.PubVO
     *
     * response code: 200
     * * msg: "已添加"
     * response code: 500
     * * msg: "数据库出错了，没有保存成功..."
     */
    @RequestMapping("/admin/publisher-questions/add-choice")
    public ResultModel<Boolean> addChoice(
            @RequestBody PubVO<PubChoiceQuestion, PubChoiceSolution> question) {
        return getAddResult(publisherQuestionsService.saveChoice(
                question.getBookId(),
                question.getQuestion(),
                question.getSolutions()));
    }

    /**
     * 添加一道单选题
     *
     * @param question 用于接收前端传来的题目内容和相关信息
     * @return 是否添加成功
     * question:
     * * quesContent: String
     * * quesId: Integer
     * * solutionLink: String
     * * solutionText: String
     * solutions: List
     * * choiceContent: String
     * * choiceLink: String
     * * isTrue: Integer
     * @see cn.net.sybt.springboot.consts.DatabaseValue
     * * quesId: Integer
     * @see cn.net.sybt.springboot.vo.PubVO
     * <p>
     * response code: 200
     * * msg: "已添加"
     * response code: 500
     * * msg: "数据库出错了，没有保存成功..."
     */
    @RequestMapping("/admin/publisher-questions/add-single-choice")
    public ResultModel<Boolean> addSingleChoice(
            @RequestBody PubVO<PubChoiceQuestion, PubChoiceSolution> question) {
        return getAddResult(publisherQuestionsService.saveSingleChoice(
                question.getBookId(),
                question.getQuestion(),
                question.getSolutions()));
    }

    /**
     * 添加一道多选题
     *
     * @param question 用于接收前端传来的题目内容和相关信息
     * @return 是否添加成功
     * question:
     * * quesContent: String
     * * quesId: Integer
     * * solutionLink: String
     * * solutionText: String
     * solutions: List
     * * choiceContent: String
     * * choiceLink: String
     * * isTrue: Integer
     * @see cn.net.sybt.springboot.consts.DatabaseValue
     * * quesId: Integer
     * @see cn.net.sybt.springboot.vo.PubVO
     * <p>
     * response code: 200
     * * msg: "已添加"
     * response code: 500
     * * msg: "数据库出错了，没有保存成功..."
     */
    @RequestMapping("/admin/publisher-questions/add-multi-choice")
    public ResultModel<Boolean> addMultiChoice(
            @RequestBody PubVO<PubChoiceQuestion, PubChoiceSolution> question) {
        return getAddResult(publisherQuestionsService.saveMultiChoice(
                question.getBookId(),
                question.getQuestion(),
                question.getSolutions()));
    }

    /**
     * 添加一道作文题
     *
     * @param question 用于接收前端传来的题目内容和相关信息
     * @return 是否添加成功
     * question:
     * * quesId: Integer
     * * quesLink: String
     * * solutionLink: String
     * @see cn.net.sybt.springboot.vo.PubVO
     *
     * response code: 200
     * * msg: "已添加"
     * response code: 500
     * * msg: "数据库出错了，没有保存成功..."
     */
    @RequestMapping("/admin/publisher-questions/add-essay")
    public ResultModel<Boolean> addEssay(
            @RequestBody PubVO<PubEssay, Object> question) {
        return getAddResult(publisherQuestionsService.saveEssay(
                question.getBookId(),
                question.getQuestion()));
    }

    /**
     * 添加一道填空题
     *
     * @param question 用于接收前端传来的题目内容和相关信息
     * @return 是否添加成功
     * question:
     * * quesContent: String
     * * quesId: Integer
     * * solutionLink: String
     * * solutionText: String
     * solutions: List
     * * quesId: Integer
     * * solutionContent: String
     * * solutionNo: Integer
     * @see cn.net.sybt.springboot.vo.PubVO
     *
     * response code: 200
     * * msg: "已添加"
     * response code: 500
     * * msg: "数据库出错了，没有保存成功..."
     */
    @RequestMapping("/admin/publisher-questions/add-fill")
    public ResultModel<Boolean> addFill(
            @RequestBody PubVO<PubFillQuestion, PubFillSolution> question) {
        return getAddResult(publisherQuestionsService.saveFill(
                question.getBookId(),
                question.getQuestion(),
                question.getSolutions()));
    }

    /**
     * 添加一道判断题
     *
     * @param question 用于接收前端传来的题目内容和相关信息
     * @return 是否添加成功
     * question:
     * * isTrue: Integer
     * * quesContent: String
     * * quesId: Integer
     * * quesLink: String
     * * solutionLink: String
     * * solutionText: String
     * @see cn.net.sybt.springboot.vo.PubVO
     *
     * response code: 200
     * * msg: "已添加"
     * response code: 500
     * * msg: "数据库出错了，没有保存成功..."
     */
    @RequestMapping("/admin/publisher-questions/add-judge")
    public ResultModel<Boolean> addJudge(
            @RequestBody PubVO<PubJudge, Object> question) {
        return getAddResult(publisherQuestionsService.saveJudge(
                question.getBookId(),
                question.getQuestion()));
    }

    /**
     * 添加一道填空题
     *
     * @param question 用于接收前端传来的题目内容和相关信息
     * @return 是否添加成功
     * question:
     * * quesContent: String
     * * quesId: Integer
     * * solutionLink: String
     * * solutionText: String
     * solutions: List
     * * quesId: Integer
     * * leftLink: String
     * * leftValue: String
     * * rightLink: String
     * * rightValue: String
     * @see cn.net.sybt.springboot.vo.PubVO
     *
     * response code: 200
     * * msg: "已添加"
     * response code: 500
     * * msg: "数据库出错了，没有保存成功..."
     */
    @RequestMapping("/admin/publisher-questions/add-match")
    public ResultModel<Boolean> addMatch(
            @RequestBody PubVO<PubMatchQuestion, PubMatchSolution> question) {
        return getAddResult(publisherQuestionsService.saveMatch(
                question.getBookId(),
                question.getQuestion(),
                question.getSolutions()));
    }

    /**
     * 添加一道填空题
     *
     * @param question 用于接收前端传来的题目内容和相关信息
     * @return 是否添加成功
     * question:
     * * quesContent: String
     * * quesId: Integer
     * * solutionLink: String
     * * solutionText: String
     * solutions: List
     * * quesId: Integer
     * * leftLink: String
     * * leftValue: String
     * * rightLink: String
     * * rightValue: String
     * @see cn.net.sybt.springboot.vo.PubVO
     *
     * response code: 200
     * * msg: "已添加"
     * response code: 500
     * * msg: "数据库出错了，没有保存成功..."
     */
    @RequestMapping("/admin/publisher-questions/add-sort")
    public ResultModel<Boolean> addSort(
            @RequestBody PubVO<PubSortQuestion, PubSortSolution> question) {
        return getAddResult(publisherQuestionsService.saveSort(
                question.getBookId(),
                question.getQuestion(),
                question.getSolutions()));
    }
}
