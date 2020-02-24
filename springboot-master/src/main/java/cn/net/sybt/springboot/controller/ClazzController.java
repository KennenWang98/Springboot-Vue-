package cn.net.sybt.springboot.controller;

import cn.net.sybt.springboot.bean.*;
import cn.net.sybt.springboot.consts.Parameters;
import cn.net.sybt.springboot.service.*;
import cn.net.sybt.springboot.utils.*;
import cn.net.sybt.springboot.vo.ClazzVO;
import cn.net.sybt.springboot.vo.LastSchoolVO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/class")
public class ClazzController {
    @Resource
    private ClazzService clazzService;
    @Resource
    private StudentService studentService;
    @Resource
    private TeacherService teacherService;
    @Resource
    private SchoolService schoolService;
    @Resource
    private LicenseCodeService licenseCodeService;
    @Resource
    private CheckObject checkObject;

    @RequestMapping("/get")
    public ResultModel<Clazz> getClass(@RequestParam Integer classId) { //根据id查询班级信息
        Clazz clazz1 = clazzService.getClassById(classId);
        if (clazz1 == null) {
            return RetResponse.resp(ResultEnum.QUERY_ERROR.code, ResultEnum.QUERY_ERROR.msg);
        }
        return RetResponse.okResp(clazz1);
    }

    @RequestMapping("/print")
    public ResultModel<List<Clazz>> getAllClasses() { //查询出所有的班级信息
        List<Clazz> clazzList = clazzService.getAllClass();
        if (clazzList == null || clazzList.size() == 0) {
            return RetResponse.resp(ResultEnum.QUERY_ERROR.code, ResultEnum.QUERY_ERROR.msg);
        }
        return RetResponse.okResp(clazzList);
    }

    @RequestMapping("/students")
    public ResultModel<List<Student>> getAllStudentsOfClass(@RequestParam Integer classId) {
        //查询当前班级的所有学生
        List<Integer> studentIds = clazzService.getAllStudentsOfClass(classId);
        if (studentIds == null || studentIds.isEmpty()) {
            return RetResponse.resp(ResultEnum.QUERY_ERROR.code, "此班级暂无学生！");
        }
        List<Student> students = new ArrayList<>();
        for (Integer studentId : studentIds) {
            students.add(studentService.getStudent(studentId));
        }
        return RetResponse.okResp(students);
    }

    @RequestMapping("/number")
    public ResultModel<Integer> getStudentsNumber(@RequestParam Integer classId) {
        //查询当前班级的学生数量
        return RetResponse.okResp(clazzService.getStudentsNumber(classId));
    }

    @RequestMapping("/add")
    public ResultModel<List<LastSchoolVO>> addClass(String code, String className, String classText,
                                                    String schoolName, String phoneNumber, String classIcon) {//添加班级
        if (("".equals(code) || code == null) && ("".equals(className) || className == null) &&
                ("".equals(classText) || classText == null) && ("".equals(schoolName) ||
                schoolName == null) && (phoneNumber == null || "".equals(phoneNumber)) && (classIcon == null
                || "".equals(classIcon))) {
            return RetResponse.resp(ResultEnum.INSERT_ERROR.code, ResultEnum.INSERT_ERROR.msg);
        }
        School school = schoolService.getSchoolByName(schoolName);
        Teacher teacher = teacherService.getTeacherByPhone(phoneNumber);
        if (teacher == null) {
            return RetResponse.resp(ResultEnum.INSERT_ERROR.code, ResultEnum.INSERT_ERROR.msg);
        }
        if (school == null) {
            school = new School();
            school.setSchoolName(schoolName);
            schoolService.addSchool(school);
            school = schoolService.getSchoolByName(schoolName);
        }
        //判断许可码的合法性
        LicenseCode code1 = licenseCodeService.getLicenseCodeByCode(code);
        if (code1 == null || code1.getIsUsed() == 1) {
            return RetResponse.resp(ResultEnum.CODE_EXPIRED.code, ResultEnum.CODE_EXPIRED.msg);
        }
        Clazz clazz = new Clazz();
        clazz.setTeaId(teacher.getTeaId());
        clazz.setClassName(className);
        clazz.setCode(code);
        clazz.setClassText(classText);
        clazz.setSchoolId(school.getSchoolId());
        clazz.setClassQrCode(code);
        clazz.setClassIcon(classIcon);
        int a = clazzService.addClass(clazz);
        if (a == 200) { //新建班级成功
            List<Clazz> clazzList = clazzService.getAllClassOfTeacher(teacher.getTeaId());
            List<LastSchoolVO> schoolList = new ArrayList<>(); // 所有学校和学校内的班级
            // 获取每个班级对应的学校
            for (Clazz tmp : clazzList) {
                School tmpSchool = schoolService.getSchoolById(tmp.getSchoolId());
                List<ClazzVO> vos = new ArrayList<>();
                List<Clazz> clazzList1 = clazzService.getAllClassOfTeacherOfSchool(tmp.getSchoolId(), teacher.getTeaId());
                for (Clazz clazz1 : clazzList1) {
                    int num = licenseCodeService.isExpired(code);
                    Date date1 = licenseCodeService.getLicenseCodeByCode(code).getActiveTime();
                    Date date2 = licenseCodeService.getLicenseCodeByCode(code).getRemainTime();
                    ClazzVO vo = new ClazzVO(clazz1, teacherService.getTeacherById(clazz1.getTeaId()).getTeaName(), num,
                            DateToString.parseTime(date1), DateToString.parseTime(date2));
                    vos.add(vo);
                }
                LastSchoolVO lastSchoolVO = new LastSchoolVO(tmpSchool, vos);
                schoolList.add(lastSchoolVO);
            }
            //给List去重，需要重写对象的equals和hashcode方法
            Set<LastSchoolVO> set = new HashSet<>(schoolList);
            List<LastSchoolVO> newList = new ArrayList<>(set);
            return RetResponse.okResp(newList);
        } else {
            return RetResponse.resp(ResultEnum.INSERT_ERROR.code, ResultEnum.INSERT_ERROR.msg);
        }
    }

    @RequestMapping("/delete")
    public ResultModel<Integer> deleteClassById(@RequestParam Integer classId) { //根据班级id删除班级
        int code = clazzService.deleteClassById(classId);
        if (code == 1) {
            return RetResponse.okResp();
        } else {
            return RetResponse.resp(ResultEnum.DELETE_ERROR.code, ResultEnum.DELETE_ERROR.msg);
        }
    }

    @RequestMapping("/delete-by-code")
    public ResultModel<Integer> deleteClassByCode(@RequestParam String code) { //根据许可码删除班级
        int a = clazzService.deleteClassByCode(code);
        if (a != 0) {
            return RetResponse.okResp();
        } else {
            return RetResponse.resp(ResultEnum.DELETE_ERROR.code, ResultEnum.DELETE_ERROR.msg);
        }
    }

    @RequestMapping("/change")
    public ResultModel<Integer> updateClass(@RequestBody Clazz clazz) { //修改班级信息
        boolean flag = checkObject.check(clazz);
        int code = 0;
        if (!flag) {
            code = clazzService.updateClass(clazz);
        }
        if (code == 200) { //成功
            return RetResponse.okResp();
        } else {
            return RetResponse.resp(ResultEnum.UPDATE_ERROR.code, ResultEnum.UPDATE_ERROR.msg);
        }
    }

    @RequestMapping("/book")
    public ResultModel<List<Book>> getBookOfClass(Integer classId) { //查看班级绑定的教材
        Book book = clazzService.getBookOfClass(classId);
        if (book == null) {
            return RetResponse.resp(ResultEnum.QUERY_ERROR.code, ResultEnum.QUERY_ERROR.msg);
        }
        List<Book> books = new ArrayList<>();
        books.add(book);
        return RetResponse.okResp(books);
    }

    @RequestMapping("/upload")
    public ResultModel<String> upload(@RequestParam("file") MultipartFile file) { //上传单个文件
        String uuid = UUID.randomUUID().toString();
        String path = "class/";
        Boolean result = FileUtil.uploadFile(file, path, uuid);
        if (result == null) {
            return RetResponse.resp(ResultEnum.INTERNAL_SERVER_ERROR.code, "上传失败，请选择文件");
        } else if (!result) {
            return RetResponse.resp(ResultEnum.INTERNAL_SERVER_ERROR.code, "上传失败");
        } else {
            return RetResponse.okResp(Parameters.IP_ADDRESS + "/public/class/" + uuid + file.getOriginalFilename());
        }
    }
}
