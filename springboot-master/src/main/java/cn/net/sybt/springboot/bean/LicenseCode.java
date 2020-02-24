package cn.net.sybt.springboot.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class LicenseCode { //许可码
    @TableId(type = IdType.AUTO)
    private Integer licenseId;
    private Date activeTime; // 激活日期
    private Date remainTime; //截止日期
    private Integer isUsed; //是否已经被使用
    private Integer bookId; //教材id
    private String code; //许可码

    public Integer getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(Integer licenseId) {
        this.licenseId = licenseId;
    }

    public Date getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    public Date getRemainTime() {
        return remainTime;
    }

    public void setRemainTime(Date remainTime) {
        this.remainTime = remainTime;
    }

    public Integer getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Integer isUsed) {
        this.isUsed = isUsed;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LicenseCode() {
    }

    public LicenseCode(Integer licenseId, Date activeTime, Date remainTime, Integer isUsed, Integer bookId, String code) {
        this.licenseId = licenseId;
        this.activeTime = activeTime;
        this.remainTime = remainTime;
        this.isUsed = isUsed;
        this.bookId = bookId;
        this.code = code;
    }

    @Override
    public String toString() {
        return "LicenseCode{" +
                "licenseId=" + licenseId +
                ", activeTime=" + activeTime +
                ", remainTime=" + remainTime +
                ", isUsed=" + isUsed +
                ", bookId=" + bookId +
                ", code='" + code + '\'' +
                '}';
    }
}
