package com.tech.vision.api.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tech.vision.api.model.Employee;

@Repository
public class JdbcTemplateRepository {
	protected final JdbcTemplate jdbcTemplate;
	protected DataSource dataSource;
	
	public JdbcTemplateRepository(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	class EmployeeRowMapper implements RowMapper <Employee> {
	        @Override
	        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
	            Employee employee = new Employee();
	            employee.setId(rs.getLong("id"));
	            employee.setFirstName(rs.getString("first_name"));
	            employee.setLastName(rs.getString("last_name"));
	            employee.setEmailId(rs.getString("email_address"));
	            return employee;
	        }
	 }
	
	
	 public int insert(Employee employee) {
	        return jdbcTemplate.update("insert into employee (id, first_name, last_name, email_address) " + "values(?, ?, ?, ?)",
	            new Object[] {
	                employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmailId()
	            });
	 }

	 public List <Employee> findAll() {
	        return jdbcTemplate.query("select * from employee", new EmployeeRowMapper());
	 }
	
	public Optional <Employee> findById(long id) {
		 String sql = "SELECT * FROM EMPLOYEE WHERE ID = ?";
		 Object[] args = {id};
	 
		 Employee employee= jdbcTemplate.queryForObject(sql, new EmployeeRowMapper(), args);
		 return Optional.of(employee);
	 }

	 public int update(Employee employee) {
	        return jdbcTemplate.update("update employee " + " set first_name = ?, last_name = ?, email_address = ? " + " where id = ?",
	            new Object[] {
	                employee.getFirstName(), employee.getLastName(), employee.getEmailId(), employee.getId()
	            });
	  }
	 
	 public int deleteById(long id) {
	        return jdbcTemplate.update("delete from employee where id=?", new Object[] {
	            id
	        });
	 }
}
