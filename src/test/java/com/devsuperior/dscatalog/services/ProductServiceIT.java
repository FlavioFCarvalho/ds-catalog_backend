package com.devsuperior.dscatalog.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.devsuperior.dscatalog.repositories.ProductRepository;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;

@SpringBootTest
public class ProductServiceIT {
	
	@Autowired
	private ProductService service;
	
	@Autowired
	private ProductRepository repository;
	
	private Long existingID;
	private Long nonExistingId;
	private Long countTotalProducts;

	@BeforeEach
	void setUp() throws Exception {
		
		existingID = 1L;
		nonExistingId = 1000L;
		countTotalProducts = 25L;
	}	
	
	@Test
	public void deleteShouldDeleteResouceWhenIdExists() {
		
		service.delete(existingID);
		
		Assertions.assertEquals(countTotalProducts - 1, repository.count());
	}
	
	@Test
	public void deleteShoulThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.delete(nonExistingId);
		});
	}
}
