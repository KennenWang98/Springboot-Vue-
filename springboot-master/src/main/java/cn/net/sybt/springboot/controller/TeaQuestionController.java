package cn.net.sybt.springboot.controller;


import cn.net.sybt.springboot.service.TeaQuestionService;
import cn.net.sybt.springboot.utils.ResultEnum;
import cn.net.sybt.springboot.utils.ResultModel;
import cn.net.sybt.springboot.utils.RetResponse;
import cn.net.sybt.springboot.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tea-question")
public class TeaQuestionController {
    @Autowired
    TeaQuestionService teaQuestionService;

    @RequestMapping("/find-by-teacher")
    public ResultModel<List<TeaQuestionVO>> getAllTeaQuestionById(Integer teaId, Integer pages) {
        if (teaQuestionService.getAllTeaQuestionById(teaId).size() == 0)
            return RetResponse.resp(ResultEnum.NOT_FOUND_TEA_QUES.code, ResultEnum.NOT_FOUND_TEA_QUES.msg);
        return RetResponse.okResp(teaQuestionService.getAllTeaQuestionById(teaId));
    }

    @RequestMapping("/find-by-teacher-and-class")
    public ResultModel<List<TeaQuestionVO>> getAllTeaQuestionByClass(Integer teaId, Integer classId, Integer pages) {
        if (teaQuestionService.getAllTeaQuestionByClass(teaId, classId).size() == 0)
            return RetResponse.resp(ResultEnum.NOT_FOUND_TEA_CLASS_QUES.code, ResultEnum.NOT_FOUND_TEA_CLASS_QUES.msg);
        return RetResponse.okResp(teaQuestionService.getAllTeaQuestionByClass(teaId, classId));
    }


    @RequestMapping("/upload-teacher-choice")
    public ResultModel<TeaQuestionVO> uploadTeacherChoice(@RequestBody TeaChoiceVO teaChoiceVo) {
        List<Integer> result = teaQuestionService.uploadTeacherChoice(teaChoiceVo);
        if (result.get(0).equals(0))
            return RetResponse.resp(ResultEnum.UPLOAD_TEA_QUES_ERROR.code, ResultEnum.UPLOAD_TEA_QUES_ERROR.msg);
        else
            return RetResponse.okResp(teaQuestionService.getTeaQuestionById(result.get(1)));
    }

    @RequestMapping("/upload-teacher-essay")
    public ResultModel<TeaQuestionVO> uploadTeacherEssay(@RequestBody TeaEssayVO teaEssayVO) {
        List<Integer> result = teaQuestionService.uploadTeacherEssay(teaEssayVO);
        if (result.get(0).equals(0))
            return RetResponse.resp(ResultEnum.UPLOAD_TEA_QUES_ERROR.code, ResultEnum.UPLOAD_TEA_QUES_ERROR.msg);
        else
            return RetResponse.okResp(teaQuestionService.getTeaQuestionById(result.get(1)));
    }

    @RequestMapping("/upload-teacher-fill")
    public ResultModel<TeaQuestionVO> uploadTeacherFill(@RequestBody TeaFillVO teaFillVO) {
        String text = teaFillVO.getTeaFillQuestion().getQuesContent();
        String placeholder = "¿";
        Integer number = (text.length() - text.replace("¿", "").length()) / placeholder.length();
        if (!number.equals(teaFillVO.getTeaFillSolutions().size()))
            return RetResponse.resp(ResultEnum.UPLOAD_TEA_QUES_ERROR.code, "占位符和空的数量不符！");
        else {
            List<Integer> result = teaQuestionService.uploadTeacherFill(teaFillVO);
            if (result.get(0).equals(0))
                return RetResponse.resp(ResultEnum.UPLOAD_TEA_QUES_ERROR.code, ResultEnum.UPLOAD_TEA_QUES_ERROR.msg);
            else
                return RetResponse.okResp(teaQuestionService.getTeaQuestionById(result.get(1)));
        }
    }


    @RequestMapping("/upload-teacher-judge")
    public ResultModel<TeaQuestionVO> uploadTeacherJudge(@RequestBody TeaJudgeVO teaJudgeVO) {
        List<Integer> result = teaQuestionService.uploadTeacherJudge(teaJudgeVO);
        if (result.get(0).equals(0))
            return RetResponse.resp(ResultEnum.UPLOAD_TEA_QUES_ERROR.code, ResultEnum.UPLOAD_TEA_QUES_ERROR.msg);
        else
            return RetResponse.okResp(teaQuestionService.getTeaQuestionById(result.get(1)));
    }

    @RequestMapping("/upload-teacher-match")
    public ResultModel uploadTeacherMatch(@RequestBody TeaMatchVO teaMatchVO) {
        Integer result = teaQuestionService.uploadTeacherMatch(teaMatchVO);
        if (result.equals(0))
            return RetResponse.resp(ResultEnum.UPLOAD_TEA_QUES_ERROR.code, ResultEnum.UPLOAD_TEA_QUES_ERROR.msg);
        return RetResponse.okResp();
    }

    @RequestMapping("/upload-teacher-sort")
    public ResultModel uploadTeacherSort(@RequestBody TeaSortVO teaSortVo) {
        Integer result = teaQuestionService.uploadTeacherSort(teaSortVo);
        if (result.equals(0))
            return RetResponse.resp(ResultEnum.UPLOAD_TEA_QUES_ERROR.code, ResultEnum.UPLOAD_TEA_QUES_ERROR.msg);
        return RetResponse.okResp();
    }

    @RequestMapping("/update-teacher-choice")
    public ResultModel updateTeacherChoice(@RequestBody TeaChoiceVO teaChoiceVo) {
        Integer result = teaQuestionService.updateTeacherChoice(teaChoiceVo);
        if (result.equals(0))
            return RetResponse.resp(ResultEnum.UPLOAD_TEA_QUES_ERROR.code, ResultEnum.UPLOAD_TEA_QUES_ERROR.msg);
        return RetResponse.okResp();
    }

    @RequestMapping("/update-teacher-essay")
    public ResultModel updateTeacherEssay(@RequestBody TeaEssayVO teaEssayVO) {
        Integer result = teaQuestionService.updateTeacherEssay(teaEssayVO);
        if (result.equals(0))
            return RetResponse.resp(ResultEnum.UPLOAD_TEA_QUES_ERROR.code, ResultEnum.UPLOAD_TEA_QUES_ERROR.msg);
        return RetResponse.okResp();
    }

    @RequestMapping("/update-teacher-fill")
    public ResultModel updateTeacherFill(@RequestBody TeaFillVO teaFillVO) {
        String text = teaFillVO.getTeaFillQuestion().getQuesContent();
        String placeholder = "¿";
        Integer number = (text.length() - text.replace("¿", "").length()) / placeholder.length();
        if (!number.equals(teaFillVO.getTeaFillSolutions().size()))
            return RetResponse.resp(ResultEnum.UPLOAD_TEA_QUES_ERROR.code, "占位符和空的数量不符！");
        else {
            Integer result = teaQuestionService.updateTeacherFill(teaFillVO);
            if (result.equals(0))
                return RetResponse.resp(ResultEnum.UPLOAD_TEA_QUES_ERROR.code, ResultEnum.UPLOAD_TEA_QUES_ERROR.msg);
            else
                return RetResponse.okResp();
        }
    }

    @RequestMapping("/update-teacher-judge")
    public ResultModel updateTeacherJudge(@RequestBody TeaJudgeVO teaJudgeVO) {
        Integer result = teaQuestionService.updateTeacherJudge(teaJudgeVO);
        if (result.equals(0))
            return RetResponse.resp(ResultEnum.UPLOAD_TEA_QUES_ERROR.code, ResultEnum.UPLOAD_TEA_QUES_ERROR.msg);
        return RetResponse.okResp();
    }

    @RequestMapping("/delete-teacher-question")
    public ResultModel deleteTeacherQues(Integer quesId) {
        Integer result = teaQuestionService.deleteTeacherQues(quesId);
        if (result == 0)
            return RetResponse.resp(ResultEnum.DELETE_TEA_QUES_ERROR.code, ResultEnum.DELETE_TEA_QUES_ERROR.msg);
        else
            return RetResponse.okResp();
    }
}
