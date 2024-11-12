package com.coderhouse.models;

import java.util.ArrayList;
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
@Table(name = "Clients")
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "client_name")
	private String name;

	@Column(name = "client_lastname")
	private String lastname;

	@Column(name = "client_docnumber", unique = true, nullable = false)
	private String docnumber;

	// Relación uno a muchos con invoice
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Invoice> invoices = new ArrayList<>();

	// Constructores

	public Client() {
	}

	public Client(String name, String lastname, String docnumber) {
		this.name = name;
		this.lastname = lastname;
		this.docnumber = docnumber;
	}

	// Getters & Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getDocnumber() {
		return docnumber;
	}

	public void setDocnumber(String docnumber) {
		this.docnumber = docnumber;
	}

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}

	@Override
	public String toString() {
		return "Clients [id=" + id + ", name=" + name + ", lastname=" + lastname + ", docnumber=" + docnumber
				+ ", invoices=" + invoices + "]";
	}

}