package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Product;

@Repository
public class ProductRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Product getItem(String MRN) {
		String query = "SELECT * FROM PRODUCT WHERE MRN=?";
		@SuppressWarnings("deprecation")
		Product item = jdbcTemplate.queryForObject(query, new Object[] { MRN },
				new BeanPropertyRowMapper<>(Product.class));

		return item;
	}

}
