package cn.net.sybt.springboot.service;

import cn.net.sybt.springboot.bean.Student;
import cn.net.sybt.springboot.mapper.StudentMapper;
import cn.net.sybt.springboot.utils.CheckObject;
import cn.net.sybt.springboot.utils.PinyinUtil;
import cn.net.sybt.springboot.utils.ResultEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class StudentService {
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private CheckObject checkObject;

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer addStudent(Student student) { //添加学生
        boolean flag = checkObject.check(student);
        int code = 0;
        if (!flag) {
            code = studentMapper.addStudent(student);
        }
        if (code != 0) {
            //将学生的拼音字段设置成拼音
            student.setPinyin(PinyinUtil.getPingYin(student.getStuName()));
            studentMapper.updateStudent(student);
            return ResultEnum.SUCCESS.code;
        } else {
            return ResultEnum.INSERT_ERROR.code;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer addStudentToClass(Integer stuId, Integer classId) {
        int code = studentMapper.addStudentToClass(stuId, classId);
        if (code != 0) {
            return ResultEnum.SUCCESS.code;
        } else {
            return ResultEnum.INSERT_ERROR.code;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer deleteStudent(Integer stuId) { //删除学生
        return studentMapper.deleteStudentById(stuId);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer deleteStudentFromClass(Integer stuId, Integer classId) {
        return studentMapper.deleteStudentFromClass(stuId, classId);
    }

    public Student getStudent(Integer stuId) { //通过id获取学生个人信息
        return studentMapper.getStudentById(stuId);
    }

    public Student getStudentByPhone(String phoneNumber) {//根据手机号查询学生
        return studentMapper.getStudentByPhone(phoneNumber);
    }

    public List<Student> fuzzyQuery(String stuName) { //模糊查询
        return studentMapper.fuzzyQuery(stuName);
    }

    public List<Integer> getAllClassIdOfStudent(Integer stuId) { //查询这个学生加入的所有班级的id
        return studentMapper.getAllClassIdOfStudent(stuId);
    }

    public List<Student> getAllStudents() { //查询出所有学生
        return studentMapper.getAllStudents();
    }

    public Integer getStudentIdByPhone(String phoneNumber) {//根据手机号查询学生ID
        return studentMapper.getStudentByPhone(phoneNumber).getStuId();
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public Integer updateStudent(Student student) { //修改学生信息
        boolean flag = checkObject.check(student);
        int code = 0;
        if (!flag) {
            Student s = studentMapper.getStudentByPhone(student.getPhoneNumber());
            if (s == null) {
                return ResultEnum.UPDATE_ERROR.code;
            } else if (student.getStuName() == null || "".equals(student.getStuName())) {
                return ResultEnum.UPDATE_ERROR.code;
            }
            s.setStuName(student.getStuName());
            s.setPinyin(PinyinUtil.getPingYin(student.getStuName()));
            if (!(student.getStuIcon() == null || "".equals(student.getStuIcon()))) {
                s.setStuIcon(student.getStuIcon());
            }
            if (!(student.getGender() == null || "".equals(student.getGender()))) {
                s.setGender(student.getGender());
            }
            code = studentMapper.updateStudent(s);
        }
        if (code == 1) {
            return ResultEnum.SUCCESS.code;
        } else {
            return ResultEnum.UPDATE_ERROR.code;
        }
    }
}
