package cn.net.sybt.springboot.vo;

import cn.net.sybt.springboot.bean.School;

import java.util.List;

public class LastSchoolVO extends School {
    List<ClazzVO> classList;

    public LastSchoolVO() {
    }

    public LastSchoolVO(School school) {
        super(school.getSchoolId(), school.getSchoolName(), school.getPrincipal(), school.getSchoolIcon(), school.getSchoolText());
    }

    public LastSchoolVO(List<ClazzVO> classList) {
        this.classList = classList;
    }

    public LastSchoolVO(School school, List<ClazzVO> classList) {
        super(school.getSchoolId(), school.getSchoolName(), school.getPrincipal(), school.getSchoolIcon(), school.getSchoolText());
        this.classList = classList;
    }

    public List<ClazzVO> getClassList() {
        return classList;
    }

    public void setClassList(List<ClazzVO> classList) {
        this.classList = classList;
    }

    @Override
    public int hashCode() {
        return this.getSchoolId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        LastSchoolVO lastSchoolVO = (LastSchoolVO) obj;
        return this.getSchoolId().equals(lastSchoolVO.getSchoolId());
    }
}
