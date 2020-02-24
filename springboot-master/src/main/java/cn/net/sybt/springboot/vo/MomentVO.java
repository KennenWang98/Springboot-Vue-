package cn.net.sybt.springboot.vo;

import java.util.List;
import java.util.Objects;

public class MomentVO {
    private String name;
    private String time;
    private List<String> pictureLink;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<String> getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(List<String> pictureLink) {
        this.pictureLink = pictureLink;
    }

    public MomentVO() {
    }

    public MomentVO(String name, String time, List<String> pictureLink) {
        this.name = name;
        this.time = time;
        this.pictureLink = pictureLink;
    }

    @Override
    public String toString() {
        return "MomentVO{" +
                "name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", pictureLink=" + pictureLink +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MomentVO momentVO = (MomentVO) o;
        return Objects.equals(name, momentVO.name) &&
                Objects.equals(time, momentVO.time) &&
                Objects.equals(pictureLink, momentVO.pictureLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, time, pictureLink);
    }
}
