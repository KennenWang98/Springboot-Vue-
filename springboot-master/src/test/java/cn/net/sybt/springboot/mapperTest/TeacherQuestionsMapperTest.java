//package cn.net.sybt.springboot.mapperTest;
//
//import cn.net.sybt.springboot.bean.TeacherQuestions;
//import cn.net.sybt.springboot.mapper.TeacherQuestionsMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.annotation.Resource;
//import java.util.List;
//
//@SpringBootTest
//@Transactional
//public class TeacherQuestionsMapperTest {
//    @Resource
//    private TeacherQuestionsMapper teacherQuestionsMapper;
//
//    @Test
//    public void select(){
//        TeacherQuestions moment = teacherQuestionsMapper.getTeacherQuestionsById(1);
//        System.out.println(moment);
//    }
//    @Test
//    public void selectAll(){
//        List<TeacherQuestions> allTeacherQuestions = teacherQuestionsMapper.getAllTeacherQuestions();
//        for (TeacherQuestions temp: allTeacherQuestions
//        ) {
//            System.out.println(temp);
//        }
//    }
//    @Test
//    public void insert(){
//        TeacherQuestions test = new TeacherQuestions();
//        test.setTeaQuesType("test");
//        teacherQuestionsMapper.addTeacherQuestions(test);
//    }
//    @Test
//    public void delete(){
//        teacherQuestionsMapper.deleteTeacherQuestionsById(1);
//    }
//    @Test
//    public void upadate(){
//        TeacherQuestions test = this.teacherQuestionsMapper.getTeacherQuestionsById(21);
//        test.setTeaQuesType("testupdate1");
//        teacherQuestionsMapper.updateTeacherQuestions(test);
//    }
//
//}
