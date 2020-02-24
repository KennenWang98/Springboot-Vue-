package cn.net.sybt.springboot.service;

import cn.net.sybt.springboot.bean.Moment;
import cn.net.sybt.springboot.bean.Student;
import cn.net.sybt.springboot.bean.Teacher;
import cn.net.sybt.springboot.mapper.MomentMapper;
import cn.net.sybt.springboot.mapper.StudentMapper;
import cn.net.sybt.springboot.mapper.TeacherMapper;
import cn.net.sybt.springboot.utils.CheckObject;
import cn.net.sybt.springboot.utils.DateToString;
import cn.net.sybt.springboot.utils.ResultEnum;
import cn.net.sybt.springboot.vo.MomentVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.rowset.spi.SyncResolver;
import java.util.*;

@Service
@Transactional
public class MomentService {
    @Resource
    private MomentMapper momentMapper;
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private CheckObject checkObject;

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer addMoment(Moment moment) { //发布一条新的微圈动态
        boolean flag = checkObject.check(moment);
        int a = 0;
        if (!flag) {
            a = momentMapper.addMoment(moment);
        }
        if (a == 1) {
            return ResultEnum.SUCCESS.code;
        } else {
            return ResultEnum.INSERT_ERROR.code;
        }
    }

    public List<MomentVO> getClassMoment(Integer classId) { //查询出某个班级的所有微圈，按时间降序
        List<Moment> momentList = momentMapper.getClassMoment(classId);
        if (momentList == null || momentList.size() == 0) {
            return null;
        }
        List<MomentVO> momentVOS = new ArrayList<>();
        for (Moment moment : momentList) {
            Student student = studentMapper.getStudentByPhone(moment.getPhoneNumber());
            Teacher teacher = teacherMapper.getTeacherByPhone(moment.getPhoneNumber());
            String name, phoneNumber = null;
            if (student != null) {
                name = student.getStuName();
                phoneNumber = student.getPhoneNumber();
            } else if (teacher != null) {
                name = teacher.getTeaName();
                phoneNumber = teacher.getPhoneNumber();
            } else {
                name = "匿名";
            }
            List<String> pictures = momentMapper.getMomentLinks(classId, moment.getUploadTime(),
                    phoneNumber);
            momentVOS.add(new MomentVO(name, DateToString.parseTime(moment.getUploadTime()),
                    pictures));
        }
        //给momentVOS去重
        Set<MomentVO> momentVOSet = new HashSet<>(momentVOS);
        List<MomentVO> momentVOList = new ArrayList<>(momentVOSet);
        return momentVOList;
    }

    public List<String> getMomentLinks(Integer classId, Date uploadTime, String phoneNumber) {
        //获取某一次微圈的所有照片
        return momentMapper.getMomentLinks(classId, uploadTime, phoneNumber);
    }
}
