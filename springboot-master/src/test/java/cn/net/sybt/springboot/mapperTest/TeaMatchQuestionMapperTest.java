package cn.net.sybt.springboot.mapperTest;


import cn.net.sybt.springboot.bean.TeaMatchQuestion;
import cn.net.sybt.springboot.mapper.TeaMatchQuestionMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional

public class TeaMatchQuestionMapperTest {

    @Autowired
    private TeaMatchQuestionMapper teaMatchQuestionMapper;

    @Test
    public void select(){
        TeaMatchQuestion moment = teaMatchQuestionMapper.getTeaMatchQuestionById(1);
        System.out.println(moment);
    }
    @Test
    public void selectAll(){
        List<TeaMatchQuestion> allTeaMatchQuestion = teaMatchQuestionMapper.getAllTeaMatchQuestion();
        for (TeaMatchQuestion temp: allTeaMatchQuestion
        ) {
            System.out.println(temp);
        }
    }
    @Test
    public void insert(){
        TeaMatchQuestion test = new TeaMatchQuestion();
        test.setQuesContent("test");
        test.setQuesContent("1");
        teaMatchQuestionMapper.addTeaMatchQuestion(test);
    }
    @Test
    public void delete(){
        teaMatchQuestionMapper.deleteTeaMatchQuestionById(1);
    }
    @Test
    public void upadate(){
        TeaMatchQuestion test = this.teaMatchQuestionMapper.getTeaMatchQuestionById(1);
        test.setSolutionLink("testupdate1");
        teaMatchQuestionMapper.updateTeaMatchQuestion(test);
    }
}
