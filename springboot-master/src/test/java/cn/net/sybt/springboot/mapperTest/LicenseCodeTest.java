package cn.net.sybt.springboot.mapperTest;

import cn.net.sybt.springboot.bean.LicenseCode;
import cn.net.sybt.springboot.mapper.LicenseCodeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SpringBootTest
@Transactional
public class LicenseCodeTest {
    @Autowired
    LicenseCodeMapper licenseCodeMapper;

    @Test
    public void testGetLicenseCodeById() {
        LicenseCode licenseCode = licenseCodeMapper.getLicenseCodeById(1);
        System.out.println(licenseCode);
    }

    @Test
    public void testGetLicenseCodeByCode() {
        LicenseCode licenseCode = licenseCodeMapper.getLicenseCodeByCode("abc");
        System.out.println(licenseCode);
    }

    @Test
    public void testAddLicenseCode() {
        LicenseCode licenseCode = new LicenseCode();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        licenseCode.setActiveTime(new Date()); //激活时间就是此刻
        calendar.add(Calendar.YEAR, 1); //截止日期是激活时间的一年后
        licenseCode.setRemainTime(calendar.getTime());
        System.out.println("激活时间：" + licenseCode.getActiveTime());
        System.out.println("到期时间：" + licenseCode.getRemainTime());
        licenseCode.setBookId(1);
        licenseCode.setIsUsed(0);
        licenseCode.setCode("ABCDEFG");
        licenseCodeMapper.addLicenseCode(licenseCode);
    }

    @Test
    public void testSDF(){
        Date now = new Date();
        System.out.println(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf.format(now));
    }

    @Test
    public void testDeleteLicenseCodeByLicenseId() {
        licenseCodeMapper.deleteLicenseCodeByLicenseId(3);
    }

    @Test
    public void testUpdateLicenseCode() {
        LicenseCode licenseCode = new LicenseCode();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        licenseCode.setActiveTime(new Date()); //激活时间就是此刻
        calendar.add(Calendar.YEAR, 1); //截止日期是激活时间的一年后
        licenseCode.setRemainTime(calendar.getTime());
        System.out.println("激活时间：" + licenseCode.getActiveTime());
        System.out.println("到期时间：" + licenseCode.getRemainTime());
        licenseCodeMapper.updateLicenseCodeByLicenseId(licenseCode);
    }
}
