package com.cg.eauction.service;

import com.cg.eauction.dto.ProductDto;
import com.cg.eauction.dto.ProductDtoAdd;
import com.cg.eauction.dto.ProductDtoUpdate;

import java.util.List;

public interface ProductService {
	int addProduct(ProductDtoAdd productDtoAdd);
	List<ProductDto> viewAllProducts();
	List<ProductDto> viewAllProductsByVendorId(int vendorId);
	List<ProductDto> viewAllProductsByType(String productType);
	ProductDto getProductById(int productid);
	void updateProduct(ProductDtoUpdate productDtoUpdate);
	void deleteProduct(int productId);
	
}
