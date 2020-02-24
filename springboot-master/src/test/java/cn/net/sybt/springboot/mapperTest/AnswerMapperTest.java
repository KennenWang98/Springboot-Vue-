//package cn.net.sybt.springboot.mapperTest;
//
//import cn.net.sybt.springboot.bean.Answer;
//import cn.net.sybt.springboot.mapper.AnswerMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//@SpringBootTest
//@Transactional
//class AnswerMapperTest {
//
//    @Autowired
//    AnswerMapper answerMapper;
//
//
//    @Test
//    void updateAnswer() {
//        Answer answer = answerMapper.getAnswerById(16);
//        System.out.println(answer);
//        answer.setPercentage(0.5);
//        answer.setChId(55);
//        System.out.println(answer);
//        answerMapper.updateAnswer(answer);
//    }
//}