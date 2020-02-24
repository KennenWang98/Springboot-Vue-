package cn.net.sybt.springboot.mapperTest;

import cn.net.sybt.springboot.bean.TeaSortSolution;
import cn.net.sybt.springboot.mapper.TeaSortSolutionMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional

public class TeaSortSolutionMapperTest {
    @Autowired
    private TeaSortSolutionMapper teaSortSolutionMapper;

    @Test
    public void select(){
        List<TeaSortSolution> moment = teaSortSolutionMapper.getTeaSortSolutionById(1);
        moment.forEach(System.out::println);
    }
    @Test
    public void selectAll(){
        List<TeaSortSolution> allTeaSortSolution = teaSortSolutionMapper.getAllTeaSortSolution();
        allTeaSortSolution.forEach(System.out::println);
    }
    @Test
    public void insert(){
        TeaSortSolution test = new TeaSortSolution();
        test.setQuesContent("test");
        teaSortSolutionMapper.addTeaSortSolution(test);
    }
    @Test
    public void delete(){
        teaSortSolutionMapper.deleteTeaSortSolutionById(1);
    }
    @Test
    public void upadate(){
        List<TeaSortSolution> test = this.teaSortSolutionMapper.getTeaSortSolutionById(1);
        test.get(0).setQuesContent("testupdate1");
        teaSortSolutionMapper.updateTeaSortSolution(test.get(0));
    }
    
}
