//package cn.net.sybt.springboot.mapperTest;
//
//
//import cn.net.sybt.springboot.bean.TeaChoiceSolution;
//import cn.net.sybt.springboot.mapper.TeaChoiceSolutionMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@SpringBootTest
//@Transactional
//
//public class TeaChoiceSolutionMapperTest {
//    @Autowired
//    private TeaChoiceSolutionMapper teaChoiceSolutionMapper;
//
//    @Test
//    public void select(){
//        List<TeaChoiceSolution> moment = teaChoiceSolutionMapper.getTeaChoiceSolutionById(1);
//        moment.forEach(System.out::println);
//    }
//    @Test
//    public void selectAll(){
//        List<TeaChoiceSolution> allTeaChoiceSolution = teaChoiceSolutionMapper.getAllTeaChoiceSolution();
//        allTeaChoiceSolution.forEach(System.out::println);
//    }
//    @Test
//    public void insert(){
//        TeaChoiceSolution test = new TeaChoiceSolution();
//        test.setChoiceContent("test");
//        test.setChoiceLink("test");
//        test.setIsTrue(1);
//        test.setQuesId(2);
//        teaChoiceSolutionMapper.addTeaChoiceSolution(test);
//    }
//    @Test
//    public void delete(){
//        teaChoiceSolutionMapper.deleteTeaChoiceSolutionById(1);
//    }
//    @Test
//    public void upadate(){
//        List<TeaChoiceSolution> test = this.teaChoiceSolutionMapper.getTeaChoiceSolutionById(22);
//        test.get(0).setChoiceContent("testupdate1");
//        teaChoiceSolutionMapper.updateTeaChoiceSolution(test.get(0));
//    }
//}
