package com.coderhouse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.dtos.InvoiceDetailDTO;
import com.coderhouse.models.Invoice;
import com.coderhouse.models.Invoice_Detail;
import com.coderhouse.models.Product;
import com.coderhouse.repositories.Invoice_DetailRepository;
import com.coderhouse.repositories.InvoiceRepository;
import com.coderhouse.repositories.ProductRepository;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Invoice_DetailService {

    @Autowired
    private Invoice_DetailRepository invoice_DetailRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ProductRepository productRepository;

    // Obtener todos los detalles de factura y convertir a DTOs
    public List<InvoiceDetailDTO> getAllInvoiceDetails() {
        return invoice_DetailRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Obtener un detalle de factura por ID y convertir a DTO
    public InvoiceDetailDTO getInvoiceDetailById(Long id) {
        Invoice_Detail invoiceDetail = invoice_DetailRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Detalle de factura no encontrado."));
        return mapToDTO(invoiceDetail);
    }

    // Crear un nuevo detalle de factura
    @Transactional
    public InvoiceDetailDTO createInvoiceDetail(Long invoiceId, Long productId, int amount) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new IllegalArgumentException("Factura no encontrada."));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado."));

        Invoice_Detail invoiceDetail = new Invoice_Detail();
        invoiceDetail.setInvoice(invoice);
        invoiceDetail.setProduct(product);
        invoiceDetail.setAmount(amount);
        invoiceDetail.setPrice(product.getPrice());

        Invoice_Detail savedDetail = invoice_DetailRepository.save(invoiceDetail);
        return mapToDTO(savedDetail);
    }

    // Actualizar un detalle de factura
    @Transactional
    public InvoiceDetailDTO updateInvoiceDetail(Long id, int amount) {
        Invoice_Detail invoiceDetail = invoice_DetailRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Detalle de factura no encontrado."));

        invoiceDetail.setAmount(amount);

        Invoice_Detail updatedDetail = invoice_DetailRepository.save(invoiceDetail);
        return mapToDTO(updatedDetail);
    }

    // Eliminar un detalle de factura
    @Transactional
    public void deleteInvoiceDetail(Long id) {
        if (!invoice_DetailRepository.existsById(id)) {
            throw new IllegalArgumentException("Detalle de factura no encontrado.");
        }
        invoice_DetailRepository.deleteById(id);
    }

    // Mapear de entidad a DTO
    private InvoiceDetailDTO mapToDTO(Invoice_Detail detail) {
        return new InvoiceDetailDTO(
                detail.getId(),
                detail.getAmount(),
                detail.getPrice()
        );
    }

    // Mapear de DTO a entidad (si es necesario en casos específicos)
    private Invoice_Detail mapToEntity(InvoiceDetailDTO dto) {
        Invoice_Detail detail = new Invoice_Detail();
        detail.setId(dto.getId());
        detail.setAmount(dto.getAmount());
        detail.setPrice(dto.getPrice());
        return detail;
    }
}


