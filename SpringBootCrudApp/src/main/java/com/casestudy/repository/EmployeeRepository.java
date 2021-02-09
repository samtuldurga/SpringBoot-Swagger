package com.casestudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	//public List<Employee> findByNameAndId(String s,int id);
	
}