package cn.net.sybt.springboot.utils;

import cn.net.sybt.springboot.bean.PublisherQuestions;
import cn.net.sybt.springboot.consts.Parameters;
import cn.net.sybt.springboot.vo.PubVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PublisherQuestionsFormatter {
    public static List<PubVO> format(HashMap questionTable) {
        if (questionTable == null) {
            return null;
        }
        List<PubVO> results = new ArrayList<>();
        questionTable.forEach((pubQuestion, questionContent) -> {
            if (pubQuestion instanceof PublisherQuestions) {
                PubVO result = new PubVO();
                result.setQuesAllId(((PublisherQuestions) pubQuestion).getPubQuesId());
                result.setBookId(((PublisherQuestions) pubQuestion).getBookId());
                result.setQuesType(((PublisherQuestions) pubQuestion).getPubQuesType());
                result.setFlag(Parameters.PUB_FLAG);
                if (questionContent instanceof HashMap) {
                    ((HashMap) questionContent).forEach((question, solutions) -> {
                        result.setQuestion(question);
                        result.setSolutions((List) solutions);
                    });
                } else {
                    result.setQuestion(questionContent);
                }
                results.add(result);
            }
        });
        return results;
    }
}
