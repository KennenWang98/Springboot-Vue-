package cn.net.sybt.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude= {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
@MapperScan(basePackages = "cn.net.sybt.springboot.mapper")
public class SpringbootApplication {
    public static void main(String[] args) {
            SpringApplication.run(SpringbootApplication.class, args);
    }
}
