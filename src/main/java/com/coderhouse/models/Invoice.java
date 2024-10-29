package com.coderhouse.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Invoices")
public class Invoice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "created_at", nullable = false)
	private LocalDateTime created_at = LocalDateTime.now();

	@Column(name = "total", nullable = false)
	private double total;

	// Relación muchos a uno con Clients
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "client_id", nullable = false)
	private Clients client;

	// Relación uno a muchos con InvoiceDetails
	@OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Invoice_Details> invoiceDetails = new ArrayList<>();

	// Constructores

	public Invoice() {
	}

	public Invoice(LocalDateTime created_at, Double total, Clients client) {
		this.created_at = created_at;
		this.total = total;
		this.client = client;
	}

	// Getters & Setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Clients getClient() {
		return client;
	}

	public void setClient(Clients client) {
		this.client = client;
	}

	public List<Invoice_Details> getInvoiceDetails() {
		return invoiceDetails;
	}

	public void setInvoiceDetails(List<Invoice_Details> invoiceDetails) {
		this.invoiceDetails = invoiceDetails;
	}

	@Override
	public String toString() {
		return "Invoice [id=" + id + ", created_at=" + created_at + ", total=" + total + ", client=" + client
				+ ", invoiceDetails=" + invoiceDetails + "]";
	}

}
