package cn.net.sybt.springboot.mapper;

import cn.net.sybt.springboot.bean.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface AdminMapper {
    @Select("select * from admin where username = #{username}")
    Admin getInfo(String username);
}
