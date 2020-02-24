package cn.net.sybt.springboot.controller;

import cn.net.sybt.springboot.bean.Admin;
import cn.net.sybt.springboot.bean.AdminHelper;
import cn.net.sybt.springboot.service.AdminService;
import cn.net.sybt.springboot.utils.CheckObject;
import cn.net.sybt.springboot.utils.ResultEnum;
import cn.net.sybt.springboot.utils.ResultModel;
import cn.net.sybt.springboot.utils.RetResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private AdminService adminService;
    @Resource
    private CheckObject checkObject;

    @RequestMapping("/login")
    public ResultModel<Admin> login(@RequestBody AdminHelper admin) {
        boolean flag = checkObject.check(admin);
        if (!flag) {
            Admin user = adminService.getInfo(admin.getUsername());
            if (user == null){
                return RetResponse.resp(ResultEnum.LOGIN_ERROR.code, ResultEnum.LOGIN_ERROR.msg);
            }
            if (user.getPassword().equals(admin.getPassword())) {
                return RetResponse.okResp(user);
            }
        }
        return RetResponse.resp(ResultEnum.LOGIN_ERROR.code, ResultEnum.LOGIN_ERROR.msg);
    }
}
