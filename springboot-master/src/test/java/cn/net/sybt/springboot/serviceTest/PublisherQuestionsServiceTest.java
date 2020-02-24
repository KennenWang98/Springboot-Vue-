package cn.net.sybt.springboot.serviceTest;

import cn.net.sybt.springboot.bean.*;
import cn.net.sybt.springboot.consts.DatabaseValue;
import cn.net.sybt.springboot.service.PublisherQuestionsService;
import cn.net.sybt.springboot.utils.PublisherQuestionsFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
public class PublisherQuestionsServiceTest {
    @Resource
    PublisherQuestionsService publisherQuestionsService;
    Integer bookId;

    @BeforeEach
    public void setUp() {
        bookId = Integer.MAX_VALUE - 5;
    }

    @Test
    public void removeAndJudge() {
        assert publisherQuestionsService.selectJudge(bookId) == null;
        assert publisherQuestionsService.saveJudge(bookId, new PubJudge(
                "问题1", "问题link", 0, "答案", "答案链接"
        ));
        assert publisherQuestionsService.selectJudge(bookId).size() == 1;
        publisherQuestionsService.selectJudge(bookId).forEach((key, value) -> {
            System.out.println(key + " -> " + value);
            publisherQuestionsService.remove(key.getPubQuesId());
        });
        assert publisherQuestionsService.selectJudge(bookId) == null;
    }

    @Test
    public void selectAllByPage() {
        List<PubJudge> pubJudges = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            PubJudge judge = new PubJudge(
                    "问题" + (i + 1), "问题link", 0, "答案", "答案链接");
            pubJudges.add(judge);
            assert publisherQuestionsService.saveJudge(
                    bookId, judge);
        }
        System.out.println(publisherQuestionsService.selectJudge(bookId));
        for (int i = 0; i < 3; i++) {
            System.out.println("page" + i + 1 + ": ");
            publisherQuestionsService.selectAllByPage(bookId, i + 1)
                    .forEach((dispId, question) ->
                            System.out.println(dispId + " -> " + question));
        }
        assert publisherQuestionsService.selectAllByPage(bookId, 4) == null;
    }

    @Test
    public void saveChoice() {
        PubChoiceQuestion question = new PubChoiceQuestion();
        PubChoiceSolution solution = new PubChoiceSolution();
        question.setQuesContent("问题内容");
        question.setSolutionLink("link");
        question.setSolutionText("text");
        solution.setChoiceContent("选项");
        solution.setChoiceLink("link");
        solution.setIsTrue(DatabaseValue.IS_TRUE);
        List<PubChoiceSolution> solutions = new ArrayList<>();
        solutions.add(solution);
        assert publisherQuestionsService.saveChoice(
                123, question, solutions);
        assert question.getQuesId() != null;
    }

    @Test
    public void saveEssay() {
        PubEssay question = new PubEssay();
        question.setQuesContent("link");
        question.setSolutionLink("link");
        assert publisherQuestionsService.saveEssay(
                123, question);
        assert question.getQuesId() != null;
    }

    @Test
    public void saveFill() {
        PubFillQuestion question = new PubFillQuestion();
        PubFillSolution solution = new PubFillSolution();
        question.setQuesContent("问题内容");
        question.setSolutionLink("link");
        question.setSolutionText("text");
        solution.setSolutionContent("content");
        solution.setSolutionNo(1);
        List<PubFillSolution> solutions = new ArrayList<>();
        solutions.add(solution);
        assert publisherQuestionsService.saveFill(
                123, question, solutions);
        assert question.getQuesId() != null;
    }

    @Test
    public void saveMatch() {
        PubMatchQuestion question = new PubMatchQuestion();
        PubMatchSolution solution = new PubMatchSolution();
        question.setQuesContent("问题内容");
        question.setSolutionLink("link");
        question.setSolutionText("text");
        solution.setLeftLink("left link");
        solution.setLeftValue("left value");
        solution.setRightLink("right link");
        solution.setRightValue("right value");
        List<PubMatchSolution> solutions = new ArrayList<>();
        solutions.add(solution);
        assert publisherQuestionsService.saveMatch(
                123, question, solutions);
        assert question.getQuesId() != null;
    }

    @Test
    public void saveSort() {
        PubSortQuestion question = new PubSortQuestion();
        PubSortSolution solution = new PubSortSolution();
        question.setQuesContent("问题内容");
        question.setSolutionLink("link");
        question.setSolutionText("text");
        solution.setSolutionContent("content");
        solution.setSortNo(1);
        List<PubSortSolution> solutions = new ArrayList<>();
        solutions.add(solution);
        assert publisherQuestionsService.saveSort(
                123, question, solutions);
        assert question.getQuesId() != null;
    }

    @Test
    public void selectOne() {
        assert publisherQuestionsService.selectOne(41) != null;
        System.out.println(
                PublisherQuestionsFormatter.format(
                        publisherQuestionsService.selectOne(41)));
    }

    @Test
    void selectAllFormatted() {
        assert publisherQuestionsService.selectAllFormatted(123) != null;
        publisherQuestionsService.selectAllFormatted(123)
                .forEach(System.out::println);
    }

    @Test
    void selectOneFormatted() {
        System.out.println(publisherQuestionsService.selectOneFormatted(120322));
    }
}