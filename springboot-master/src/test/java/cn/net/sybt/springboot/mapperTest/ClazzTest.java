package cn.net.sybt.springboot.mapperTest;

import cn.net.sybt.springboot.bean.Clazz;
import cn.net.sybt.springboot.bean.School;
import cn.net.sybt.springboot.mapper.ClazzMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.*;

@SpringBootTest
@Transactional
public class ClazzTest {
    @Autowired
    private ClazzMapper clazzMapper;

    @Test
    public void testGetClassById() {
        Clazz clazz = clazzMapper.getClassById(1);
        System.out.println(clazz);
    }

    @Test
    public void testGetClassByQRCode() {
        Clazz clazz = clazzMapper.getClassByQRCode("01981e20-55aa-484e-b5f5-cd0ef2ca9f37");
        System.out.println(clazz);
    }

    @Test
    public void testGetAllClass() {
        List<Clazz> classes = clazzMapper.getAllClass();
        classes.forEach(System.out::println);
    }

    @Test
    public void test() {
        MultiValueMap<School, Clazz> scMap = new LinkedMultiValueMap<>();
        School school = new School();
        School school1 = new School();
        Clazz clazz = new Clazz();
        clazz.setClassName("三年级二班");
        Clazz clazz1 = new Clazz();
        clazz1.setClassName("初一的");
        Clazz clazz2 = new Clazz();
        clazz2.setClassName("高中");
        scMap.add(school, clazz);
        scMap.add(school, clazz1);
        scMap.add(school1, clazz2);
        /*List<Clazz> clazzList = scMap.get(school);
        clazzList.forEach(System.out::println);*/
        System.out.println("scMap:" + scMap);
        System.out.println("===============");
        Set<School> schoolSet = scMap.keySet();
        for (School school2 : schoolSet) {
            System.out.println(school2);
        }
    }

    @Test
    public void testDate() {
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, 1); //截止日期是激活时间的一年后
        Date end = calendar.getTime();
        System.out.println("当前时间：" + now);
        System.out.println("结束时间：" + end);
        System.out.println(now.compareTo(end)); //-1是a小于b，0是a=b，1是a>b
    }

    @Test
    public void testAddClass() {
        Clazz clazz = new Clazz();
        clazz.setClassName("三年级二班");
        clazzMapper.addClass(clazz);
    }

    @Test
    public void testUUID() {
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
    }

    @Test
    public void testGetAllClassOfSchool() {
        List<Clazz> clazzList = clazzMapper.getAllClassOfSchool(1);
        clazzList.forEach(System.out::println);
    }

    @Test
    public void testDeleteClassById() {
        int a = clazzMapper.deleteClassById(1);
        System.out.println(a);
    }

    @Test
    public void tesUpdateClass() {
        Clazz clazz = new Clazz();
        clazz.setClassIcon("没有");
        clazz.setClassId(2);
        clazzMapper.updateClass(clazz);
    }
}
