package cn.net.sybt.springboot.utils;

import cn.net.sybt.springboot.bean.PubChoiceQuestion;

import java.util.stream.Stream;

public class PublisherValidator {
    public static Boolean nullId(Integer id) {
        return id == null;
    }

    public static Boolean nullQuestion(PubChoiceQuestion question) {
        return Stream.of(question.getClass().getFields())
                .filter(t -> !"quesId".equals(t.getName()))
                .noneMatch(t -> t == null);
    }
}
