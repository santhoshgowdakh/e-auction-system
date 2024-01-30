package com.cg.eauction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.eauction.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	@Query(value = "select product from Product product where product.productId=?1")
	Product getProductByProductId(int productid);
	
	@Query(value = "select product from Product product where product.productType=?1")
	List<Product> getProductsByProductType(String productType);
	
	@Query(value = "select product from Product product where product.vendor.userId=?1")
	List<Product> getProductsByVendorId(int vendorid);
}
