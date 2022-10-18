package com.devsuperior.dscatalog.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.devsuperior.dscatalog.entities.Product;
import com.devsuperior.dscatalog.tests.Factory;

@DataJpaTest
public class ProductRepositoryTests {

	@Autowired
	private ProductRepository repository;

	private long existingId;
	private long nonExistingId;
	private long countTotalProducts;

	@BeforeEach
	void setup() {
		// Assert
		existingId = 1L;
		nonExistingId = 1000L;
		countTotalProducts = 25L;
	};

	@Test
	public void findByIdShouldReturnNoEmptyOptionalWhenIdExists() {
		// Act
		Optional<Product> product = repository.findById(existingId);

		// Assert
		Assertions.assertTrue(product.isPresent());
	}

	@Test
	public void findByIdShouldReturnEmptyOptionalWhenIdDoesNotExists() {
		// Act
		Optional<Product> product = repository.findById(nonExistingId);

		// Assert
		Assertions.assertTrue(product.isEmpty());
	}

	@Test
	public void saveShouldPersistWithAutoincrementWhenIdIsNull() {
		// Assert
		Product product = Factory.createProduct();
		product.setId(null);

		// Act
		product = repository.save(product);

		// Assert
		Assertions.assertNotNull(product.getId());
		Assertions.assertEquals(countTotalProducts + 1, product.getId());
	}

	@Test
	public void deleteShouldDeleteObjectWhenIdExists() {
		// Act
		repository.deleteById(existingId);
		Optional<Product> result = repository.findById(existingId);

		// Assert
		/* O isPresent() do Optional verificar se existe algum objeto nele! */
		Assertions.assertFalse(result.isPresent());
	}

	@Test
	public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExists() {
		// Assert
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			repository.deleteById(nonExistingId); // Act
		});
	}
}
