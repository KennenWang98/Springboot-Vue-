package cn.net.sybt.springboot.interfaces;

import java.util.List;

public interface SolutionMapperInterface {
    Integer deleteByQuestionId(Integer quesId);

    List selectByQuestionId(Integer quesId);
}
