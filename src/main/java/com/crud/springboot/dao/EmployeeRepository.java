package com.crud.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.springboot.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
