package cn.net.sybt.springboot.mapperTest;

import cn.net.sybt.springboot.bean.School;
import cn.net.sybt.springboot.mapper.SchoolMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class SchoolTest {
    @Autowired
    SchoolMapper schoolMapper;

    @Test
    public void testGetSchoolById() {
        School school = schoolMapper.getSchoolById(1);
        System.out.println(school);
    }

    @Test
    public void testGetSchoolByName() {
        System.out.println(schoolMapper.getSchoolByName("大同一中"));
    }

    @Test
    public void testGetAllSchools() {
        List<School> schools = schoolMapper.getAllSchools();
        schools.forEach(System.out::println);
    }

    @Test
    public void testAddSchool() {
        School school = new School();
        school.setSchoolName("山西省实验中学");
        school.setPrincipal("孟祥君");
        school.setSchoolIcon("没见过");
        school.setSchoolText("山西省实验中学是山西唯一一所直属省教育厅管理的省级重点中学、省示范高中，山西唯一一所经教育部批准的具有中外合作办学资格的中学。 山西省实验中学创建于1882年，前身为令德堂书院。");
        schoolMapper.addSchool(school);
    }

    @Test
    public void testDeleteSchoolById() {
        schoolMapper.deleteSchoolById(4);
    }

    @Test
    public void testUpdateSchool() {
        School school = new School();
        school.setSchoolName("衡水中学");
        school.setPrincipal("不认识");
        school.setSchoolIcon("奥利给");
        school.setSchoolText("超级中学");
        school.setSchoolId(3);
        schoolMapper.updateSchool(school);
    }
}
