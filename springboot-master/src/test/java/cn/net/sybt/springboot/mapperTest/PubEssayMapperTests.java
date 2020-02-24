package cn.net.sybt.springboot.mapperTest;

import cn.net.sybt.springboot.bean.PubEssay;
import cn.net.sybt.springboot.mapper.PubEssayMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@SpringBootTest
@Transactional
public class PubEssayMapperTests {
    @Resource
    PubEssayMapper pubEssayMapper;
    Integer quesId = Integer.MAX_VALUE - 5;
    PubEssay pubEssay = new PubEssay();

    @BeforeEach
    public void init() {
        pubEssay.setQuesId(quesId);
        pubEssay.setQuesContent("我是一个作文题");
        pubEssay.setSolutionLink("我不想读范文");
    }

    @Test
    public void test() {
        Integer now = pubEssayMapper.selectCount(null);
        assert pubEssayMapper.insert(pubEssay) == 1;
        assert !pubEssay.getQuesId().equals(quesId);
        System.out.println(pubEssay.getQuesId());
        assert pubEssayMapper.selectCount(null) == 1 + now;
    }
}
