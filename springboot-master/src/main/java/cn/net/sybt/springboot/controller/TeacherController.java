package cn.net.sybt.springboot.controller;

import cn.net.sybt.springboot.bean.Clazz;
import cn.net.sybt.springboot.bean.School;
import cn.net.sybt.springboot.bean.Student;
import cn.net.sybt.springboot.bean.Teacher;
import cn.net.sybt.springboot.service.*;
import cn.net.sybt.springboot.utils.*;
import cn.net.sybt.springboot.vo.ClazzVO;
import cn.net.sybt.springboot.vo.LastSchoolVO;
import cn.net.sybt.springboot.vo.StuClaSchVO;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Resource
    private TeacherService teacherService;
    @Resource
    private StudentService studentService;
    @Resource
    private ClazzService clazzService;
    @Resource
    private SchoolService schoolService;
    @Resource
    private LicenseCodeService licenseCodeService;
    @Resource
    private CheckObject checkObject;

    @RequestMapping("/get")
    public ResultModel<Teacher> getTeacher(@RequestParam Integer teaId) {//通过id获取老师个人信息
        return RetResponse.okResp(teacherService.getTeacherById(teaId));
    }

    @RequestMapping("/classes")
    public ResultModel<List<Clazz>> getAllClassesOfTeacher(@RequestBody Teacher teacher) {
        boolean flag = checkObject.check(teacher);
        if (!flag && teacher.getTeaId() != null) {
            //获取老师教的所有班级
            return RetResponse.okResp(clazzService.getAllClassOfTeacher(teacher.getTeaId()));
        } else {
            return RetResponse.resp(ResultEnum.INTERNAL_SERVER_ERROR.code, "结果未找到，请检查输入参数");
        }
    }

    @RequestMapping("/print")
    public ResultModel<List<Teacher>> getAllTeachers() { //查询出所有老师
        return RetResponse.okResp(teacherService.getAllTeachers());
    }

    @RequestMapping("/delete")
    public ResultModel<Integer> deleteTeacher(@RequestParam Integer teaId) {//根据id删除老师
        int code = teacherService.deleteTeacherById(teaId);
        if (code == 0) {
            return RetResponse.resp(ResultEnum.DELETE_ERROR.code, "删除失败，目标老师不存在或仍在多个班级内任教！");
        }
        return RetResponse.okResp();
    }

    @RequestMapping("/add")
    public ResultModel<Integer> addTeacher(@RequestBody Teacher teacher) { //添加老师
        boolean flag = checkObject.check(teacher);
        int code = 0;
        if (!flag) {
            code = teacherService.addTeacher(teacher);
        }
        if (code == 200) {
            return RetResponse.okResp();
        } else {
            return RetResponse.resp(ResultEnum.INSERT_ERROR.code, ResultEnum.INSERT_ERROR.msg);
        }
    }

    @RequestMapping("/change")
    public ResultModel<Integer> updateTeacher(@RequestBody Teacher teacher) {//更新老师个人信息
        boolean flag = checkObject.check(teacher);
        int code = 0;
        if (!flag) {
            code = teacherService.updateTeacher(teacher);
        }
        if (code == 200) {
            return RetResponse.okResp();
        } else {
            return RetResponse.resp(ResultEnum.UPDATE_ERROR.code, ResultEnum.UPDATE_ERROR.msg);
        }
    }

    @RequestMapping("/upload")
    public ResultModel<String> upload(@RequestParam("file") MultipartFile file) { //上传单个文件
        String uuid = UUID.randomUUID().toString();
        String path = "teacher/";
        Boolean result = FileUtil.uploadFile(file, path, uuid);
        if (result == null) {
            return RetResponse.resp(ResultEnum.INTERNAL_SERVER_ERROR.code, "上传失败，请选择文件");
        } else if (!result) {
            return RetResponse.resp(ResultEnum.INTERNAL_SERVER_ERROR.code, "上传失败");
        } else {
            return RetResponse.okResp("classpath://public/teacher/" + uuid);
        }
    }

    @RequestMapping("/analyse")
    public ResultModel<Teacher> analyse(@RequestParam("encryptedData") String encryptedData,
                                        @RequestParam("code") String code,
                                        @RequestParam("iv") String iv) {
        if (encryptedData == null || code == null || iv == null) {
            return RetResponse.resp(ResultEnum.INTERNAL_SERVER_ERROR.code, ResultEnum.INTERNAL_SERVER_ERROR.msg);
        }
        JSONObject sessionKey = WechatUtil.getSessionKeyOrOpenId(code);
        JSONObject userInfo = WechatUtil.getUserInfo(encryptedData, sessionKey.get("session_key").toString(), iv);
        String phone = userInfo.get("phoneNumber").toString();
        Teacher teacher = teacherService.getTeacherByPhone(phone);
        return RetResponse.okResp(teacher);
    }

    @RequestMapping("/deciphering")
    public ResultModel<StuClaSchVO> deciphering(@RequestParam("encryptedData") String encryptedData,
                                                @RequestParam("code") String code,
                                                @RequestParam("iv") String iv,
                                                @RequestParam("identity") String identity,
                                                @RequestParam("teaName") String teaName,
                                                @RequestParam("teaIcon") String teaIcon,
                                                @RequestParam("gender") String gender) {
        if (encryptedData == null || code == null || iv == null || "".equals(identity)) {
            return RetResponse.resp(ResultEnum.INTERNAL_SERVER_ERROR.code, ResultEnum.INTERNAL_SERVER_ERROR.msg);
        }
        JSONObject sessionKey = WechatUtil.getSessionKeyOrOpenId(code);
        JSONObject userInfo = WechatUtil.getUserInfo(encryptedData, sessionKey.get("session_key").toString(), iv);
        String phone = userInfo.get("phoneNumber").toString();
        StuClaSchVO schVO = new StuClaSchVO(userInfo); //设置用户信息
        if ("teacher".equals(identity)) {
            Teacher t = teacherService.getTeacherByPhone(phone);
            if (t == null) { //如果数据库中没有该老师，就插入数据库
                Teacher teacher = new Teacher(teaName, phone, teaIcon, gender);
                teacherService.addTeacher(teacher);
            }
            //获取老师所在的所有班
            List<Clazz> clazzList = clazzService.getAllClassOfTeacher(
                    teacherService.getTeacherByPhone(phone).getTeaId());
            List<LastSchoolVO> schoolList = new ArrayList<>(); // 存放学校以及它里面的所有班级
            //获取每个班级对应的学校
            for (Clazz clazz : clazzList) {
                School school = schoolService.getSchoolById(clazz.getSchoolId());
                List<Clazz> clazz1 = clazzService.getAllClassOfTeacherOfSchool(clazz.getSchoolId(), teacherService.getTeacherByPhone(phone).getTeaId());
                List<ClazzVO> clazzVOList = new ArrayList<>();
                for (Clazz clazz2 : clazz1) {
                    int a = licenseCodeService.isExpired(clazz2.getCode()); //0是过期，1是没有过期
                    Date date1 = licenseCodeService.getLicenseCodeByCode(clazz2.getCode()).getActiveTime();
                    Date date2 = licenseCodeService.getLicenseCodeByCode(clazz2.getCode()).getRemainTime();
                    ClazzVO vo = new ClazzVO(clazz2, teacherService.getTeacherById(clazz2.getTeaId()).getTeaName(), a,
                            DateToString.parseTime(date1), DateToString.parseTime(date2));
                    clazzVOList.add(vo);
                }
                LastSchoolVO tmp = new LastSchoolVO(school, clazzVOList);
                schoolList.add(tmp);
            }
            //去重
            Set<LastSchoolVO> set = new HashSet<>(schoolList);
            List<LastSchoolVO> newList = new ArrayList<>(set);
            schVO.setSchoolList(newList);
            Teacher teacher = teacherService.getTeacherByPhone(phone);
            schVO.setName(teacher.getTeaName());
            schVO.setIcon(teacher.getTeaIcon());
            schVO.setGender(teacher.getGender());
        } else if ("student".equals(identity)) {
            Student s = studentService.getStudentByPhone(phone);
            if (s == null) { ////如果数据库中没有该学生，就插入数据库
                Student student = new Student(teaName, phone, teaIcon, PinyinUtil.getPingYin(teaName), gender);
                studentService.addStudent(student);
            }
            List<LastSchoolVO> schoolList = new ArrayList<>(); // 存放学校以及它里面的所有班级

            // 1.获取学生所在的所有班级的id
            List<Clazz> clazzList = new ArrayList<>(); // 存放学生所在的所有班级
            List<Integer> classIdList = studentService.getAllClassIdOfStudent(
                    studentService.getStudentByPhone(phone).getStuId());
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
                List<ClazzVO> clazzVOList = new ArrayList<>();
                for (Integer tinyId : Objects.requireNonNull(mvMap.get(id))) { //遍历每个班级id
                    tmpClazz.add(clazzService.getClassById(tinyId)); //把当前班级加入List中
                }
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
            //去重
            Set<LastSchoolVO> set = new HashSet<>(schoolList);
            List<LastSchoolVO> newList = new ArrayList<>(set);
            schVO.setSchoolList(newList);
            Student student = studentService.getStudentByPhone(phone);
            schVO.setName(student.getStuName());
            schVO.setIcon(student.getStuIcon());
            schVO.setGender(student.getGender());
        } else {
            return RetResponse.resp(ResultEnum.INTERNAL_SERVER_ERROR.code, ResultEnum.INTERNAL_SERVER_ERROR.msg);
        }
        return RetResponse.okResp(schVO);
    }

    @RequestMapping("/login")
    public ResultModel<List<LastSchoolVO>> login(@RequestParam("identity") String identity,
                                                 @RequestParam("phoneNumber") String phoneNumber) {
        List<LastSchoolVO> schoolList = new ArrayList<>(); // 所有学校和学校内的班级
        if ("teacher".equals(identity)) {
            Integer teaId = teacherService.getTeacherByPhone(phoneNumber).getTeaId(); //老师id
            List<Clazz> clazzList = clazzService.getAllClassOfTeacher(teaId);

            // 获取每个班级对应的学校
            for (Clazz clazz : clazzList) {
                School school = schoolService.getSchoolById(clazz.getSchoolId());
                List<ClazzVO> clazzVOList = new ArrayList<>();
                List<Clazz> clazzList1 = clazzService.getAllClassOfTeacherOfSchool(clazz.getSchoolId(), teaId);
                for (Clazz clazz1 : clazzList1) {
                    int a = licenseCodeService.isExpired(clazz1.getCode()); //0是过期，1是没有过期
                    Date date1 = licenseCodeService.getLicenseCodeByCode(clazz1.getCode()).getActiveTime();
                    Date date2 = licenseCodeService.getLicenseCodeByCode(clazz1.getCode()).getRemainTime();
                    ClazzVO vo = new ClazzVO(clazz1, teacherService.getTeacherById(clazz1.getTeaId()).getTeaName(), a,
                            DateToString.parseTime(date1), DateToString.parseTime(date2));
                    clazzVOList.add(vo);
                }
                LastSchoolVO tmp = new LastSchoolVO(school, clazzVOList);
                schoolList.add(tmp);
            }
        } else if ("student".equals(identity)) {
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
}
