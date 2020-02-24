//package cn.net.sybt.springboot.mapperTest;
//
//
//import cn.net.sybt.springboot.bean.TeaFillSolution;
//import cn.net.sybt.springboot.mapper.TeaFillSolutionMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@SpringBootTest
//@Transactional
//public class TeaFillSolutionMapperTest {
//    @Autowired
//    private TeaFillSolutionMapper teaFillSolutionMapper;
//
//    @Test
//    public void select(){
//        List<TeaFillSolution> moment = teaFillSolutionMapper.getTeaFillSolutionById(1);
//        moment.forEach(System.out::println);
//    }
//    @Test
//    public void selectAll() {
//        List<TeaFillSolution> allTeaFillSolution = teaFillSolutionMapper.getAllTeaFillSolution();
//        allTeaFillSolution.forEach(System.out::println);
//    }
//    @Test
//    public void insert(){
//        TeaFillSolution test = new TeaFillSolution();
//        test.setSolutionContent("test");
//        test.setSolutionNo(1);
//        teaFillSolutionMapper.addTeaFillSolution(test);
//    }
//    @Test
//    public void delete(){
//        teaFillSolutionMapper.deleteTeaFillSolutionById(1);
//    }
//    @Test
//    public void upadate(){
//        List<TeaFillSolution> test = this.teaFillSolutionMapper.getTeaFillSolutionById(4);
//        test.get(0).setSolutionContent("testupdate1");
//        teaFillSolutionMapper.updateTeaFillSolution(test.get(0));
//    }
//
//}
//
