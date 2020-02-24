package cn.net.sybt.springboot.controller;

import cn.net.sybt.springboot.bean.Subject;
import cn.net.sybt.springboot.service.SubjectService;
import cn.net.sybt.springboot.utils.CheckObject;
import cn.net.sybt.springboot.utils.ResultEnum;
import cn.net.sybt.springboot.utils.ResultModel;
import cn.net.sybt.springboot.utils.RetResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Resource
    private CheckObject checkObject;
    @Resource
    private SubjectService subjectService;


    @RequestMapping("/print")
    public ResultModel<List<Subject>> getAllSubject() {
        return RetResponse.okResp(subjectService.getAllSubject());
    }

    @RequestMapping("/add")
    public ResultModel<Integer> addSubject(@RequestBody Subject subject) {
        boolean flag = checkObject.check(subject);
        int code = 0;
        if (!flag) {
            code = subjectService.addSubject(subject);
        }
        if (code == 200) {
            return RetResponse.okResp();
        } else {
            return RetResponse.resp(ResultEnum.INSERT_ERROR.code, ResultEnum.INSERT_ERROR.msg);
        }
    }

    @RequestMapping("/delete")
    public ResultModel<Integer> deleteSubject(@RequestParam Integer subjectId) {
        return RetResponse.okResp(subjectService.deleteSubject(subjectId));
    }

    @RequestMapping("/change")
    public ResultModel<Integer> updateSubject(@RequestBody Subject subject) {
        boolean flag = checkObject.check(subject);
        int code = 0;
        if (!flag) {
            code = subjectService.updateSubject(subject);
        }
        if (code == 200) {
            return RetResponse.okResp();
        } else {
            return RetResponse.resp(ResultEnum.UPDATE_ERROR.code, ResultEnum.UPDATE_ERROR.msg);
        }
    }
}
