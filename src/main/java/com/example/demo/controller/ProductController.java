package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	
	@Autowired	
	private ProductService productService;

	// create a new product
	@PostMapping("/create")
	public ResponseEntity<Product> createProduct(@RequestBody @Valid Product product) {
		
		logger.info("creating product");
		Product newProduct = productService.createProduct(product);
		
		logger.info("successfully created the product");
		return new ResponseEntity<>(newProduct, HttpStatus.CREATED);

	}

	// Read all products
	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts() {
		
		logger.info("getting all products");
		List<Product> products = productService.getAllProducts();
		
		logger.info("successfully retrieved all products");
		return new ResponseEntity<>(products, HttpStatus.OK);

	}

	// Read product by ID
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {

		Product product = productService.getProductById(id);

		return new ResponseEntity<Product>(product,HttpStatus.OK);
	}

	// Update a product
	@PutMapping("/update/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @Valid @RequestBody Product product) {

		Product updatedProduct = productService.updateProduct(id, product);
		
		if(updatedProduct != null) {
			
			
			return new ResponseEntity<Product> (updatedProduct,HttpStatus.OK);
			
		}
		
		else {
			
			return new ResponseEntity<> (null,HttpStatus.NOT_FOUND);
			
		}
	
	}

	// Delete a product
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {

	productService.deleteProduct(id);
	
	return new ResponseEntity<>(HttpStatus.OK);

	}

}
