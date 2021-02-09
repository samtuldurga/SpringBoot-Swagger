package com.example.demo.model;
import javax.persistence.Column;
import javax.persistence.Table;

@Table
public class Product {

	@Column
	String MRN;

	@Column
	String Preference;

	public String getMRN() {
		return MRN;
	}

	public void setMRN(String mRN) {
		MRN = mRN;
	}

	public String getPreference() {
		return Preference;
	}

	public void setPreference(String preference) {
		Preference = preference;
	}

	@Override
	public String toString() {
		return "Product [MRN=" + MRN + ", Preference=" + Preference + "]";
	}

}
