package cn.net.sybt.springboot.service;

import cn.net.sybt.springboot.bean.LicenseCode;
import cn.net.sybt.springboot.mapper.LicenseCodeMapper;
import cn.net.sybt.springboot.utils.CheckObject;
import cn.net.sybt.springboot.utils.ResultEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class LicenseCodeService {
    @Resource
    private LicenseCodeMapper mapper;
    @Resource
    private CheckObject checkObject;

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer addLicenseCode(LicenseCode licenseCode) { //添加一个许可码
        boolean flag = checkObject.check(licenseCode);
        if (flag) {
            return ResultEnum.INSERT_ERROR.code;
        } else {
            LicenseCode code = mapper.getLicenseCodeByCode(licenseCode.getCode());
            if (code == null) { //数据库中不存在此许可码，可以添加
                //许可码的是否使用标志和唯一uuid不能为空
                if (licenseCode.getCode() == null || "".equals(licenseCode.getCode())) {
                    return ResultEnum.INSERT_ERROR.code;
                }
                licenseCode.setIsUsed(0);
                return mapper.addLicenseCode(licenseCode);
            } else {
                return ResultEnum.CODE_EXIST.code; //许可码已经存在，不能添加
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer deleteLicenseCodeByLicenseId(Integer licenseId) { //根据许可码id删除许可码
        return mapper.deleteLicenseCodeByLicenseId(licenseId);
    }

    public LicenseCode getLicenseCodeById(Integer licenseId) {//根据许可码id查询许可码信息
        return mapper.getLicenseCodeById(licenseId);
    }


    public LicenseCode getLicenseCodeByCode(String code) {//根据许可码查询许可码信息
        return mapper.getLicenseCodeByCode(code);
    }

    public List<LicenseCode> getAllCodes() {
        return mapper.getAllCodes();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer updateLicenseCodeByLicenseId(LicenseCode licenseCode) {//根据许可码id更新许可码信息
        boolean flag = checkObject.check(licenseCode);
        int a = 0;
        if (!flag) {
            LicenseCode code = mapper.getLicenseCodeById(licenseCode.getLicenseId());
            if (code == null) {
                return ResultEnum.UPDATE_ERROR.code;
            } else if (licenseCode.getIsUsed() == null || licenseCode.getCode() == null || "".equals(licenseCode.getCode())) {
                return ResultEnum.UPDATE_ERROR.code;
            }
            code.setActiveTime(licenseCode.getActiveTime());
            code.setRemainTime(licenseCode.getRemainTime());
            code.setBookId(licenseCode.getBookId());
            code.setCode(licenseCode.getCode());
            a = mapper.updateLicenseCodeByLicenseId(licenseCode);
        }
        if (a == 1) {
            return ResultEnum.SUCCESS.code;
        } else {
            return ResultEnum.UPDATE_ERROR.code;
        }
    }

    public Integer isExpired(String code) { //判断某个许可码是否过期
        // 查询出这个许可码的所有信息
        LicenseCode licenseCode = mapper.getLicenseCodeByCode(code);
        Date now = new Date(); //当前时间
        Date end = licenseCode.getRemainTime(); //截止时间
        if (now.compareTo(end) > 0) { //许可码已经过期
            return 0;
        } else {
            return 1;
        }
    }
}
