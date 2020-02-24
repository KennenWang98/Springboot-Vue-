package cn.net.sybt.springboot.mapperTest;

import cn.net.sybt.springboot.bean.TeaMatchSolution;
import cn.net.sybt.springboot.mapper.TeaMatchSolutionMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional

public class TeaMatchSolutionMapperTest {
    @Autowired
    private TeaMatchSolutionMapper teaMatchSolutionMapper;

    @Test
    public void select(){
        List<TeaMatchSolution> moment = teaMatchSolutionMapper.getTeaMatchSolutionById(1);
        moment.forEach(System.out::println);
    }
    @Test
    public void selectAll(){
        List<TeaMatchSolution> allTeaMatchSolution = teaMatchSolutionMapper.getAllTeaMatchSolution();
        allTeaMatchSolution.forEach(System.out::println);
    }
    @Test
    public void insert(){
        TeaMatchSolution test = new TeaMatchSolution();
        test.setLeftLink("test");
        test.setLeftLink("1");
        teaMatchSolutionMapper.addTeaMatchSolution(test);
    }
    @Test
    public void delete(){
        teaMatchSolutionMapper.deleteTeaMatchSolutionById(1);
    }
    @Test
    public void upadate(){
        List<TeaMatchSolution> test = this.teaMatchSolutionMapper.getTeaMatchSolutionById(1);
        test.get(0).setLeftLink("testupdate1");
        teaMatchSolutionMapper.updateTeaMatchSolution(test.get(0));
    }
}
