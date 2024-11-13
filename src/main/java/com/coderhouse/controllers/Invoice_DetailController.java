package com.coderhouse.controllers;

import com.coderhouse.models.Invoice_Detail;
import com.coderhouse.services.Invoice_DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoice-details")
public class Invoice_DetailController {

    @Autowired
    private Invoice_DetailService invoiceDetailService;


    @GetMapping
    public ResponseEntity<List<Invoice_Detail>> getAllInvoiceDetails() {
        try {
            List<Invoice_Detail> invoiceDetails = invoiceDetailService.getAllInvoiceDetails();
            return ResponseEntity.ok(invoiceDetails);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Invoice_Detail> getInvoiceDetailById(@PathVariable Long id) {
        try {
            Invoice_Detail invoiceDetail = invoiceDetailService.getInvoiceDetailById(id);
            return ResponseEntity.ok(invoiceDetail);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build(); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    
    @PostMapping
    public ResponseEntity<Invoice_Detail> createInvoiceDetail(@RequestBody Invoice_Detail invoiceDetail) {
        try {
            Invoice_Detail createdInvoiceDetail = invoiceDetailService.createInvoiceDetail(invoiceDetail.getInvoice().getId(),
                    invoiceDetail.getProduct().getId(), invoiceDetail.getAmount());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdInvoiceDetail);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Invoice_Detail> updateInvoiceDetail(@PathVariable Long id, @RequestBody Invoice_Detail invoiceDetailDetails) {
        try {
            Invoice_Detail updatedInvoiceDetail = invoiceDetailService.updateInvoiceDetail(id, invoiceDetailDetails.getAmount());
            return ResponseEntity.ok(updatedInvoiceDetail);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build(); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoiceDetail(@PathVariable Long id) {
        try {
            invoiceDetailService.deleteInvoiceDetail(id);
            return ResponseEntity.noContent().build(); 
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build(); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

