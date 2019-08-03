package com.insight.springstartboard.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;


@Configuration
@PropertySource("classpath:/application.properties")    // 설정파일 위치 지정
public class DatabaseConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")   // 데이터베이스 관련 정보를 사용하도록 지정
    public HikariConfig hikariConfig() {
        return new HikariConfig();  // 히카리CP의 설정파일 생성
    }

    @Bean
    public DataSource dataSource() throws Exception {   // 히카리CP의 설정 파일을 이용해 데이터베이스와 연결하는 데이터 소스 생성
        DataSource dataSource = new HikariDataSource(hikariConfig());
        System.out.println("=========================");
        System.out.println(dataSource.toString());
        System.out.println("=========================");
        return dataSource;
    }
}
