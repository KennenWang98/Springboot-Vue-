package cn.net.sybt.springboot.mapperTest;

import cn.net.sybt.springboot.bean.PubFillQuestion;
import cn.net.sybt.springboot.mapper.PubFillQuestionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@SpringBootTest
@Transactional
public class PubFillQuestionMapperTests {
    @Resource
    PubFillQuestionMapper pubFillQuestionMapper;
    PubFillQuestion pubChoiceQuestion = new PubFillQuestion();

    @BeforeEach
    public void init() {
        pubChoiceQuestion.setQuesContent("一个填空题干");
        pubChoiceQuestion.setSolutionText("一个填空题解");
        pubChoiceQuestion.setSolutionLink("一个填空题解链接");
    }

    @Test
    public void test() {
        Integer count = pubFillQuestionMapper.selectCount(null);
        assert pubFillQuestionMapper.insert(pubChoiceQuestion) == 1;
        assert pubChoiceQuestion.getQuesId() != null;
        assert pubFillQuestionMapper.selectCount(null) == 1 + count;
    }
}
