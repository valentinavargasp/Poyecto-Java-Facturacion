package com.coderhouse.controllers;

import com.coderhouse.dtos.ProductDTO;
import com.coderhouse.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Obtiene todos los productos.
     */
    @GetMapping
    @Operation(summary = "Obtiene todos los productos", description = "Retorna una lista de todos los productos.")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Obtiene un producto por su ID.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un producto por ID", description = "Retorna los detalles de un producto por su ID.")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        ProductDTO productDTO = productService.getProductById(id);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    /**
     * Crea un nuevo producto.
     */
    @PostMapping
    @Operation(summary = "Crea un nuevo producto", description = "Crea un nuevo producto a partir de los datos proporcionados.")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO createdProduct = productService.createProduct(productDTO);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    /**
     * Actualiza un producto existente.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un producto", description = "Actualiza los detalles de un producto existente.")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        ProductDTO updatedProduct = productService.updateProduct(id, productDTO);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    /**
     * Elimina un producto por ID.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un producto", description = "Elimina un producto existente por su ID.")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

