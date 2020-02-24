package cn.net.sybt.springboot.controller;

import cn.net.sybt.springboot.bean.Clazz;
import cn.net.sybt.springboot.bean.School;
import cn.net.sybt.springboot.bean.Teacher;
import cn.net.sybt.springboot.consts.Parameters;
import cn.net.sybt.springboot.service.ClazzService;
import cn.net.sybt.springboot.service.SchoolService;
import cn.net.sybt.springboot.utils.*;
import cn.net.sybt.springboot.vo.SuperSchoolVO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/school")
public class SchoolController {
    @Resource
    private SchoolService schoolService;
    @Resource
    private ClazzService clazzService;
    @Resource
    private CheckObject checkObject;

    @RequestMapping("/add")
    public ResultModel<Integer> addSchool(@RequestBody School school) { //新增学校
        boolean flag = checkObject.check(school);
        int a = 0;
        if (!flag) {
            if (school.getSchoolName() == null || "".equals(school.getSchoolName())) {
                //校名起码不能为空，别的无所谓
                return RetResponse.resp(ResultEnum.INSERT_ERROR.code, "校名不能为空！");
            }
            a = schoolService.addSchool(school);
        }
        if (a == 200) {
            return RetResponse.okResp();
        } else {
            return RetResponse.resp(ResultEnum.INSERT_ERROR.code, ResultEnum.INSERT_ERROR.msg);
        }
    }

    @RequestMapping("/delete")
    public ResultModel<Integer> deleteSchool(@RequestParam Integer schoolId) {//删除学校
        int a = schoolService.deleteSchoolById(schoolId);
        if (a != 0) {
            return RetResponse.okResp();
        } else {
            return RetResponse.resp(ResultEnum.DELETE_ERROR.code, "该学校内存在班级，无法删除！");
        }
    }

    @RequestMapping("/classes")
    public ResultModel<List<Clazz>> getAllClassOfSchool(@RequestParam Integer schoolId) {
        //查出学校里的所有班级;
        return RetResponse.okResp(clazzService.getAllClassOfSchool(schoolId));
    }

    @RequestMapping("/class/number")
    public ResultModel<Integer> getClassNumberOfSchool(@RequestParam Integer schoolId) {
        //查询学校里的班级数量
        return RetResponse.okResp(clazzService.getClassNumberOfSchool(schoolId));
    }

    @RequestMapping("/get-by-id")
    public ResultModel<School> getSchoolById(@RequestParam Integer schoolId) { //根据id查询学校
        return RetResponse.okResp(schoolService.getSchoolById(schoolId));
    }

    @RequestMapping("/get-by-name")
    public ResultModel<School> getSchoolByName(@RequestParam String schoolName) { //根据校名查询学校
        return RetResponse.okResp(schoolService.getSchoolByName(schoolName));
    }

    @RequestMapping("/print")
    public ResultModel<List<SuperSchoolVO>> getAllSchools() {
        //查询出所有学校的信息、学校下的班级数量、学生数量、老师数量、学校的许可码数量
        List<School> schoolList = schoolService.getAllSchools();
        List<SuperSchoolVO> superSchoolVOS = new ArrayList<>();
        for (School school : schoolList) {
            //学校内的班级数量，许可码数量 = 班级数量
            int classNumber = clazzService.getClassNumberOfSchool(school.getSchoolId());
            //学校内的老师数量
            int teacherNumber = schoolService.getTeacherNumberOfSchool(school.getSchoolId());
            //学校内的学生数量
            int studentNumber = schoolService.getStudentNumberOfSchool(school.getSchoolId());
            SuperSchoolVO schoolVO = new SuperSchoolVO(school, classNumber, teacherNumber,
                    studentNumber, classNumber);
            superSchoolVOS.add(schoolVO);
        }
        return RetResponse.okResp(superSchoolVOS);
    }

    @RequestMapping("/teachers")
    public ResultModel<List<Teacher>> getAllTeachersOfSchool(Integer schoolId) {
        //查看某一学校内的所有老师
        List<Teacher> teacherList = schoolService.getAllTeachersOfSchool(schoolId);
        if (teacherList == null || teacherList.size() == 0) {
            return RetResponse.resp(ResultEnum.QUERY_ERROR.code, "此学校暂无老师！");
        }
        return RetResponse.okResp(teacherList);
    }

    @RequestMapping("/change")
    public ResultModel<Integer> updateSchool(@RequestBody School school) {//修改学校信息
        boolean flag = checkObject.check(school);
        int a = 0; //标识SQL语句是否执行成功
        if (!flag) {
            a = schoolService.updateSchool(school);
        }
        if (a == 200) {
            return RetResponse.okResp();
        } else {
            return RetResponse.resp(ResultEnum.UPDATE_ERROR.code, ResultEnum.UPDATE_ERROR.msg);
        }
    }

    @RequestMapping("/upload")
    public ResultModel<String> upload(@RequestParam("file") MultipartFile file) { //上传单个文件
        String uuid = UUID.randomUUID().toString();
        String path = "school/";
        Boolean result = FileUtil.uploadFile(file, path, uuid);
        if (result == null) {
            return RetResponse.resp(ResultEnum.INTERNAL_SERVER_ERROR.code, "上传失败，请选择文件");
        } else if (!result) {
            return RetResponse.resp(ResultEnum.INTERNAL_SERVER_ERROR.code, "上传失败");
        } else {
            return RetResponse.okResp(Parameters.IP_ADDRESS + "/public/school/" + uuid + file.getOriginalFilename());
        }
    }

    @RequestMapping("/code/number")
    public ResultModel<Integer> getCodeNumberOfSchool(Integer schoolId) {
        //统计某一学校内的许可码数量
        return RetResponse.okResp(schoolService.getCodeNumberOfSchool(schoolId));
    }
}
