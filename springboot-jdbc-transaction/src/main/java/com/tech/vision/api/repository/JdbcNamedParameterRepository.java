package com.tech.vision.api.repository;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcNamedParameterRepository {
	
	protected final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	//protected final JdbcTemplate jdbcTemplate;
	protected DataSource dataSource;
	
	// note: @Qualifier("dataSource") is not required as there is dataSource Bean configured.
	//For multiple dataSource, there other bean can be used with @Qualifier 
	public JdbcNamedParameterRepository(DataSource dataSource) {
		this.dataSource =  dataSource;
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);	     
	}

	//TODO

}
