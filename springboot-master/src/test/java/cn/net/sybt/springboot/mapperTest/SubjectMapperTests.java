package cn.net.sybt.springboot.mapperTest;

import cn.net.sybt.springboot.mapper.SubjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@SpringBootTest
@Transactional
public class SubjectMapperTests {
    @Resource
    SubjectMapper subjectMapper;
    @Test
    public void getAllTest() {
        System.out.println(subjectMapper.getAll());
    }
    @Test
    public void getSubjectByIdTest() {
        Integer subjectId = 1;
        System.out.println(subjectMapper.getSubjectById(subjectId));
    }
    @Test
    public void addAndDeleteTest() {
        Integer subjectId = 9;
        System.out.println(subjectMapper.deleteSubject(subjectId));
    }
}
