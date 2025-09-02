package com.example.demo.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	@NotBlank(message = "Product name can't be blank")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "price")
	@Min(value = 0, message = "price can't be negative")
	private BigDecimal price;
	
	//Getters and setters
	public Long getId() {
		return this.id;
		
	}
	
	public String getName() {
		return this.name;
	}
	
	
	public String getDescription() {
		return description;
	}


	public BigDecimal getPrice() {
		return price;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		
		this.name = name;
	}	
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	
}
