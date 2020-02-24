package cn.net.sybt.springboot.mapperTest;

import cn.net.sybt.springboot.bean.PubMatchQuestion;
import cn.net.sybt.springboot.mapper.PubMatchQuestionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@SpringBootTest
@Transactional
public class PubMatchQuestionMapperTests {
    @Resource
    PubMatchQuestionMapper pubMatchQuestionMapper;
    PubMatchQuestion pubMatchQuestion = new PubMatchQuestion();

    @BeforeEach
    public void init() {
        pubMatchQuestion.setQuesContent("一个连线的题干");
        pubMatchQuestion.setSolutionText("一个连线的题解");
        pubMatchQuestion.setSolutionLink("一个连线的题解链接");
    }

    @Test
    public void test() {
        pubMatchQuestionMapper.insert(pubMatchQuestion);
        assert pubMatchQuestionMapper.selectCount(null) != 0;
    }
}
