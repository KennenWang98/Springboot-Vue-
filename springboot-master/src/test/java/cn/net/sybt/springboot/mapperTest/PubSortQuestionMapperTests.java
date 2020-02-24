package cn.net.sybt.springboot.mapperTest;

import cn.net.sybt.springboot.bean.PubSortQuestion;
import cn.net.sybt.springboot.mapper.PubSortQuestionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@SpringBootTest
@Transactional
public class PubSortQuestionMapperTests {
    @Resource
    PubSortQuestionMapper pubSortQuestionMapper;
    PubSortQuestion pubSortQuestion = new PubSortQuestion();

    @BeforeEach
    public void init() {
        pubSortQuestion.setQuesContent("一个排序题干");
        pubSortQuestion.setSolutionText("一个排序题解");
        pubSortQuestion.setSolutionLink("一个排序题解链接");
    }

    @Test
    public void test() {
        assert pubSortQuestionMapper.selectCount(null) == 0;
        assert pubSortQuestionMapper.insert(pubSortQuestion) == 1;
        assert pubSortQuestionMapper.selectCount(null) == 1;
    }

}
