package cn.net.sybt.springboot.service;

import cn.net.sybt.springboot.bean.Subject;
import cn.net.sybt.springboot.mapper.SubjectMapper;
import cn.net.sybt.springboot.utils.CheckObject;
import cn.net.sybt.springboot.utils.ResultEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class SubjectService {
    @Resource
    private CheckObject checkObject;
    @Resource
    private SubjectMapper subjectMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer addSubject(Subject subject) {
        boolean flag = checkObject.check(subject);
        int code = 0;
        if (!flag) {
            if (subject.getSubjectName() == null || "".equals(subject.getSubjectName())) {
                return ResultEnum.INSERT_ERROR.code;
            }
            code = subjectMapper.addSubject(subject);
        }
        if (code == 1) {
            return ResultEnum.SUCCESS.code;
        } else {
            return ResultEnum.INSERT_ERROR.code;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer deleteSubject(Integer subjectId) {
        return subjectMapper.deleteSubject(subjectId);
    }

    public List<Subject> getAllSubject() {
        return subjectMapper.getAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer updateSubject(Subject subject) {
        boolean flag = checkObject.check(subject);
        int code = 0;
        if (!flag) {
            if (subject.getSubjectName() == null || "".equals(subject.getSubjectName())) {
                return ResultEnum.UPDATE_ERROR.code;
            }
            code = subjectMapper.updateSubject(subject);
        }
        if (code == 1) {
            return ResultEnum.SUCCESS.code;
        } else {
            return ResultEnum.UPDATE_ERROR.code;
        }
    }
}
