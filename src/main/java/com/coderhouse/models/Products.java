package com.coderhouse.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Products {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "product_description")
	private String description;
	
	@Column(name = "product_code")
	private String code;
	
	@Column(name = "product_stock")
	private int stock;
	
	@Column(name = "product_price")
	private double price;
	
	
}
