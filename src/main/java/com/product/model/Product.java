package com.product.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private String imageUrl;
	
	private Float originalPrice;
	
	private Float discountedPrice;
	
	private Integer rating;
	
	private String badges;
	
	private String colors;
	
	private String sizes;
	
	private Integer stockStatus;
	
	@ManyToOne
	private Category category;
	
	@ManyToOne
	private Brand brand;
	
	private Boolean isDeleted;
	
}
