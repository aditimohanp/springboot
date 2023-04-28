package com.crud.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.crud.springboot.dao.EmployeeRepository;
import com.crud.springboot.entity.Employee;

@Service
public class EmployeeService {
	
		@Autowired	
		private EmployeeRepository employeeRepository;
		public Employee createEmployee(Employee employee) {
			return employeeRepository.save(employee);
		}
		public List<Employee> createEmployee(List<Employee> employee) {
			return employeeRepository.saveAll(employee);
		}
		public Employee getEmployeeById(int empId) {
			return employeeRepository.findById(empId).orElse(null);
		}
		public List<Employee> getEmployee(int pageNo, int recordCount) { // pagination and sorting demonstration
			Pageable pageable = PageRequest.of(pageNo, recordCount,Sort.by("empName"));
			return employeeRepository.findAll(pageable).toList();
		}
		public Employee updateEmployee(Employee employee) {
			Employee oldEmployee = null;
			Optional<Employee> optionalEmployee = employeeRepository.findById(employee.getEmpId());
			if(optionalEmployee.isPresent()) {
			    oldEmployee = optionalEmployee.get();
				oldEmployee.setEmpName(employee.getEmpName());
				employeeRepository.save(oldEmployee);
				}else {
					return new Employee();
				}
			return oldEmployee;
		}
		public String deleteEmployeeById(int empId) {
			employeeRepository.deleteById(empId);
			return "Employee has been deleted";
		}

}
