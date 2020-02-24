package cn.net.sybt.springboot.mapperTest;

import cn.net.sybt.springboot.bean.PubMatchSolution;
import cn.net.sybt.springboot.mapper.PubMatchSolutionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@Transactional
public class PubMatchSolutionMapperTests {
    @Resource
    PubMatchSolutionMapper pubMatchSolutionMapper;
    Integer quesId = Integer.MAX_VALUE - 5;
    PubMatchSolution pubMatchSolution = new PubMatchSolution();

    @BeforeEach
    public void init() {
        pubMatchSolution.setQuesId(quesId);
        pubMatchSolution.setLeftValue("我是左边");
        pubMatchSolution.setRightValue("我是右边");
    }

    @Test
    public void getAndDeleteByQuestionIdTest() {
        System.out.println(pubMatchSolution);
        pubMatchSolutionMapper.insert(pubMatchSolution);
        List<PubMatchSolution> solutions =
                pubMatchSolutionMapper.selectByQuestionId(quesId);
        assert solutions.size() == 1;
        assert pubMatchSolutionMapper.deleteByQuestionId(quesId) == 1;
        assert pubMatchSolutionMapper.selectByQuestionId(quesId).isEmpty();
    }
}
