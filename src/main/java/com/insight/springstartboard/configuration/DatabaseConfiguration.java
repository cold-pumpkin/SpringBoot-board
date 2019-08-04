package com.insight.springstartboard.configuration;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;


@Configuration
@PropertySource("classpath:/application.properties")    // 설정파일 위치 지정
public class DatabaseConfiguration {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")    // 데이터베이스 관련 정보를 사용하도록 지정
    public HikariConfig hikariConfig() {
        return new HikariConfig();  // 히카리CP의 설정파일 생성
    }

    @Bean
    @ConfigurationProperties(prefix = "mybatis.configuration")  // application.properties 설정 중 mybatis 관련 설정 가져옴
    public org.apache.ibatis.session.Configuration mybatisConfig() {
        return new org.apache.ibatis.session.Configuration();   // 가져온 mybatis 설정을 자바 클래스로 변환
    }

    @Bean
    public DataSource dataSource() throws Exception {
        // 히카리CP의 설정 파일을 이용해 데이터베이스와 연결하는 데이터 소스 생성
        /*
        DataSource dataSource = new HikariDataSource(hikariConfig());
        System.out.println("=========================");
        System.out.println(dataSource.toString());
        System.out.println("=========================");
        return dataSource;
        */
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/**/sql-*.xml"));
        sqlSessionFactoryBean.setConfiguration(mybatisConfig());    // mybatis 설정 파일 추가

        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
