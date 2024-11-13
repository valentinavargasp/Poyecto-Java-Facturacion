package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.coderhouse.models.Product;
import com.coderhouse.repositories.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
	
	public Product getCursoById(Long id) {
		return productRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Producto no encontrado."));
	}
	
	@Transactional
	public Product createProduct (@RequestBody Product product) {
		return productRepository.save(product);
	}
	
	@Transactional
	public Product updateProduct(Long id, Product productDetails) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Producto no encontrado."));
		
		product.setCode(productDetails.getCode());
		product.setDescription(productDetails.getDescription());
		product.setPrice(productDetails.getPrice());
		product.setStock(productDetails.getStock());
		return productRepository.save(product);
	}
	
	public void deleteProduct(Long id) {
		if (!productRepository.existsById(id)) {
			throw new IllegalArgumentException("Producto no encontrado.");
		}
		productRepository.deleteById(id);
	}
	
}
