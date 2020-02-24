package cn.net.sybt.springboot.mapperTest;

import cn.net.sybt.springboot.mapper.MomentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@SpringBootTest
@Transactional
public class MomentTest {
    @Autowired
    MomentMapper momentMapper;

    @Test
    public void testQuery() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(momentMapper.getMomentLinks(2,
                format.parse("2020-01-03 17:00:25"), "2"));
    }
}
