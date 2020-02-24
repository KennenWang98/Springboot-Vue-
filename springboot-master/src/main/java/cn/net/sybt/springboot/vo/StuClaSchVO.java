package cn.net.sybt.springboot.vo;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class StuClaSchVO {
    private JSONObject userInfo;
    private List<LastSchoolVO> schoolList;
    private String name;
    private String icon;
    private String gender;


    public StuClaSchVO(JSONObject userInfo) {
        this.userInfo = userInfo;
    }

    public StuClaSchVO(JSONObject userInfo, List<LastSchoolVO> schoolList) {
        this.userInfo = userInfo;
        this.schoolList = schoolList;
    }

    public StuClaSchVO(JSONObject userInfo, List<LastSchoolVO> schoolList, String name, String icon, String gender) {
        this.userInfo = userInfo;
        this.schoolList = schoolList;
        this.name = name;
        this.icon = icon;
        this.gender = gender;
    }

    public JSONObject getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(JSONObject userInfo) {
        this.userInfo = userInfo;
    }

    public List<LastSchoolVO> getSchoolList() {
        return schoolList;
    }

    public void setSchoolList(List<LastSchoolVO> schoolList) {
        this.schoolList = schoolList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "StuClaSchVO{" +
                "userInfo=" + userInfo +
                ", lastSchoolVOList=" + schoolList +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
