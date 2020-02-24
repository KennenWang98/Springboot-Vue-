package cn.net.sybt.springboot.controller;

import cn.net.sybt.springboot.bean.Clazz;
import cn.net.sybt.springboot.bean.Student;
import cn.net.sybt.springboot.service.*;
import cn.net.sybt.springboot.utils.*;
import cn.net.sybt.springboot.vo.ClazzVO;
import cn.net.sybt.springboot.vo.LastSchoolVO;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Resource
    private StudentService studentService;
    @Resource
    private CheckObject checkObject;
    @Resource
    private LicenseCodeService licenseCodeService;
    @Resource
    private TeacherService teacherService;
    @Resource
    private SchoolService schoolService;
    @Resource
    private ClazzService clazzService;

    @RequestMapping("/get")
    public ResultModel<Student> getStudent(@RequestParam String phone) { //通过手机号获取学生个人信息
        return RetResponse.okResp(studentService.getStudentByPhone(phone));
    }

    @RequestMapping("/classes")
    public ResultModel<List<Clazz>> getAllClassesOfStudent(@RequestParam Integer stuId) {
        //获取学生加入的所有班级
        List<Integer> classIdList = studentService.getAllClassIdOfStudent(stuId);
        List<Clazz> clazzList = new ArrayList<>();
        for (int id : classIdList) {
            clazzList.add(clazzService.getClassById(id));
        }
        if (clazzList.size() == 0) {
            return RetResponse.resp(ResultEnum.QUERY_ERROR.code, "你还未加入任何班级！");
        }
        return RetResponse.okResp(clazzList);
    }

    @RequestMapping("/query")
    public ResultModel<List<Student>> fuzzyQueryStudent(String stuName, Integer classId) { //模糊查询
        if ((stuName == null || "".equals(stuName)) && (classId == null || classId == 0)) {
            return RetResponse.resp(ResultEnum.QUERY_ERROR.code, ResultEnum.QUERY_ERROR.msg);
        }
        List<Student> studentList = clazzService.getStudentByclassId(classId);
        List<Student> result = new ArrayList<>();
        for (Student student : studentList) {
            if (student.getStuName().contains(stuName)) {
                result.add(student);
            }
        }
        if(result.size() == 0){
            return RetResponse.resp(ResultEnum.QUERY_ERROR.code, "没有查到任何人！");
        }
        return RetResponse.okResp(result);
    }

    @RequestMapping("/get-all")
    public ResultModel<List<Student>> getAllStudents() { //查询出所有学生
        return RetResponse.okResp(studentService.getAllStudents());
    }

    @RequestMapping("/add-one")
    public ResultModel<Student> addOneStudent(String phoneNumber, Integer classId) {
        boolean flag = true;
        if ((phoneNumber == null || "".equals(phoneNumber)) && (classId == null || classId == 0)) {
            flag = false;
        }
        int code;
        if (flag) {
            Student student = studentService.getStudentByPhone(phoneNumber);
            if (student == null || checkObject.check(student)) { //学生不在学生表中，直接返回错误信息
                return RetResponse.resp(ResultEnum.INSERT_ERROR.code, "此学生不存在！");
            } else {//学生已经在学生表中，无需添加，只需要把学生id和班级id写入student_class表
                code = studentService.addStudentToClass(student.getStuId(), classId);
            }
            if (code == 200) {
                return RetResponse.okResp(studentService.getStudentByPhone(phoneNumber));
            }
        }
        return RetResponse.resp(ResultEnum.INSERT_ERROR.code, ResultEnum.INSERT_ERROR.msg);
    }

    @RequestMapping("/add")
    public ResultModel<List<Student>> addStudent(String phoneNumber, Integer classId) {
        //添加学生
        boolean flag = true;
        if ((phoneNumber == null || "".equals(phoneNumber)) && (classId == null || classId == 0)) {
            flag = false;
        }
        int code = 0;
        if (flag) {
            Student student = studentService.getStudentByPhone(phoneNumber);
            if (student == null || checkObject.check(student)) { //学生不在学生表中，直接返回错误信息
                return RetResponse.resp(ResultEnum.INSERT_ERROR.code, "此学生不存在！");
            } else {//学生已经在学生表中，无需添加，只需要把学生id和班级id写入student_class表
                code = studentService.addStudentToClass(student.getStuId(), classId);
            }
        }
        if (code == 200) {
            List<Integer> studentIds = clazzService.getAllStudentsOfClass(classId);
            List<Student> students = new ArrayList<>();
            for (Integer studentId : studentIds) {
                students.add(studentService.getStudent(studentId));
            }
            return RetResponse.okResp(students);
        } else {
            return RetResponse.resp(ResultEnum.INSERT_ERROR.code, ResultEnum.INSERT_ERROR.msg);
        }
    }

    @RequestMapping("/join")
    public ResultModel<List<LastSchoolVO>> joinClass(String phoneNumber, String classQrCode) { //学生扫描二维码加入班级
        boolean flag = true;
        if ((phoneNumber == null || "".equals(phoneNumber)) && (classQrCode == null || "".equals(classQrCode))) {
            flag = false;
        }
        int code = 0;
        if (flag) {
            Student student = studentService.getStudentByPhone(phoneNumber);
            if (student == null || checkObject.check(student)) { //学生不在学生表中，直接返回错误信息
                return RetResponse.resp(ResultEnum.INSERT_ERROR.code, "此学生不存在！");
            } else {//学生已经在学生表中，无需添加
                Clazz clazz = clazzService.getClassByQRCode(classQrCode);
                if (clazz == null || checkObject.check(clazz)) {
                    return RetResponse.resp(ResultEnum.INSERT_ERROR.code, "二维码无效！");
                }
                List<Integer> classIds = studentService.getAllClassIdOfStudent(student.getStuId());
                for (int classId : classIds) {
                    if (classId == clazz.getClassId()) { //学生已经加入了此班级
                        return RetResponse.resp(ResultEnum.INSERT_ERROR.code, "该学生在班级中！");
                    }
                }
                //学生存在，且学生之前没有加入该班级，就把学生id和班级id写入student_class表
                code = studentService.addStudentToClass(student.getStuId(), clazz.getClassId());
            }
        }
        List<LastSchoolVO> schoolList = new ArrayList<>(); // 所有学校和学校内的班级
        if (code == 200) {
            Integer stuId = studentService.getStudentByPhone(phoneNumber).getStuId(); //学生id

            // 1.获取学生所在的所有班级id
            List<Integer> classIdList = studentService.getAllClassIdOfStudent(stuId);
            List<Clazz> clazzList = new ArrayList<>();
            for (int id : classIdList) {
                clazzList.add(clazzService.getClassById(id));
            }
            // 2.拿到所有班级id后，到clazz表中查出所有学校id
            List<Integer> schoolIds = new ArrayList<>(); //保存所有学校的id

            MultiValueMap<Integer, Integer> mvMap = new LinkedMultiValueMap<>();//存放所有班级id和与其对应的学校id
            for (Clazz clazz : clazzList) {
                schoolIds.add(clazz.getSchoolId());
                //学校id放在前面，因为一个学校可能有多个班级
                mvMap.add(clazz.getSchoolId(), clazz.getClassId());
            }

            //遍历学校id，取得每个学校id对应的多个班级id
            for (Integer id : schoolIds) {
                List<Clazz> tmpClazz = new ArrayList<>();
                for (Integer tinyId : Objects.requireNonNull(mvMap.get(id))) { //遍历每个班级id
                    tmpClazz.add(clazzService.getClassById(tinyId)); //把当前班级加入List中
                }
                List<ClazzVO> clazzVOList = new ArrayList<>();
                for (Clazz clazz : tmpClazz) {
                    int a = licenseCodeService.isExpired(clazz.getCode()); //0是过期，1是没有过期
                    Date date1 = licenseCodeService.getLicenseCodeByCode(clazz.getCode()).getActiveTime();
                    Date date2 = licenseCodeService.getLicenseCodeByCode(clazz.getCode()).getRemainTime();
                    ClazzVO clazzVO = new ClazzVO(clazz, teacherService.getTeacherById(clazz.getTeaId()).getTeaName(), a,
                            DateToString.parseTime(date1), DateToString.parseTime(date2));
                    clazzVOList.add(clazzVO);
                }
                LastSchoolVO clsSchVO = new LastSchoolVO(schoolService.getSchoolById(id), clazzVOList);
                schoolList.add(clsSchVO);
            }
        } else {
            return RetResponse.resp(ResultEnum.INTERNAL_SERVER_ERROR.code, ResultEnum.INTERNAL_SERVER_ERROR.msg);
        }
        //给List去重，需要重写对象的equals和hashcode方法
        Set<LastSchoolVO> set = new HashSet<>(schoolList);
        List<LastSchoolVO> newList = new ArrayList<>(set);
        return RetResponse.okResp(newList);
    }

    @RequestMapping("/delete")
    public ResultModel<List<Student>> deleteStudent(String phoneNumber, Integer classId) { //将学生从某一班级中删除
        boolean flag = true;
        if ((phoneNumber == null || "".equals(phoneNumber)) && classId == null) {
            flag = false;
        }
        if (flag) {
            int code = studentService.deleteStudentFromClass(studentService.getStudentByPhone(phoneNumber).getStuId(), classId);
            if (code != 0) {
                List<Integer> studentIds = clazzService.getAllStudentsOfClass(classId);
                List<Student> students = new ArrayList<>();
                for (Integer studentId : studentIds) {
                    students.add(studentService.getStudent(studentId));
                }
                return RetResponse.okResp(students);
            }
        }
        return RetResponse.resp(ResultEnum.DELETE_ERROR.code, ResultEnum.DELETE_ERROR.msg);
    }

    @RequestMapping("/change")
    public ResultModel<Integer> updateStudent(@RequestBody Student student) { //更新学生个人信息
        boolean flag = checkObject.check(student);
        int code = 0;
        if (!flag) {
            code = studentService.updateStudent(student);
        }
        if (code == 200) {
            return RetResponse.okResp();
        } else {
            return RetResponse.resp(ResultEnum.UPDATE_ERROR.code, ResultEnum.UPDATE_ERROR.msg);
        }
    }
}
