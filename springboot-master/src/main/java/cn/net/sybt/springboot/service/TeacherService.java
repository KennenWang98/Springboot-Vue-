package cn.net.sybt.springboot.service;

import cn.net.sybt.springboot.bean.Clazz;
import cn.net.sybt.springboot.bean.Teacher;
import cn.net.sybt.springboot.mapper.ClazzMapper;
import cn.net.sybt.springboot.mapper.TeacherMapper;
import cn.net.sybt.springboot.utils.CheckObject;
import cn.net.sybt.springboot.utils.ResultEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class TeacherService {
    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private ClazzMapper clazzMapper;
    @Resource
    private CheckObject checkObject;

    public Teacher getTeacherById(Integer teaId) {//根据id查询老师
        return teacherMapper.getTeacherById(teaId);
    }

    public List<Teacher> getTeacherByName(String teaName) {//根据姓名查询老师
        return teacherMapper.getTeacherByName(teaName);
    }

    public Teacher getTeacherByPhone(String phoneNumber) { //根据手机号查询老师
        return teacherMapper.getTeacherByPhone(phoneNumber);
    }

    public List<Teacher> getAllTeachers() {//查询出所有的老师
        return teacherMapper.getAllTeachers();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer deleteTeacherById(Integer teaId) {//根据id删除老师
        List<Clazz> clazzList = clazzMapper.getAllClassOfTeacher(teaId);
        if (clazzList == null || clazzList.size() == 0) {
            //只有教师没有教任何班级时，才可以删除
            return teacherMapper.deleteTeacherById(teaId);
        }
        return 0;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer addTeacher(Teacher teacher) {//添加老师
        boolean flag = checkObject.check(teacher);
        int code = 0;
        if (!flag) {
            code = teacherMapper.addTeacher(teacher);
        }
        if (code == 1) {
            return ResultEnum.SUCCESS.code;
        } else {
            return ResultEnum.INSERT_ERROR.code;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer updateTeacher(Teacher teacher) {// 更新老师个人信息
        boolean flag = checkObject.check(teacher);
        int code = 0;
        if (!flag) {
            Teacher t = teacherMapper.getTeacherByPhone(teacher.getPhoneNumber());
            if (t == null) {
                t = teacherMapper.getTeacherById(teacher.getTeaId());
                if (t == null) {
                    return ResultEnum.UPDATE_ERROR.code;
                }
            }
            if (!(teacher.getTeaName() == null || "".equals(teacher.getTeaName()))) {
                t.setTeaName(teacher.getTeaName());
            }
            if (!(teacher.getTeaIcon() == null || "".equals(teacher.getTeaIcon()))) {
                t.setTeaIcon(teacher.getTeaIcon());
            }
            if (!(teacher.getGender() == null || "".equals(teacher.getGender()))) {
                t.setGender(teacher.getGender());
            }
            code = teacherMapper.updateTeacher(t);
        }
        if (code != 0) {
            return ResultEnum.SUCCESS.code;
        } else {
            return ResultEnum.UPDATE_ERROR.code;
        }
    }
}
