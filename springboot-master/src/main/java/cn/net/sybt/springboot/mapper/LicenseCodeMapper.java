package cn.net.sybt.springboot.mapper;

import cn.net.sybt.springboot.bean.LicenseCode;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LicenseCodeMapper extends BaseMapper<LicenseCode> {
    @Select("select * from license_code where license_id = #{licenseId}")
    LicenseCode getLicenseCodeById(Integer licenseId); //根据许可码id查询许可码信息

    @Select("select * from license_code where code=#{code}")
    LicenseCode getLicenseCodeByCode(String code); //根据许可码查询许可码信息

    @Select("select * from license_code")
    List<LicenseCode> getAllCodes(); //查询所有的许可码

    @Insert("insert into license_code(active_time, remain_time, is_used, book_id, code)" +
            "values(#{activeTime}, #{remainTime}, #{isUsed}, #{bookId}, #{code})")
    Integer addLicenseCode(LicenseCode licenseCode); //新增一个许可码

    @Delete("delete from license_code where license_id = #{licenseId}")
    Integer deleteLicenseCodeByLicenseId(Integer licenseId); //根据许可码id删除许可码

    @Update("update license_code set active_time = #{activeTime}, remain_time = #{remainTime}, " +
            "is_used = #{isUsed}, book_id = #{bookId}, code = #{code} " +
            "where license_id = #{licenseId}")
    Integer updateLicenseCodeByLicenseId(LicenseCode licenseCode); //根据许可码id更新许可码信息
}
