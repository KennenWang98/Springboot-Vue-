package cn.net.sybt.springboot.controller;

import cn.net.sybt.springboot.bean.Moment;
import cn.net.sybt.springboot.bean.Student;
import cn.net.sybt.springboot.bean.Teacher;
import cn.net.sybt.springboot.consts.Parameters;
import cn.net.sybt.springboot.service.MomentService;
import cn.net.sybt.springboot.service.StudentService;
import cn.net.sybt.springboot.service.TeacherService;
import cn.net.sybt.springboot.utils.*;
import cn.net.sybt.springboot.vo.MomentVO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jca.cci.CciOperationNotSupportedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/moment")
public class MomentController {
    @Resource
    private MomentService momentService;
    @Resource
    private StudentService studentService;
    @Resource
    private TeacherService teacherService;

    @RequestMapping("/add")
    public ResultModel<MomentVO> addMoment(String phoneNumber, Integer classId, String time,
                                           List<String> pictureLinks) throws ParseException {
        if ((phoneNumber == null || "".equals(phoneNumber)) && classId == null && time == null
                && (pictureLinks == null || pictureLinks.size() == 0)) {
            return RetResponse.resp(ResultEnum.INSERT_ERROR.code, "上传失败");
        }
        assert time != null;
        Student student = studentService.getStudentByPhone(phoneNumber);
        Teacher teacher = teacherService.getTeacherByPhone(phoneNumber);
        String name;
        if (student != null) {
            name = student.getStuName();
        } else if (teacher != null) {
            name = teacher.getTeaName();
        } else {
            name = "匿名";
        }
        MomentVO momentVO = new MomentVO(name, time, pictureLinks);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (String pictureLink : pictureLinks) {
            Moment moment = new Moment(classId, pictureLink, format.parse(time), phoneNumber);
            momentService.addMoment(moment);
        }
        return RetResponse.okResp(momentVO);
    }

    @RequestMapping("/class")
    public ResultModel<List<MomentVO>> getClassMoment(@RequestParam Integer classId) {
        List<MomentVO> momentList = momentService.getClassMoment(classId);
        if (momentList == null || momentList.size() == 0) {
            return RetResponse.resp(ResultEnum.NOT_FOUND_PAGES.code, "你的班级还没有人发布微圈呢~");
        }
        return RetResponse.okResp(momentList);
    }

    /*@RequestMapping("/upload")
    public ResultModel<String> upload(@RequestParam("file") MultipartFile file, String time,
                                      String phoneNumber, Integer classId) throws ParseException {
        System.out.println("time: " + time);
        String path = "moment/";
        Boolean result = FileUtil.uploadFile(file, path, "");
        if (result == null) {
            return RetResponse.resp(ResultEnum.INTERNAL_SERVER_ERROR.code, "上传失败，请选择文件");
        } else if (!result) {
            return RetResponse.resp(ResultEnum.INTERNAL_SERVER_ERROR.code, "上传失败");
        } else { //插入数据库
            String link = Parameters.IP_ADDRESS + "/public/moment/" + file.getOriginalFilename();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            DateConverter converter = new DateConverter();
            int flag = momentService.addMoment(new Moment(classId, link, format.parse(time), phoneNumber));
            return flag != 0 ? RetResponse.okResp() : RetResponse.resp(ResultEnum.INTERNAL_SERVER_ERROR.code, "数据库异常，请稍候重试");
        }
    }*/

    @RequestMapping("/upload")
    public ResultModel<MomentVO> upload(@RequestParam("file") MultipartFile file, String time,
                                        String phoneNumber, Integer classId) throws ParseException {
        String path = "moment/";
        String uuid = UUID.randomUUID().toString();
        Boolean result = FileUtil.uploadFile(file, path, uuid);
        if (result == null) {
            return RetResponse.resp(ResultEnum.INTERNAL_SERVER_ERROR.code, "上传失败，请选择文件");
        } else if (!result) {
            return RetResponse.resp(ResultEnum.INTERNAL_SERVER_ERROR.code, "上传失败");
        } else { //插入数据库
            String link = Parameters.IP_ADDRESS + "/public/moment/" + uuid + file.getOriginalFilename();
            System.out.println("link：" +link);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //DateConverter converter = new DateConverter();
            momentService.addMoment(new Moment(classId, link, format.parse(time), phoneNumber));
            List<String> pictures = momentService.getMomentLinks(classId, format.parse(time), phoneNumber);
            if (pictures == null || pictures.size() == 0) {
                return RetResponse.resp(ResultEnum.QUERY_ERROR.code, "没有查到任何照片");
            }
            String name;
            Student student = studentService.getStudentByPhone(phoneNumber);
            Teacher teacher = teacherService.getTeacherByPhone(phoneNumber);
            if (student != null) {
                name = student.getStuName();
            } else if (teacher != null) {
                name = teacher.getTeaName();
            } else {
                name = "匿名";
            }
            MomentVO momentVO = new MomentVO(name, time, pictures);
            return RetResponse.okResp(momentVO);
        }
    }

   /* @RequestMapping("/query")
    public ResultModel<MomentVO> getOneMoment(String time, String phoneNumber, Integer classId) throws ParseException {
        if ((time == null || "".equals(time)) && (phoneNumber == null || "".equals(phoneNumber))
                && classId == null) {
            return RetResponse.resp(ResultEnum.QUERY_ERROR.code, "查询失败，请检查输入参数");
        }
        Student student = studentService.getStudentByPhone(phoneNumber);
        Teacher teacher = teacherService.getTeacherByPhone(phoneNumber);
        String name;
        if (student != null) {
            name = student.getStuName();
        } else if (teacher != null) {
            name = teacher.getTeaName();
        } else {
            name = "匿名";
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(format.parse(time));
        calendar.add(Calendar.HOUR_OF_DAY, 8);
        List<String> pictures = momentService.getMomentLinks(classId, calendar.getTime(), phoneNumber);
        if (pictures == null || pictures.size() == 0) {
            return RetResponse.resp(ResultEnum.QUERY_ERROR.code, "没有查到任何照片");
        }
        return RetResponse.okResp(new MomentVO(name, time, pictures));
    }*/

    @RequestMapping("/query")
    public ResultModel<MomentVO> getOneMoment(String time, String phoneNumber, Integer classId) throws ParseException {
        if ((time == null || "".equals(time)) && (phoneNumber == null || "".equals(phoneNumber))
                && classId == null) {
            return RetResponse.resp(ResultEnum.QUERY_ERROR.code, "查询失败，请检查输入参数");
        }
        System.out.println("time: " + time);
        Student student = studentService.getStudentByPhone(phoneNumber);
        Teacher teacher = teacherService.getTeacherByPhone(phoneNumber);
        String name;
        if (student != null) {
            name = student.getStuName();
        } else if (teacher != null) {
            name = teacher.getTeaName();
        } else {
            name = "匿名";
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateConverter converter = new DateConverter();
        List<String> pictures = momentService.getMomentLinks(classId, format.parse(time), phoneNumber);
        if (pictures == null || pictures.size() == 0) {
            return RetResponse.resp(ResultEnum.QUERY_ERROR.code, "没有查到任何照片");
        }
        return RetResponse.okResp(new MomentVO(name, time, pictures));
    }
}
