package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;


@RestController
@RequestMapping("/test/" )
public class ProductController {

	@Autowired
	ProductService service;

	@GetMapping("/getData/{MRN}")
	public ResponseEntity<Product> getItem(@PathVariable("MRN") int MRN) throws JsonMappingException, JsonProcessingException   {
		    return ResponseEntity.ok().body(service.product(MRN));	


	}
}






