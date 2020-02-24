package cn.net.sybt.springboot.mapperTest;

import cn.net.sybt.springboot.bean.PublisherQuestions;
import cn.net.sybt.springboot.mapper.PublisherQuestionsMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@SpringBootTest
@Transactional
public class PublisherQuestionsMapperTests {
    @Resource
    PublisherQuestionsMapper publisherQuestionsMapper;
    Integer bookId = Integer.MAX_VALUE - 5;
    Integer quesId = Integer.MAX_VALUE - 6;
    PublisherQuestions publisherQuestions =
            new PublisherQuestions(null, bookId, "select", quesId);

    @BeforeEach
    public void init() {
        assert publisherQuestionsMapper.getByBookId(bookId).isEmpty();
    }
    @Test
    public void addAndDeleteByBookIdTest() {
        assert publisherQuestionsMapper.addPublisherQuestions(publisherQuestions) == 1;
        assert publisherQuestionsMapper.getByBookId(bookId).size() == 1;
        assert publisherQuestionsMapper.deletePublisherQuestionsByBookId(bookId) == 1;
        assert publisherQuestionsMapper.getByBookId(bookId).isEmpty();
    }
    @Test
    public void addByBookIdTest() {
        assert publisherQuestionsMapper.addPublisherQuestions(publisherQuestions) == 1;
        assert publisherQuestionsMapper.getByBookId(bookId).stream()
                .filter(t -> bookId.equals(t.getBookId())).count() == 1;
        assert publisherQuestionsMapper.getByBookId(bookId).stream()
                .filter(t -> quesId.equals(t.getPubQuesSpecificId())).count() == 1;
        assert publisherQuestionsMapper.deletePublisherQuestionsByBookId(bookId) == 1;
        assert publisherQuestionsMapper.getByBookId(bookId).isEmpty();
    }
    @Test
    public void getAndDeleteByIdTest() {
        assert publisherQuestionsMapper.addPublisherQuestions(publisherQuestions) == 1;
        publisherQuestions = publisherQuestionsMapper.getByBookId(bookId).get(0);
        assert publisherQuestions != null;
        assert publisherQuestionsMapper.getById(publisherQuestions.getPubQuesId()).size() == 1;
        assert publisherQuestionsMapper.deletePublisherQuestionsById(publisherQuestions.getPubQuesId()) == 1;
        assert publisherQuestionsMapper.getById(publisherQuestions.getPubQuesId()).isEmpty();
    }
    @Test
    public void substTest() {
        assert publisherQuestionsMapper.addPublisherQuestions(publisherQuestions) == 1;
        publisherQuestions = publisherQuestionsMapper.getByBookId(bookId).get(0);
        Integer newBookId = Integer.MAX_VALUE - 6;
        Integer newQuesId = Integer.MAX_VALUE - 5;
        PublisherQuestions newPublisherQuestions =
                new PublisherQuestions(null, newBookId, "match", newQuesId);
        assert publisherQuestionsMapper.getByBookId(newBookId).isEmpty();
        assert publisherQuestionsMapper.substPublisherQuestions(
                publisherQuestions, newPublisherQuestions) == 1;
        assert publisherQuestionsMapper.getByBookId(bookId).isEmpty();
        assert publisherQuestionsMapper.getByBookId(newBookId).size() == 1;
        assert publisherQuestionsMapper.deletePublisherQuestionsByBookId(newBookId) == 1;
        assert publisherQuestionsMapper.getByBookId(newBookId).isEmpty();
    }
}
