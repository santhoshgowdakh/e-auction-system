package com.cg.eauction.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.eauction.dto.ProductDto;
import com.cg.eauction.dto.ProductDtoAdd;
import com.cg.eauction.dto.ProductDtoUpdate;
import com.cg.eauction.entities.Product;
import com.cg.eauction.entities.User;
import com.cg.eauction.exceptions.ProductNotFoundException;
import com.cg.eauction.exceptions.UserNotFoundException;
import com.cg.eauction.repository.ProductRepository;
import com.cg.eauction.repository.UserRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepo;
	@Autowired
	UserRepository userRepo;


	@Override
	public int addProduct(ProductDtoAdd productDtoAdd) {
		User vendor=userRepo.getUserByUserId(productDtoAdd.getVendorId());
		if(vendor==null)
			throw new UserNotFoundException("Enter valid vendor id..");
		Product product=new Product();
		product.setProductName(productDtoAdd.getProductName());
		product.setDescription(productDtoAdd.getDescription());
		product.setMinPrice(productDtoAdd.getMinPrice());
		product.setProductType(productDtoAdd.getProductType());
		product.setVendor(vendor);
		product.setHighestBid(productDtoAdd.getMinPrice());
		product.setStatus(productDtoAdd.getStatus());
		product.setStartDate(new Date());
		product.setEndDate(productDtoAdd.getEndDate());
		productRepo.save(product);
		return product.getProductId();
	}

	@Override
	public void updateProduct(ProductDtoUpdate productDtoUpdate) {
		Product product=productRepo.getProductByProductId(productDtoUpdate.getProductId());
		if(product==null)
			throw new ProductNotFoundException("Invalid product Id...");
		product.setProductName(productDtoUpdate.getProductName());
		product.setDescription(productDtoUpdate.getDescription());
		product.setMinPrice(productDtoUpdate.getMinPrice());
		product.setProductType(productDtoUpdate.getProductType());
		product.setHighestBid(productDtoUpdate.getMinPrice());
		product.setStatus(productDtoUpdate.getStatus());
		product.setStartDate(new Date());
		product.setEndDate(productDtoUpdate.getEndDate());
		productRepo.save(product);
	}

	
	@Override
	public ProductDto getProductById(int productId) {
		Product product = productRepo.getProductByProductId(productId);
		if (product == null) 
			throw new ProductNotFoundException("Invalid Product id..!!! ");
		ProductDto productDtoView=new ProductDto();
		productDtoView.setProductId(product.getProductId());
		productDtoView.setProductName(product.getProductName());
		productDtoView.setDescription(product.getDescription());
		productDtoView.setMinPrice(product.getMinPrice());
		productDtoView.setHighestBid(product.getHighestBid());
		productDtoView.setStatus(product.getStatus());
		productDtoView.setStartDate(product.getStartDate());
		productDtoView.setEndDate(product.getEndDate());
		productDtoView.setProductType(product.getProductType());
		User vendor=product.getVendor();
		productDtoView.setVendorName(vendor.getName());
		
		return productDtoView;
		
	}
	
	@Override
	public List<ProductDto> viewAllProducts() {
		List<Product> productList = productRepo.findAll();
		if (productList.isEmpty())
			throw new ProductNotFoundException("Products not found ..");
		List<ProductDto> productDtoList=new ArrayList<ProductDto>();
		for (Product product : productList) {
			ProductDto productDtoView=new ProductDto();
			productDtoView.setProductId(product.getProductId());
			productDtoView.setProductName(product.getProductName());
			productDtoView.setDescription(product.getDescription());
			productDtoView.setMinPrice(product.getMinPrice());
			productDtoView.setHighestBid(product.getHighestBid());
			productDtoView.setStatus(product.getStatus());
			productDtoView.setStartDate(product.getStartDate());
			productDtoView.setEndDate(product.getEndDate());
			productDtoView.setProductType(product.getProductType());
			User vendor=product.getVendor();
			productDtoView.setVendorName(vendor.getName());
			productDtoList.add(productDtoView);
		}
		
		return productDtoList;
	}
	@Override
	public List<ProductDto> viewAllProductsByType(String productType) {
		List<Product> productList = productRepo.getProductsByProductType(productType);
		if (productList.isEmpty())
			throw new ProductNotFoundException("Products not found .");
		List<ProductDto> productDtoList=new ArrayList<ProductDto>();
		for (Product product : productList) {
			ProductDto productDtoView=new ProductDto();
			productDtoView.setProductId(product.getProductId());
			productDtoView.setProductName(product.getProductName());
			productDtoView.setDescription(product.getDescription());
			productDtoView.setMinPrice(product.getMinPrice());
			productDtoView.setHighestBid(product.getHighestBid());
			productDtoView.setStatus(product.getStatus());
			productDtoView.setStartDate(product.getStartDate());
			productDtoView.setEndDate(product.getEndDate());
			productDtoView.setProductType(product.getProductType());
			User vendor=product.getVendor();
			productDtoView.setVendorName(vendor.getName());
			productDtoList.add(productDtoView);
		}
		
		return productDtoList;
	}
	

	@Override
	public List<ProductDto> viewAllProductsByVendorId(int vendorId) {
		User vendor= userRepo.getUserByUserId(vendorId);
		if(vendor==null)
			throw new UserNotFoundException("Invalid vendor Id..");
		List<Product> productList = productRepo.getProductsByVendorId(vendorId);
		if (productList.isEmpty())
			throw new ProductNotFoundException("Products not found.. ");
		List<ProductDto> productDtoList=new ArrayList<ProductDto>();
		for (Product product : productList) {
			ProductDto productDtoView=new ProductDto();
			productDtoView.setProductId(product.getProductId());
			productDtoView.setProductName(product.getProductName());
			productDtoView.setDescription(product.getDescription());
			productDtoView.setMinPrice(product.getMinPrice());
			productDtoView.setHighestBid(product.getHighestBid());
			productDtoView.setStatus(product.getStatus());
			productDtoView.setStartDate(product.getStartDate());
			productDtoView.setEndDate(product.getEndDate());
			productDtoView.setProductType(product.getProductType());
			vendor=product.getVendor();
			productDtoView.setVendorName(vendor.getName());
			productDtoList.add(productDtoView);
		}
		
		return productDtoList;
	}

	@Override
	public void deleteProduct(int productId) {
		Product product = productRepo.getProductByProductId(productId);
		if (product == null)
			throw new ProductNotFoundException("Invalid Product id..!!! ");
		productRepo.deleteById(productId);
	
	}

}
