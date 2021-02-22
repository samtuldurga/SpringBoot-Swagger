package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Service
public class ProductService {

	@Autowired
	ProductRepository repository;

	public Product product(int MRN) throws JsonMappingException, JsonProcessingException {
		Product product = repository.findById(MRN);
		return product;

	}
}
