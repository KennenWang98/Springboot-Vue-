//package cn.net.sybt.springboot.mapperTest;
//
//
//import cn.net.sybt.springboot.bean.TeaFillQuestion;
//import cn.net.sybt.springboot.mapper.TeaFillQuestionMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@SpringBootTest
//@Transactional
//public class TeaFillQuestionMapperTest {
//    @Autowired
//    private TeaFillQuestionMapper teaFillQuestionMapper;
//
//    @Test
//    public void select(){
//        TeaFillQuestion moment = teaFillQuestionMapper.getTeaFillQuestionById(1);
//        System.out.println(moment);
//    }
//    @Test
//    public void selectAll(){
//        List<TeaFillQuestion> allTeaFillQuestion = teaFillQuestionMapper.getAllTeaFillQuestion();
//        for (TeaFillQuestion temp: allTeaFillQuestion
//        ) {
//            System.out.println(temp);
//        }
//    }
//    @Test
//    public void insert(){
//        TeaFillQuestion test = new TeaFillQuestion();
//        test.setQuesContent("test");
//        test.setSolutionLink("test");
//
//        teaFillQuestionMapper.addTeaFillQuestion(test);
//    }
//    @Test
//    public void delete(){
//        teaFillQuestionMapper.deleteTeaFillQuestionById(1);
//    }
//    @Test
//    public void upadate(){
//        TeaFillQuestion test = this.teaFillQuestionMapper.getTeaFillQuestionById(4);
//        test.setSolutionLink("testupdate1");
//        teaFillQuestionMapper.updateTeaFillQuestion(test);
//    }
//}
