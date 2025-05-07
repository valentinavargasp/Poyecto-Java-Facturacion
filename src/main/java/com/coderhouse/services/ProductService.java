package com.coderhouse.services;

import com.coderhouse.dtos.ProductDTO;
import com.coderhouse.models.Product;
import com.coderhouse.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Obtiene todos los productos como DTOs.
     */
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene un producto por ID como DTO.
     */
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado."));
        return mapToDTO(product);
    }

    /**
     * Crea un nuevo producto desde un DTO.
     */
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = mapToEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return mapToDTO(savedProduct);
    }

    /**
     * Actualiza un producto existente desde un DTO.
     */
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado."));

        existingProduct.setCode(productDTO.getCode()); // Cambié 'name' por 'code'
        existingProduct.setDescription(productDTO.getDescription()); // 'description' es más relevante en la entidad
        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setStock(productDTO.getStock());
        existingProduct.setImage(productDTO.getImage());
        existingProduct.setCategory(productDTO.getCategory());

        Product updatedProduct = productRepository.save(existingProduct);
        return mapToDTO(updatedProduct);
    }

    /**
     * Elimina un producto por ID.
     */
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Producto no encontrado.");
        }
        productRepository.deleteById(id);
    }

    /**
     * Mapea una entidad Product a un ProductDTO.
     */
    private ProductDTO mapToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setCode(product.getCode()); // 'code' en vez de 'name'
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setStock(product.getStock());
        productDTO.setImage(product.getImage());
        productDTO.setCategory(product.getCategory());
        return productDTO;
    }

    /**
     * Mapea un ProductDTO a una entidad Product.
     */
    private Product mapToEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setCode(productDTO.getCode()); 
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        product.setImage(productDTO.getImage());
        product.setCategory(productDTO.getCategory());
        return product;
    }
}

