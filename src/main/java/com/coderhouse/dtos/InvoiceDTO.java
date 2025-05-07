package com.coderhouse.dtos;

import java.util.List;

public class InvoiceDTO {
    private Long id;
    private Long clientId;
    private double total;
    private List<InvoiceDetailDTO> invoiceDetails;

    // Constructor vacío (necesario para frameworks)
    public InvoiceDTO() {
    }

    // Constructor completo
    public InvoiceDTO(Long id, Long clientId, double total, List<InvoiceDetailDTO> invoiceDetails) {
        this.id = id;
        this.clientId = clientId;
        this.total = total;
        this.invoiceDetails = invoiceDetails;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<InvoiceDetailDTO> getInvoiceDetails() {
        return invoiceDetails;
    }

    public void setInvoiceDetails(List<InvoiceDetailDTO> invoiceDetails) {
        this.invoiceDetails = invoiceDetails;
    }
}
