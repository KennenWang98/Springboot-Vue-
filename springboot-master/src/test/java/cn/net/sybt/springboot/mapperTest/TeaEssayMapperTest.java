//package cn.net.sybt.springboot.mapperTest;
//
//
//import cn.net.sybt.springboot.bean.TeaEssay;
//import cn.net.sybt.springboot.mapper.TeaEssayMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@SpringBootTest
//@Transactional
//public class TeaEssayMapperTest {
//    @Autowired
//    private TeaEssayMapper teaEssayMapper;
//
//    @Test
//    public void select(){
//        TeaEssay moment = teaEssayMapper.getTeaEssayById(1);
//        System.out.println(moment);
//    }
//    @Test
//    public void selectAll(){
//        List<TeaEssay> allTeaEssay = teaEssayMapper.getAllTeaEssay();
//        for (TeaEssay temp: allTeaEssay
//        ) {
//            System.out.println(temp);
//        }
//    }
//    @Test
//    public void insert(){
//        TeaEssay test = new TeaEssay();
//        test.setQuesContent("test");
//        test.setQuesContent("test");
//        teaEssayMapper.addTeaEssay(test);
//    }
//    @Test
//    public void delete(){
//        teaEssayMapper.deleteTeaEssayById(1);
//    }
//    @Test
//    public void upadate(){
//        TeaEssay test = this.teaEssayMapper.getTeaEssayById(4);
//        test.setSolutionLink("testupdate1");
//        teaEssayMapper.updateTeaEssay(test);
//    }
//
//}
