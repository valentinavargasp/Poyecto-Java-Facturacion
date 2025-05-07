package com.coderhouse.dtos;

public class InvoiceDetailDTO {
    private Long id;         
    private int amount;      
    private double price;    

    // Constructor vacío
    public InvoiceDetailDTO() {}

    // Constructor con parámetros
    public InvoiceDetailDTO(Long id, int amount, double price) {
        this.id = id;
        this.amount = amount;
        this.price = price;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}
