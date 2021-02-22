package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class ProductRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	ObjectMapper mapper;

	@SuppressWarnings("deprecation")
	public Product findById(int id) throws JsonMappingException, JsonProcessingException {

		String sql = "SELECT PREFERENCE FROM PRODUCT WHERE MRN=?";
		try {
			String s = jdbcTemplate.queryForObject(sql, new Object[] { id }, String.class);
			Product product = null;
			product = new ObjectMapper().readValue(s, Product.class);
			return product;
			}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("MRN NOT EXIST");
		}
	}

	
	
	
	
	
	/*
	 * public Product getItem(int mRN) { String query =
	 * "SELECT * FROM PRODUCT WHERE MRN=?";
	 * 
	 * @SuppressWarnings("deprecation") Product item =
	 * jdbcTemplate.queryForObject(query, new Object[] { mRN }, new
	 * BeanPropertyRowMapper<>(Product.class));
	 * 
	 * return item; }
	 *
	 */
}
