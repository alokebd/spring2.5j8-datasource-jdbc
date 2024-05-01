package com.tech.vision.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.vision.api.model.Employee;
import com.tech.vision.api.service.EmployeeService;

@RestController
@RequestMapping(path = "/api/v1")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	
	@PostMapping("/employees")
	public ResponseEntity<String> save(@RequestBody Employee employee) {
		return this.employeeService.save(employee);

	}
	
	@GetMapping("/employees")
	public ResponseEntity<List <Employee>> fillAll(){
		return this.employeeService.findAllEmployees();
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> findById(@PathVariable long id){
		return this.employeeService.findById(id);
	}
	
	@PutMapping("/employees")
	public ResponseEntity<String> updateEmploee(@RequestBody Employee employee){
		return this.employeeService.update(employee);
	}
	
	@DeleteMapping ("/employees/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable long id){
		return this.employeeService.deleteById(id);
	}

}
