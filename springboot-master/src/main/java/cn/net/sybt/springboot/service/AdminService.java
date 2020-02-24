package cn.net.sybt.springboot.service;

import cn.net.sybt.springboot.bean.Admin;
import cn.net.sybt.springboot.mapper.AdminMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminService {
    @Resource
    private AdminMapper adminMapper;

    public Admin getInfo(String username) {
        return adminMapper.getInfo(username);
    }
}
