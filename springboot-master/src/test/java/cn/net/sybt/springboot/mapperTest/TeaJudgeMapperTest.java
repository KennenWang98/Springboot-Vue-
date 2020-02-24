//package cn.net.sybt.springboot.mapperTest;
//
//
//import cn.net.sybt.springboot.bean.TeaJudge;
//import cn.net.sybt.springboot.mapper.TeaJudgeMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@SpringBootTest
//@Transactional
//public class TeaJudgeMapperTest {
//    @Autowired
//    private TeaJudgeMapper teaJudgeMapper;
//
//    @Test
//    public void select(){
//        TeaJudge moment = teaJudgeMapper.getTeaJudgeById(1);
//        System.out.println(moment);
//    }
//    @Test
//    public void selectAll(){
//        List<TeaJudge> allTeaJudge = teaJudgeMapper.getAllTeaJudge();
//        for (TeaJudge temp: allTeaJudge
//        ) {
//            System.out.println(temp);
//        }
//    }
//    @Test
//    public void insert(){
//        TeaJudge test = new TeaJudge();
//        test.setQuesContent("test");
//        test.setQuesLink("1");
//        teaJudgeMapper.addTeaJudge(test);
//    }
//    @Test
//    public void delete(){
//        teaJudgeMapper.deleteTeaJudgeById(1);
//    }
//    @Test
//    public void upadate(){
//        TeaJudge test = this.teaJudgeMapper.getTeaJudgeById(3);
//        test.setSolutionLink("testupdate1");
//        teaJudgeMapper.updateTeaJudge(test);
//    }
//
//}
