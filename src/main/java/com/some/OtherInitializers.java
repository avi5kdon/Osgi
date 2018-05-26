package com.some;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class OtherInitializers {

	
	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("org.postgresql.Driver");
		ds.setUrl("jdbc:postgresql://172.30.80.110:5432/sampledb");
		ds.setUsername("root");
		ds.setPassword("root");
		return ds;
		
		
	}
	
	@Bean
	public HttpHeaders httpHeaders(){
		return new HttpHeaders();
	}
}
