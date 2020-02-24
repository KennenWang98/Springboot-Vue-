package cn.net.sybt.springboot.service;

import cn.net.sybt.springboot.bean.Book;
import cn.net.sybt.springboot.bean.Clazz;
import cn.net.sybt.springboot.bean.LicenseCode;
import cn.net.sybt.springboot.bean.Student;
import cn.net.sybt.springboot.mapper.BookMapper;
import cn.net.sybt.springboot.mapper.ClazzMapper;
import cn.net.sybt.springboot.mapper.LicenseCodeMapper;
import cn.net.sybt.springboot.utils.CheckObject;
import cn.net.sybt.springboot.utils.ResultEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ClazzService {
    @Resource
    private ClazzMapper clazzMapper;
    @Resource
    private LicenseCodeMapper codeMapper;
    @Resource
    private BookMapper bookMapper;
    @Resource
    CheckObject checkObject;
    @Resource
    private StudentService studentService;
    @Resource
    private HomeworkService homeworkService;

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer addClass(Clazz clazz) { //新建班级
        LicenseCode licenseCode = codeMapper.getLicenseCodeByCode(clazz.getCode());
        if (licenseCode == null || licenseCode.getIsUsed() == 1) {
            //许可码不存在，或许可码已经被使用
            return ResultEnum.CODE_EXPIRED.code;
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.YEAR, 1);
            licenseCode.setActiveTime(new Date()); //当前时间就是激活时间
            licenseCode.setRemainTime(calendar.getTime()); //截止日期是激活时间的一年后
            licenseCode.setIsUsed(1);
            codeMapper.updateLicenseCodeByLicenseId(licenseCode);
            clazzMapper.addClass(clazz);
            return ResultEnum.SUCCESS.code;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer deleteClassById(Integer classId) { //根据班级id删除班级
        //删除班级、student_class表的班级，还有班级的所有作业
        clazzMapper.deleteClassById(classId);
        clazzMapper.deleteAllStudentsOfClass(classId);
        homeworkService.deleteClassHomework(classId);
        return 1;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer deleteClassByCode(String code) { //根据许可码删除班级
        return clazzMapper.deleteClassByCode(code);
    }

    public Clazz getClassById(Integer classId) { //根据id查询班级信息
        return clazzMapper.getClassById(classId);
    }

    public Integer getAllNumber() { //获取数据库中所有班级的数量
        return clazzMapper.getAllNumber();
    }

    public List<Clazz> getAllClass() {
        return clazzMapper.getAllClass(); //查询所有班级信息
    }

    public List<Clazz> getAllClassOfSchool(Integer schoolId) { //查出某个学校里的所有班级
        return clazzMapper.getAllClassOfSchool(schoolId);
    }

    public Integer getClassNumberOfSchool(Integer schoolId) { //查出某个学校里的班级数量
        return clazzMapper.getClassNumberOfSchool(schoolId);
    }

    public List<Integer> getAllStudentsOfClass(Integer classId) { //查询当前班级的所有学生
        return clazzMapper.getAllStudentsOfClass(classId);
    }

    public Integer getStudentsNumber(Integer classId) {//查询当前班级的学生数量
        return clazzMapper.getStudentsNumber(classId);
    }

    public List<Clazz> getAllClassOfTeacher(Integer teaId) { //查出某个老师教的所有班级
        return clazzMapper.getAllClassOfTeacher(teaId);
    }

    public List<Clazz> getAllClassOfTeacherOfSchool(Integer schoolId, Integer teaId) {
        //查询老师在一个学校里教的所有班级
        return clazzMapper.getAllClassOfTeacherOfSchool(schoolId, teaId);
    }

    public Clazz getClassByQRCode(String classQrCode) {
        return clazzMapper.getClassByQRCode(classQrCode);
    }

    public List<Student> getStudentByclassId(Integer classId) {
        // 通过班级ID，返回所有学生
        List<Integer> studentIds = this.getAllStudentsOfClass(classId);
        List<Student> students = new ArrayList<>();
        for (Integer studentId : studentIds) {
            students.add(studentService.getStudent(studentId));
        }
        return students;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer updateClass(Clazz clazz) { //修改班级信息
        boolean flag = checkObject.check(clazz);
        int code = 0;
        if (!flag) {
            Clazz c = clazzMapper.getClassById(clazz.getClassId());
            if (c == null) {
                return ResultEnum.UPDATE_ERROR.code;
            }
            if (!(clazz.getClassName() == null || "".equals(clazz.getClassName()))) {
                c.setClassName(clazz.getClassName());
            }
            if (!(clazz.getSchoolId() == null)) {
                c.setSchoolId(clazz.getSchoolId());
            }
            if (!(clazz.getClassQrCode() == null || "".equals(clazz.getClassQrCode()))) {
                c.setClassQrCode(clazz.getClassQrCode());
            }
            if (!(clazz.getClassIcon() == null || "".equals(clazz.getClassIcon()))) {
                c.setClassIcon(clazz.getClassIcon());
            }
            if (!(clazz.getCode() == null || "".equals(clazz.getCode()))) {
                c.setCode(clazz.getCode());
            }
            if (!(clazz.getTeaId() == null)) {
                c.setTeaId(clazz.getTeaId());
            }
            // 班级简介可以为空
            c.setClassText(clazz.getClassText());
            code = clazzMapper.updateClass(c);
        }
        if (code == 1) {
            return ResultEnum.SUCCESS.code;
        } else {
            return ResultEnum.UPDATE_ERROR.code;
        }
    }

    public Integer isExpired(Integer classId) { //判断这个班级的许可码是否过期
        // 获得这个班级的许可码
        String code = clazzMapper.getCode(classId);
        // 查询出这个许可码的所有信息
        LicenseCode licenseCode = codeMapper.getLicenseCodeByCode(code);
        Date now = new Date(); //当前时间
        Date end = licenseCode.getRemainTime(); //截止时间
        if (now.compareTo(end) >= 0) { //许可码已经过期
            return 0;
        } else {
            return 1;
        }
    }

    public Book getBookOfClass(Integer classId) { //查看班级绑定的教材
        String code = clazzMapper.getCode(classId);
        if (code == null) {
            return null;
        }
        LicenseCode code1 = codeMapper.getLicenseCodeByCode(code);
        if (code1 == null) {
            return null;
        }
        int bookId = code1.getBookId();
        return bookMapper.getBookById(bookId);
    }
}
