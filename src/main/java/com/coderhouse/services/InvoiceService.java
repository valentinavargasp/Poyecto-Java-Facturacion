package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.models.Client;
import com.coderhouse.models.Invoice;
import com.coderhouse.models.Invoice_Detail;
import com.coderhouse.repositories.ClientRepository;
import com.coderhouse.repositories.InvoiceRepository;

import jakarta.transaction.Transactional;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ClientRepository clientRepository;


    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Invoice getInvoiceById(Long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Factura no encontrada."));
    }

    @Transactional
    public Invoice createInvoice(Long clientId, List<Invoice_Detail> invoiceDetails) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado."));

        Invoice invoice = new Invoice();
        invoice.setClient(client);

        // Calcular el total de la factura sumando los subtotales de cada detalle de factura
        double total = invoiceDetails.stream()
                .mapToDouble(detail -> detail.getAmount() * detail.getPrice())
                .sum();
        invoice.setTotal(total);

        // Asociar los detalles de la factura con la factura
        invoiceDetails.forEach(detail -> detail.setInvoice(invoice));
        invoice.setInvoiceDetails(invoiceDetails);

        // Guardar la factura y sus detalles
        return invoiceRepository.save(invoice);
    }

    public void deleteInvoice(Long id) {
        if (!invoiceRepository.existsById(id)) {
            throw new IllegalArgumentException("Factura no encontrada.");
        }
        invoiceRepository.deleteById(id);
    }
}
