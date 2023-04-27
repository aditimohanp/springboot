package com.crud.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crud.springboot.entity.Employee;
import com.crud.springboot.service.EmployeeService;



@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	@PostMapping("/addEmployee")
	public Employee addEmployee(@RequestBody Employee employee) {                                                
		return employeeService.createEmployee(employee);
	}

	@PostMapping("/addEmployees")
	public List<Employee> addEmployee(@RequestBody List<Employee> employee) {
		return employeeService.createEmployee(employee);
	}

	@GetMapping("/Employee/{id}")
	public Employee getEmployeeById(@PathVariable int empId) {
		return employeeService.getEmployeeById(empId);

	}

	@GetMapping("Employee/{pageNo}/{recordCount}")
	public List<Employee> getAllEmployee(@PathVariable int pageNo,@PathVariable int recordCount) {
		return employeeService.getEmployee( pageNo,recordCount);
	}

	@PutMapping("/updateEmployee")
	public Employee updateEmployee(@RequestBody Employee employee) {
		return employeeService.updateEmployee(employee);

	}

	@DeleteMapping("/Employee/{empId}")
	public String deleteEmployeeById(@PathVariable int empId) {
		return employeeService.deleteEmployeeById(empId);
	}


}
