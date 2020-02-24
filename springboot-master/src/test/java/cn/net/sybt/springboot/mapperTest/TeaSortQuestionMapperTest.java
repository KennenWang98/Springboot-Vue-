package cn.net.sybt.springboot.mapperTest;

import cn.net.sybt.springboot.bean.TeaSortQuestion;
import cn.net.sybt.springboot.mapper.TeaSortQuestionMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional

public class TeaSortQuestionMapperTest {
    @Autowired
    private TeaSortQuestionMapper teaSortQuestionMapper;

    @Test
    public void select(){
        TeaSortQuestion moment = teaSortQuestionMapper.getTeaSortQuestionById(1);
        System.out.println(moment);
    }
    @Test
    public void selectAll(){
        List<TeaSortQuestion> allTeaSortQuestion = teaSortQuestionMapper.getAllTeaSortQuestion();
        for (TeaSortQuestion temp: allTeaSortQuestion
        ) {
            System.out.println(temp);
        }
    }
    @Test
    public void insert(){
        TeaSortQuestion test = new TeaSortQuestion();
        test.setQuesContent("test");
        test.setQuesContent("1");
        teaSortQuestionMapper.addTeaSortQuestion(test);
    }
    @Test
    public void delete(){
        teaSortQuestionMapper.deleteTeaSortQuestionById(1);
    }
    @Test
    public void upadate(){
        TeaSortQuestion test = this.teaSortQuestionMapper.getTeaSortQuestionById(1);
        test.setQuesContent("testupdate1");
        teaSortQuestionMapper.updateTeaSortQuestion(test);
    }
}
