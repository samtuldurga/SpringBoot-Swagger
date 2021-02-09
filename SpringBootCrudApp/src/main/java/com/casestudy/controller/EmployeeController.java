package com.casestudy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.Service.EmployeeService;
import com.casestudy.exception.EmployeeNotFoundException;
import com.casestudy.model.Employee;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

	@Autowired
	EmployeeService service;

	@GetMapping(path = "/employees", produces = "application/json")
	public List<Employee> getAllEmployee() {

		return service.employeeList();

	}

	@PostMapping(path = "/addEmployee", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Employee> addEmpDetails(@Validated@RequestBody Employee employee) {

		employee = service.addEmp(employee);

		return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);

	}

	@GetMapping("/employees/{empid}")
	public ResponseEntity <Employee> getEmployee(@PathVariable("empid") Long empId) {
		
        return ResponseEntity.ok().body(service.getEmpById(empId));
		
		
		
	}

	@DeleteMapping("/employees/{empid}")
	public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable("empid") Long empId){
		
		service.deleteById(empId);
		Map<String,Boolean> response=new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);

	}
}
