package cn.net.sybt.springboot.mapperTest;

import cn.net.sybt.springboot.bean.PubSortSolution;
import cn.net.sybt.springboot.mapper.PubSortSolutionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@SpringBootTest
@Transactional
public class PubSortSolutionMapperTests {
    @Resource
    PubSortSolutionMapper pubSortSolutionMapper;
    PubSortSolution pubSortSolution = new PubSortSolution();
    Integer quesId = Integer.MAX_VALUE - 5;

    @BeforeEach
    public void init() {
        pubSortSolution.setQuesId(quesId);
        pubSortSolution.setSortNo(1);
        pubSortSolution.setSolutionContent("一个排序");
    }

    @Test
    public void testSelectAndDelete() {
        assert pubSortSolutionMapper.selectByQuestionId(quesId).isEmpty();
        assert pubSortSolutionMapper.insert(pubSortSolution) == 1;
        assert pubSortSolutionMapper.selectByQuestionId(quesId).size() == 1;
        assert pubSortSolutionMapper.deleteByQuestionId(quesId) == 1;
        assert pubSortSolutionMapper.selectByQuestionId(quesId).isEmpty();
    }
}
