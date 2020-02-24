package cn.net.sybt.springboot.mapperTest;

import cn.net.sybt.springboot.bean.PubChoiceSolution;
import cn.net.sybt.springboot.mapper.PubChoiceSolutionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@SpringBootTest
@Transactional
public class PubChoiceSolutionMapperTests {
    @Resource
    PubChoiceSolutionMapper pubChoiceSolutionMapper;
    PubChoiceSolution pubChoiceSolution = new PubChoiceSolution();
    Integer quesId = Integer.MAX_VALUE - 5;

    @BeforeEach
    public void init() {
        pubChoiceSolution.setQuesId(quesId);
        pubChoiceSolution.setIsTrue(0);
        pubChoiceSolution.setChoiceLink("www.mychoicelink.com");
        pubChoiceSolution.setChoiceContent("我也不想留下选择内容");
    }

    @Test
    public void selectAndDeleteTest() {
        assert pubChoiceSolutionMapper.selectByQuestionId(quesId).isEmpty();
        pubChoiceSolutionMapper.insert(pubChoiceSolution);
        assert pubChoiceSolutionMapper.selectByQuestionId(quesId).size() == 1;
        assert pubChoiceSolutionMapper.deleteByQuestionId(quesId) == 1;
        assert pubChoiceSolutionMapper.selectByQuestionId(quesId).isEmpty();
    }
}
