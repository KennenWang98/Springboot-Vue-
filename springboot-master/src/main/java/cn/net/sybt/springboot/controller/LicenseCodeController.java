package cn.net.sybt.springboot.controller;

import cn.net.sybt.springboot.bean.LicenseCode;
import cn.net.sybt.springboot.service.LicenseCodeService;
import cn.net.sybt.springboot.utils.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/code")
public class LicenseCodeController {
    @Resource
    private LicenseCodeService service;
    @Resource
    private CheckObject checkObject;

    @RequestMapping("/add")
    public ResultModel<Integer> addLicenseCode(String codeNum,String bookId) {
        if (codeNum == null || bookId == null) {
            return RetResponse.resp(ResultEnum.INSERT_ERROR.code, ResultEnum.INSERT_ERROR.msg);
        }
        for (int i = 0; i < Integer.parseInt(codeNum); i++) {
            LicenseCode licenseCode = new LicenseCode();
            licenseCode.setCode(LicenseCodeGenerator.nextCode());
            licenseCode.setIsUsed(0);
            licenseCode.setBookId(Integer.valueOf(bookId));
            service.addLicenseCode(licenseCode);
        }
        return RetResponse.okResp();
    }

    @RequestMapping("/delete")
    public ResultModel<Integer> deleteLicenseCodeByLicenseId(@RequestParam Integer licenseId) {
        int code = service.deleteLicenseCodeByLicenseId(licenseId);
        if (code != 0) {
            return RetResponse.okResp();
        } else {
            return RetResponse.resp(ResultEnum.DELETE_ERROR.code, ResultEnum.DELETE_ERROR.msg);
        }
    }

    @RequestMapping("/get-by-license")
    public ResultModel<LicenseCode> getLicenseCodeById(@RequestParam Integer licenseId) {
        return RetResponse.okResp(service.getLicenseCodeById(licenseId));
    }

    @RequestMapping("/get-by-code")
    public ResultModel<LicenseCode> getLicenseCodeByCode(@RequestParam String code) {
        return RetResponse.okResp(service.getLicenseCodeByCode(code));
    }

    @RequestMapping("/print")
    public ResultModel<List<LicenseCode>> getAllCodes() {
        return RetResponse.okResp(service.getAllCodes());
    }

    @RequestMapping("/change")
    public ResultModel<Integer> updateLicenseCodeByLicenseId(@RequestBody LicenseCode licenseCode) {
        boolean flag = checkObject.check(licenseCode);
        int a = 0;
        if (!flag) {
            a = service.updateLicenseCodeByLicenseId(licenseCode);
        }
        if (a == 200) {
            return RetResponse.okResp();
        } else {
            return RetResponse.resp(ResultEnum.UPDATE_ERROR.code, ResultEnum.UPDATE_ERROR.msg);
        }
    }
}
