package com.coderhouse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.models.Invoice;
import com.coderhouse.models.Invoice_Detail;
import com.coderhouse.models.Product;
import com.coderhouse.repositories.Invoice_DetailRepository;
import com.coderhouse.repositories.InvoiceRepository;
import com.coderhouse.repositories.ProductRepository;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class Invoice_DetailService {

    @Autowired
    private Invoice_DetailRepository invoice_DetailRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ProductRepository productRepository;


    public List<Invoice_Detail> getAllInvoiceDetails() {
        return invoice_DetailRepository.findAll();  
    }

    public Invoice_Detail getInvoiceDetailById(Long id) {
        Optional<Invoice_Detail> invoiceDetail = invoice_DetailRepository.findById(id);
        return invoiceDetail.orElseThrow(() -> new IllegalArgumentException("Detalle de factura no encontrado."));  // Si no encuentra, lanza excepción
    }

    @Transactional
    public Invoice_Detail createInvoiceDetail(Long invoiceId, Long productId, int amount) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new IllegalArgumentException("Factura no encontrada."));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado."));

        Invoice_Detail invoiceDetail = new Invoice_Detail();
        invoiceDetail.setInvoice(invoice);
        invoiceDetail.setProduct(product);
        invoiceDetail.setAmount(amount);
        invoiceDetail.setPrice(product.getPrice());

        return invoice_DetailRepository.save(invoiceDetail);
    }

    @Transactional
    public Invoice_Detail updateInvoiceDetail(Long id, int amount) {
        Invoice_Detail invoiceDetail = invoice_DetailRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Detalle de factura no encontrado."));

        invoiceDetail.setAmount(amount);
        return invoice_DetailRepository.save(invoiceDetail);
    }

    @Transactional
    public void deleteInvoiceDetail(Long id) {
        if (!invoice_DetailRepository.existsById(id)) {
            throw new IllegalArgumentException("Detalle de factura no encontrado.");
        }
        invoice_DetailRepository.deleteById(id);
    }
}

