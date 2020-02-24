package cn.net.sybt.springboot.mapperTest;

import cn.net.sybt.springboot.bean.PubChoiceQuestion;
import cn.net.sybt.springboot.mapper.PubChoiceQuestionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@SpringBootTest
@Transactional
public class PubChoiceQuestionMapperTests {
    @Resource
    PubChoiceQuestionMapper pubChoiceQuestionMapper;
    PubChoiceQuestion pubChoiceQuestion = new PubChoiceQuestion();

    @BeforeEach
    public void init() {
        pubChoiceQuestion.setQuesContent("一个选择题干");
        pubChoiceQuestion.setSolutionText("一个选择题解");
        pubChoiceQuestion.setSolutionLink("一个选择题解链接");
    }

    @Test
    public void test() {
        Integer count = pubChoiceQuestionMapper.selectCount(null);
        assert pubChoiceQuestionMapper.insert(pubChoiceQuestion) == 1;
        System.out.println(pubChoiceQuestion);
        pubChoiceQuestionMapper.selectList(null).forEach(System.out::println);
        assert pubChoiceQuestionMapper.selectCount(null) == count + 1;
    }
}
