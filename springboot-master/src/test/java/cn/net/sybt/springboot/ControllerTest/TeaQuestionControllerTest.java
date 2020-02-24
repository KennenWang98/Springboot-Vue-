package cn.net.sybt.springboot.ControllerTest;

import cn.net.sybt.springboot.controller.TeaQuestionController;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class TeaQuestionControllerTest {
    @Resource
    TeaQuestionController teaQuestionController;
//    @Test
//    public void TeaQuestionControllerTest(){
//        teaQuestionController.getAllTeaQuestionById(1);
//    }
}
