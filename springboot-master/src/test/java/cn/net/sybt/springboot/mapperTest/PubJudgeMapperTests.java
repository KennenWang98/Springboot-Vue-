package cn.net.sybt.springboot.mapperTest;

import cn.net.sybt.springboot.bean.PubJudge;
import cn.net.sybt.springboot.mapper.PubJudgeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@Transactional
public class PubJudgeMapperTests {
    @Resource
    PubJudgeMapper pubJudgeMapper;

    @Test
    public void test() {
        PubJudge pubJudge = new PubJudge();
        pubJudge.setQuesContent("我是谁?");
        pubJudge.setQuesLink("www.hao123.com");
        pubJudge.setIsTrue(0);
        pubJudge.setSolutionText("我不是谁");
        pubJudge.setSolutionLink("www.end.xyz");
        assert pubJudgeMapper.insert(pubJudge) == 1;
        List<PubJudge> result = pubJudgeMapper.selectList(null);
        result.forEach(System.out::println);
        pubJudgeMapper.deleteBatchIds(
                result.stream()
                        .filter(t -> "我是谁?".equals(t.getQuesContent()))
                        .map(PubJudge::getQuesId)
                        .collect(Collectors.toList()));
        List<PubJudge> newResult = pubJudgeMapper.selectList(null);
        System.out.println();
        newResult.forEach(System.out::println);
    }
}
