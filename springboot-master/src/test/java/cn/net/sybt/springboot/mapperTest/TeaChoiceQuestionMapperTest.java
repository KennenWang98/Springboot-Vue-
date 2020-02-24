//package cn.net.sybt.springboot.mapperTest;
//
//
//import cn.net.sybt.springboot.bean.TeaChoiceQuestion;
//import cn.net.sybt.springboot.mapper.TeaChoiceQuestionMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@SpringBootTest
//@Transactional
//public class TeaChoiceQuestionMapperTest {
//
//    @Autowired
//    private TeaChoiceQuestionMapper teaChoiceQuestionMapper;
//
//    @Test
//    public void select(){
//        TeaChoiceQuestion moment = teaChoiceQuestionMapper.getTeaChoiceQuestionById(1);
//        System.out.println(moment);
//    }
//    @Test
//    public void selectAll(){
//        List<TeaChoiceQuestion> allTeaChoiceQuestion = teaChoiceQuestionMapper.getAllTeaChoiceQuestion();
//        for (TeaChoiceQuestion temp: allTeaChoiceQuestion
//        ) {
//            System.out.println(temp);
//        }
//    }
//    @Test
//    public void insert(){
//        TeaChoiceQuestion test = new TeaChoiceQuestion();
//        test.setQuesContent("mxjtest1");
//
//        test.setSolutionText("mxjtest1");
//        test.setSolutionLink("mxjtest1");
//        teaChoiceQuestionMapper.addTeaChoiceQuestion(test);
//    }
//    @Test
//    public void delete(){
//        teaChoiceQuestionMapper.deleteTeaChoiceQuestionById(1);
//    }
//    @Test
//    public void upadate(){
//        TeaChoiceQuestion test = this.teaChoiceQuestionMapper.getTeaChoiceQuestionById(22);
//        test.setSolutionText("testupdate1");
//        teaChoiceQuestionMapper.updateTeaChoiceQuestion(test);
//    }
//
//}
