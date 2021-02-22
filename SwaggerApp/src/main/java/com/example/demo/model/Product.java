package com.example.demo.model;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;

@Entity
@Table
public class Product {

	
	@Id
	@Column
	int MRN;

	@Column
	@Lob
	List Preference;
	
	

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Product(int mRN, List preference) {
		super();
		MRN = mRN;
		Preference = preference;
	}



	


	public int getMRN() {
		return MRN;
	}



	public void setMRN(int mRN) {
		MRN = mRN;
	}



	public List getPreference() {
		return Preference;
	}



	public void setPreference(List preference) {
		Preference = preference;
	}



	@Override
	public String toString() {
		return "Product [MRN=" + MRN + ", Preference=" + Preference + "]";
	}

	
	

}
