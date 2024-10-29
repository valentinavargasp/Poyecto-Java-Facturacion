package com.coderhouse.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Invoices Details")
public class Invoice_Details {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "invoice_detail_id", nullable = false)
	private int id;

	@Column(name = "amount", nullable = false)
	private int amount;

	@Column(name = "price", nullable = false)
	private double price;

	// Relación muchos a uno con Invoice
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "invoice_id", nullable = false)
	private Invoice invoice;

	// Relación muchos a uno con Product
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id", nullable = false)
	private Products product;

	// Constructores

	public Invoice_Details() {
	}

	public Invoice_Details(int amount, double price, Invoice invoice, Products product) {
		this.amount = amount;
		this.price = price;
		this.invoice = invoice;
		this.product = product;
	}

	// Getters & Setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Invoice_Details [id=" + id + ", amount=" + amount + ", price=" + price + ", invoice=" + invoice
				+ ", product=" + product + "]";
	}

}
