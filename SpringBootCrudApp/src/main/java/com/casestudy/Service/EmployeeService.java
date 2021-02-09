package com.casestudy.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.casestudy.exception.EmployeeNotFoundException;
import com.casestudy.model.Employee;
import com.casestudy.repository.EmployeeRepository;;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository repository;

	public List<Employee> employeeList() {

		List<Employee> employee = new ArrayList<>();
		repository.findAll().forEach(employee::add);
		return employee;
	}

	public Employee addEmp(Employee employee) {

		return repository.save(employee);
	}

	public Employee getEmpById(Long empId) {
		Optional<Employee> employee = repository.findById(empId);

		if (employee.isPresent()) {

			return employee.get();
		}

		else {
			throw new EmployeeNotFoundException("Employee Not Found:" + empId);
			// TODO Auto-generated method stub

		}
	}

	public Employee deleteById(Long empId) {
		Employee employee = repository.findById(empId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not exist with id" + empId));

		repository.delete(employee);

		return employee;

		

	}
}
