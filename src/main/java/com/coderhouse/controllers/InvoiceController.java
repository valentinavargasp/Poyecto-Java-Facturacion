package com.coderhouse.controllers;

import com.coderhouse.models.Invoice;
import com.coderhouse.models.Invoice_Detail;
import com.coderhouse.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        try {
            List<Invoice> invoices = invoiceService.getAllInvoices();
            return ResponseEntity.ok(invoices);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long id) {
        try {
            Invoice invoice = invoiceService.getInvoiceById(id);
            return ResponseEntity.ok(invoice);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build(); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@RequestParam Long clientId, @RequestBody List<Invoice_Detail> invoiceDetails) {
        try {
            Invoice createdInvoice = invoiceService.createInvoice(clientId, invoiceDetails);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdInvoice);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        try {
            invoiceService.deleteInvoice(id);
            return ResponseEntity.noContent().build(); 
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build(); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

