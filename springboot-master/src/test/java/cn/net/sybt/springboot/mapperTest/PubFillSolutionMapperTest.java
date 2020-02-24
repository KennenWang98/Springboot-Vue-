package cn.net.sybt.springboot.mapperTest;

import cn.net.sybt.springboot.bean.PubFillSolution;
import cn.net.sybt.springboot.mapper.PubFillSolutionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@SpringBootTest
@Transactional
public class PubFillSolutionMapperTest {
    @Resource
    PubFillSolutionMapper pubFillSolutionMapper;
    PubFillSolution pubFillSolution = new PubFillSolution();
    Integer quesId = Integer.MAX_VALUE - 5;

    @BeforeEach
    public void init() {
        pubFillSolution.setQuesId(quesId);
        pubFillSolution.setSolutionContent("不想填空");
        pubFillSolution.setSolutionNo(100);
    }

    @Test
    public void selectAndDeleteTest() {
        assert pubFillSolutionMapper.selectByQuestionId(quesId).isEmpty();
        pubFillSolutionMapper.insert(pubFillSolution);
        assert pubFillSolutionMapper.selectByQuestionId(quesId).size() == 1;
        assert pubFillSolutionMapper.deleteByQuestionId(quesId) == 1;
        assert pubFillSolutionMapper.selectByQuestionId(quesId).isEmpty();
    }
}