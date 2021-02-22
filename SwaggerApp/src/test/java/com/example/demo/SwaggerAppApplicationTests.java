package com.example.demo;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.controller.ProductController;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class SwaggerAppApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	ProductService mockService;

	
	@Test
	public void getPreferenceByMrn() throws Exception {
		Product product=new Product();
		
		product.setMRN(1);
		
		List<String> preference=new ArrayList();
		
		preference.add("{\"mrn\":1,\"preference\":[{\"id\":1, \"channelCode\":\"Email\"}]}");
		
		product.setPreference(preference);


		when(mockService.product(1)).thenReturn(product);

		mockMvc.perform(get("/test/getData/1"))
				/* .andDo(print()) */
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.mrn").value(1)).andExpect(jsonPath("$.preference").value("{\"mrn\":123,\"preference\":[{\"id\":123, \"channelCode\":\"Email\"}]}"));
	}

}
