package cn.touale.ve;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Touale
 * @description VeApplication
 * @date 2022/6/11 12:34
 */

@SpringBootApplication
@MapperScan("cn.touale.ve.mapper")
public class VeApplication {
    //
    public static void main(String[] args) {
        SpringApplication.run(VeApplication.class, args);
    }
}
