package cn.net.sybt.springboot.vo;

import cn.net.sybt.springboot.bean.Clazz;

public class ClazzVO extends Clazz {
    private String teaName;
    private Integer expired;
    private String activeTime; // 激活日期
    private String remainTime; // 截止日期

    public ClazzVO() {
    }

    public ClazzVO(String teaName) {
        this.teaName = teaName;
    }

    public ClazzVO(String teaName, Integer expired) {
        this.teaName = teaName;
        this.expired = expired;
    }

    public ClazzVO(String teaName, Integer expired, String activeTime, String remainTime) {
        this.teaName = teaName;
        this.expired = expired;
        this.activeTime = activeTime;
        this.remainTime = remainTime;
    }

    public ClazzVO(Integer classId, String className, String classText, Integer schoolId, String classQrCode, String classIcon, String code, Integer teaId, String teaName, Integer expired) {
        super(classId, className, classText, schoolId, classQrCode, classIcon, code, teaId);
        this.teaName = teaName;
        this.expired = expired;
    }

    public ClazzVO(Clazz clazz, String teaName) {
        super(clazz.getClassId(), clazz.getClassName(), clazz.getClassText(), clazz.getSchoolId(),
                clazz.getClassQrCode(), clazz.getClassIcon(), clazz.getCode(), clazz.getTeaId());
        this.teaName = teaName;
    }

    public ClazzVO(Clazz clazz, String teaName, Integer expired) {
        super(clazz.getClassId(), clazz.getClassName(), clazz.getClassText(), clazz.getSchoolId(),
                clazz.getClassQrCode(), clazz.getClassIcon(), clazz.getCode(), clazz.getTeaId());
        this.teaName = teaName;
        this.expired = expired;
    }

    public ClazzVO(Clazz clazz, String teaName, Integer expired, String activeTime, String remainTime) {
        super(clazz.getClassId(), clazz.getClassName(), clazz.getClassText(), clazz.getSchoolId(),
                clazz.getClassQrCode(), clazz.getClassIcon(), clazz.getCode(), clazz.getTeaId());
        this.teaName = teaName;
        this.expired = expired;
        this.activeTime = activeTime;
        this.remainTime = remainTime;
    }

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName;
    }

    public Integer getExpired() {
        return expired;
    }

    public void setExpired(Integer expired) {
        this.expired = expired;
    }

    public String getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(String activeTime) {
        this.activeTime = activeTime;
    }

    public String getRemainTime() {
        return remainTime;
    }

    public void setRemainTime(String remainTime) {
        this.remainTime = remainTime;
    }
}
