package org.wlc.feeder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/3/17 下午7:14
 */
@Configuration
public class webConfig {
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/smartPetFeeder");
        dataSource.setUsername("root");
        dataSource.setPassword("147258wang");
        return dataSource;
    }

}
