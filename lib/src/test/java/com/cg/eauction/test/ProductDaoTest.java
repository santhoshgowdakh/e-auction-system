package com.cg.eauction.test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.cg.eauction.dto.ProductDtoAdd;
import com.cg.eauction.entities.Product;
import com.cg.eauction.repository.ProductRepository;
import com.cg.eauction.service.ProductService;

@SpringBootTest
class ProductDaoTest {
	
	@Autowired
	ProductRepository productRepo;
	@Autowired
	ProductService productService;

	@Test
	void addProduct() {
		ProductDtoAdd productDtoAdd = new ProductDtoAdd();
		productDtoAdd.setProductName("Mobile");
		productDtoAdd.setDescription("It's xiaomi brand");
		productDtoAdd.setMinPrice(8000);
		productDtoAdd.setProductType("Electronics");
		productDtoAdd.setStatus("Active");
		productDtoAdd.setEndDate(new Date());
		productDtoAdd.setStartDate(new Date());
		productDtoAdd.setVendorId(2);
		int productId = productService.addProduct(productDtoAdd);
		assertEquals("Mobile", productRepo.getProductByProductId(productId).getProductName());
		assertEquals("It's xiaomi brand", productRepo.getProductByProductId(productId).getDescription());
		assertEquals(8000, productRepo.getProductByProductId(productId).getMinPrice());
		assertEquals("Electronics", productRepo.getProductByProductId(productId).getProductType());
		assertEquals("Active", productRepo.getProductByProductId(productId).getStatus());
	}

	@Test
	void testViewAllProducts() {
		List<Product> productList = productRepo.findAll();
		assertNotNull(productList);

	}

	@Test
	void testViewAllProductsByType() {
		List<Product> productList = productRepo.getProductsByProductType("Fashion");
		assertNotNull(productList);
	}

	@Test
	void testViewAllProductsByVendorId() {
		List<Product> productList = productRepo.getProductsByVendorId(4);
		assertNotNull(productList);
	}

	@Test
	void testGetProductByProductId() {
		Product product = productRepo.getProductByProductId(4);
		assertEquals(4,product.getProductId());
	}

	@Test
	void testDeleteProduct() {
		productRepo.deleteById(3);
		Product product = productRepo.getProductByProductId(3);
		assertNull(product);
	}

	@Test
	void testUpdateProduct() {
		Product product = productRepo.getProductByProductId(9);
		assertEquals(9, product.getVendor().getUserId());

	}
}
