package com.casestudy;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.casestudy.Service.EmployeeService;
import com.casestudy.controller.EmployeeController;
import com.casestudy.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerIntegrationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	EmployeeService mockService;

	private static final ObjectMapper om = new ObjectMapper();

	@Test

	public void getAllEmployeesAPI() throws Exception {
		List<Employee> employee = Arrays.asList(new Employee(1L, "Ram", "Kumar", "ram@gmail.com"),
				new Employee(2l, "Raj", "Kumar", "raj@gmail.com"));

		when(mockService.employeeList()).thenReturn(employee);

		mockMvc.perform(get("/api/v1/employees")).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].id").value(1))
				.andExpect(jsonPath("$[0].firstName").value("Ram")).andExpect(jsonPath("$[0].lastName").value("Kumar"))
				.andExpect(jsonPath("$[0].email").value("ram@gmail.com")).andExpect(jsonPath("$[1].id").value(2))
				.andExpect(jsonPath("$[1].firstName").value("Raj")).andExpect(jsonPath("$[1].lastName").value("Kumar"))
				.andExpect(jsonPath("$[1].email").value("raj@gmail.com"));

	}

	@Test
	public void createEmployeeAPI() throws Exception {

		Employee employee = new Employee(1L, "Ram", "Kumar", "ram@gmail.com");

		when(mockService.addEmp(any(Employee.class))).thenReturn(employee);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/addEmployee").content(om.writeValueAsString(employee))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))

				.andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.firstName").value("Ram")).andExpect(jsonPath("$.lastName").value("Kumar"))
				.andExpect(jsonPath("$.email").value("ram@gmail.com"));

	}

	@Test

	public void getEmployeeById() throws Exception {
		Employee employee = new Employee(1L, "Ram", "Kumar", "ram@gmail.com");

		when(mockService.getEmpById(1L)).thenReturn((employee));

		mockMvc.perform(get("/api/v1/employees/1"))
				/* .andDo(print()) */
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1)).andExpect(jsonPath("$.firstName").value("Ram"))
				.andExpect(jsonPath("$.lastName").value("Kumar")).andExpect(jsonPath("$.email").value("ram@gmail.com"));
	}

	
	  @Test public void deleteEmployee() throws Exception{ Employee employee=new
	  Employee( 1L,"Ram", "Kumar","ram@gmail.com");
	  
	  when(mockService.getEmpById(employee.getId())).thenReturn(employee);
	  //doNothing().when(mockService).deleteById(employee.getId());
	  
	  mockMvc.perform(delete("/api/v1/employees/1")) .andDo(print())
	  .andExpect(status().isOk()); }
	 

}