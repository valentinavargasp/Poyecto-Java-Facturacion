package com.coderhouse.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Products")
public class Products {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "product_description", nullable = false)
	private String description;

	@Column(name = "product_code", unique = true, nullable = false)
	private String code;

	@Column(name = "product_stock", nullable = false)
	private int stock;

	@Column(name = "product_price", nullable = false)
	private double price;

	// Relación uno a muchos con InvoiceDetails
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Invoice_Details> invoiceDetails;

	// Constructor
	public Products() {
	}

	// Constructor
	public Products(String description, String code, int stock, double price) {
		this.description = description;
		this.code = code;
		this.stock = stock;
		this.price = price;
	}

	// Getters & Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List<Invoice_Details> getInvoiceDetails() {
		return invoiceDetails;
	}

	public void setInvoiceDetails(List<Invoice_Details> invoiceDetails) {
		this.invoiceDetails = invoiceDetails;
	}

	@Override
	public String toString() {
		return "Products [id=" + id + ", description=" + description + ", code=" + code + ", stock=" + stock
				+ ", price=" + price + ", invoiceDetails=" + invoiceDetails + "]";
	}

}
