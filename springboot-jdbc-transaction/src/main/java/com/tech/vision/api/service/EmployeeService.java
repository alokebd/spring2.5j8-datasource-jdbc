package com.tech.vision.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tech.vision.api.model.Employee;
import com.tech.vision.api.repository.JdbcTemplateRepository;

@Service
@Transactional
public class EmployeeService {
	
	@Autowired
	private JdbcTemplateRepository jdbcTemplateRepository;
	
	
	public ResponseEntity<String> save(Employee employee) {
		int savedVal = jdbcTemplateRepository.insert(employee);
		if (savedVal>0) {
			return ResponseEntity.ok().body("saved");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Saved");
	}
	
	public ResponseEntity<List <Employee>> findAllEmployees(){
		List <Employee> list= jdbcTemplateRepository.findAll();
		if (list != null && list.size()>0) {
		  return ResponseEntity.ok().body(list);
		}
		List <Employee> empty = new ArrayList<>();
		empty.add(new Employee());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(empty);
	}
	
	 public ResponseEntity<Employee> findById(long id) {
		 Optional <Employee> emp= jdbcTemplateRepository.findById(id);
		
		 if (emp.isPresent()) {
			 return ResponseEntity.ok().body(emp.get());
		 }
		 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Employee());
	 }
	 
	 public ResponseEntity<String> update(Employee employee) {
		 int update= jdbcTemplateRepository.update(employee);
		 if (update==1) {
				return ResponseEntity.ok().body("Update");
		 }
		 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Update");
	 }
	 
	 public ResponseEntity<String>  deleteById(long id) {
		 int delete= this.jdbcTemplateRepository.deleteById(id);
		 if (delete==1) {
			return ResponseEntity.ok().body("Deleted");
		 }
		 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not deleted");
	 }

}
