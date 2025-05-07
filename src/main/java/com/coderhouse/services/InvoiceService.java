package com.coderhouse.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.dtos.InvoiceDTO;
import com.coderhouse.dtos.InvoiceDetailDTO;
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

    // Obtener todas las facturas y mapearlas a DTOs
    public List<InvoiceDTO> getAllInvoices() {
        return invoiceRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Obtener una factura por ID y mapearla a un DTO
    public InvoiceDTO getInvoiceById(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Factura no encontrada."));
        return mapToDTO(invoice);
    }

    // Crear una nueva factura a partir de DTOs
    @Transactional
    public InvoiceDTO createInvoice(Long clientId, List<InvoiceDetailDTO> invoiceDetailDTOs) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado."));

        Invoice invoice = new Invoice();
        invoice.setClient(client);

        // Convertir los InvoiceDetailDTOs a modelos
        List<Invoice_Detail> invoiceDetails = invoiceDetailDTOs.stream()
                .map(dto -> {
                    Invoice_Detail detail = new Invoice_Detail();
                    detail.setAmount(dto.getAmount());
                    detail.setPrice(dto.getPrice());
                    return detail;
                })
                .collect(Collectors.toList());

        // Calcular el total de la factura
        double total = invoiceDetails.stream()
                .mapToDouble(detail -> detail.getAmount() * detail.getPrice())
                .sum();
        invoice.setTotal(total);

        // Asociar los detalles de la factura con la factura
        invoiceDetails.forEach(detail -> detail.setInvoice(invoice));
        invoice.setInvoiceDetails(invoiceDetails);

        // Guardar la factura y mapearla a un DTO
        return mapToDTO(invoiceRepository.save(invoice));
    }

    // Eliminar una factura por ID
    public void deleteInvoice(Long id) {
        if (!invoiceRepository.existsById(id)) {
            throw new IllegalArgumentException("Factura no encontrada.");
        }
        invoiceRepository.deleteById(id);
    }

    // Métodos auxiliares de mapeo

    // Mapear modelo Invoice a DTO
    private InvoiceDTO mapToDTO(Invoice invoice) {
        List<InvoiceDetailDTO> detailDTOs = invoice.getInvoiceDetails()
                .stream()
                .map(detail -> new InvoiceDetailDTO(detail.getId(), detail.getAmount(), detail.getPrice()))
                .collect(Collectors.toList());

        return new InvoiceDTO(invoice.getId(), invoice.getClient().getId(), invoice.getTotal(), detailDTOs);
    }
}

