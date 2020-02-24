package cn.net.sybt.springboot.service;

import cn.net.sybt.springboot.bean.Clazz;
import cn.net.sybt.springboot.bean.School;
import cn.net.sybt.springboot.bean.Teacher;
import cn.net.sybt.springboot.mapper.ClazzMapper;
import cn.net.sybt.springboot.mapper.SchoolMapper;
import cn.net.sybt.springboot.utils.CheckObject;
import cn.net.sybt.springboot.utils.ResultEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@Transactional
public class SchoolService {
    @Resource
    private SchoolMapper schoolMapper;
    @Resource
    private ClazzMapper clazzMapper;
    @Resource
    private ClazzService clazzService;
    @Resource
    private TeacherService teacherService;
    @Resource
    private CheckObject checkObject;

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer addSchool(School school) { //新增学校
        boolean flag = checkObject.check(school);
        int code = 0;
        if (!flag) {
            code = schoolMapper.addSchool(school);
        }
        if (code == 1) {
            return ResultEnum.SUCCESS.code;
        } else {
            return ResultEnum.INSERT_ERROR.code;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer deleteSchoolById(Integer schoolId) { //删除学校
        List<Clazz> clazzList = clazzMapper.getAllClassOfSchool(schoolId);
        if (clazzList == null || clazzList.size() == 0) {
            schoolMapper.deleteSchoolById(schoolId);
            return 1;
        }
        return 0;
    }

    public School getSchoolById(Integer schoolId) { //根据id查询学校
        return schoolMapper.getSchoolById(schoolId);
    }

    public School getSchoolByName(String schoolName) { //根据校名查询学校
        return schoolMapper.getSchoolByName(schoolName);
    }

    public List<School> getAllSchools() { //查询出所有学校
        return schoolMapper.getAllSchools();
    }

    public Integer getStudentNumberOfSchool(Integer schoolId) {//查询出某一学校内的学生数量
        //查询出某一学校内的所有班级
        List<Clazz> clazzList = clazzMapper.getAllClassOfSchool(schoolId);
        int sum = 0;
        for (Clazz clazz : clazzList) {
            sum += clazzService.getStudentsNumber(clazz.getClassId());
        }
        return sum;
    }

    public Integer getTeacherNumberOfSchool(Integer schoolId) { //查询出某一学校内的老师数量
        List<Clazz> clazzList = clazzMapper.getAllClassOfSchool(schoolId);
        HashSet<Teacher> teachers = new HashSet<>();
        Teacher teacher;
        for (Clazz clazz : clazzList) {
            teacher = teacherService.getTeacherById(clazz.getTeaId());
            teachers.add(teacher);
        }
        return teachers.size();
    }

    public List<Teacher> getAllTeachersOfSchool(Integer schoolId) { //查询某一学校内的所有老师
        List<Clazz> clazzList = clazzMapper.getAllClassOfSchool(schoolId);
        HashSet<Teacher> teachers = new HashSet<>();
        Teacher teacher;
        for (Clazz clazz : clazzList) {
            teacher = teacherService.getTeacherById(clazz.getTeaId());
            teachers.add(teacher);
        }
        return new ArrayList<>(teachers);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer updateSchool(School school) { //修改学校信息
        boolean flag = checkObject.check(school);
        int code = 0;
        if (!flag) {
            School s = schoolMapper.getSchoolById(school.getSchoolId());
            if (s == null) {
                return ResultEnum.UPDATE_ERROR.code;
            }
            if (!(school.getSchoolName() == null || "".equals(school.getSchoolName()))) {
                s.setSchoolName(school.getSchoolName());
            }
            if (!(school.getPrincipal() == null || "".equals(school.getPrincipal()))) {
                s.setPrincipal(school.getPrincipal());
            }
            if (!(school.getSchoolIcon() == null || "".equals(school.getSchoolIcon()))) {
                s.setSchoolIcon(school.getSchoolIcon());
            }
            // 学校简介可以空着
            s.setSchoolText(school.getSchoolText());
            code = schoolMapper.updateSchool(s);
        }
        if (code == 1) {
            return ResultEnum.SUCCESS.code;
        } else {
            return ResultEnum.UPDATE_ERROR.code;
        }
    }

    public Integer getCodeNumberOfSchool(Integer schoolId) {
        //查询学校内的许可码数量
        return clazzMapper.getClassNumberOfSchool(schoolId);
    }
}
