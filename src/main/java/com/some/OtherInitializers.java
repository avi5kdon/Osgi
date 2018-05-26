package com.some;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpHeaders;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.context.annotation.RequestScope;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	@Bean
	@RequestScope
	public ResponseBean responseBean(){
		return new ResponseBean();
	}
	
	@Bean
	public ObjectMapper objectMapper(){
		ObjectMapper objectMapper = new ObjectMapper();
		 objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		 return objectMapper;
		 
	}
}
