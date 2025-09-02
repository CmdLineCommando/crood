package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	// create
	@Transactional
	public Product createProduct(Product product) {

		return productRepository.save(product);

	}

	// read
	@Transactional(readOnly = true)
	public List<Product> getAllProducts() {

		return productRepository.findAll();

	}
	
	@Transactional
	public Product getProductById(Long id) {

		return productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found with the id"));
	}

	// update
	@Transactional
	public Product updateProduct(Long id, Product product) {

		Product existingProduct = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Can't find product"));

		existingProduct.setName(product.getName());
		existingProduct.setDescription(product.getDescription());
		existingProduct.setPrice(product.getPrice());
		return productRepository.save(existingProduct);

	}

	// delete
	@Transactional
	public void deleteProduct(Long id) {

		if(!productRepository.existsById(id)) {
			
			throw new ResourceNotFoundException("not found");
			
		}
		
		productRepository.deleteById(id);

	}

}
