package org.wlc.feeder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/3/26 下午10:20
 */
@SpringBootApplication
@MapperScan("org.wlc.feeder.dao")
@EnableAspectJAutoProxy
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
