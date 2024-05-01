package com.tech.vision.api.config;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DatasourceConfig {
   /*
    @Bean
    @Primary
    @ConfigurationProperties("com.tech.vision.api")
    public HikariDataSource hikariDataSource() {
        return DataSourceBuilder
                .create()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(HikariDataSource hikariDataSource) {
        return new JdbcTemplate(hikariDataSource);
    }
    */
	
    private static final int MAX_LIFE_TIME = 90000;
    private static final int MAXIMUM_POOL_SIZE = 10;
    
	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;
	
	@Value("${spring.datasource.username}")
	private String userName;
	@Value("${spring.datasource.password}")
	private String password;
	
   
	@Bean
	@Primary
    public DataSource dataSource() {
         // instantiate, configure and return DataSource
		 DriverManagerDataSource dataSource = new DriverManagerDataSource ();
		 dataSource.setDriverClassName(driverClassName);
		 dataSource.setUrl(url);
		 dataSource.setUsername(userName);
		 dataSource.setPassword(password);
		//return dataSource;
		 //Creating Connection POOL by using Hikari
		 HikariDataSource hikariDataSource = new HikariDataSource();
	        hikariDataSource.setDataSource(dataSource);
	        hikariDataSource.setMaximumPoolSize(MAXIMUM_POOL_SIZE);
	        hikariDataSource.setAutoCommit(false);
	        hikariDataSource.setMaxLifetime(MAX_LIFE_TIME);

	        return hikariDataSource;
    }
	
	
}
