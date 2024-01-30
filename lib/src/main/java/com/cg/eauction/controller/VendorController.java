package com.cg.eauction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.eauction.dto.BidDto;
import com.cg.eauction.dto.FeedbackDto;
import com.cg.eauction.dto.ProductDto;
import com.cg.eauction.dto.ProductDtoAdd;
import com.cg.eauction.dto.ProductDtoUpdate;
import com.cg.eauction.dto.UserDto;
import com.cg.eauction.dto.UserDtoUpdate;
import com.cg.eauction.service.BidServiceImpl;
import com.cg.eauction.service.ProductServiceImpl;
import com.cg.eauction.service.UserServiceImpl;
import com.cg.eauction.utils.ApiResponse;
@CrossOrigin(origins= "*" ,maxAge = 3600)
@RestController
@RequestMapping("/vendor")
public class VendorController {
	@Autowired
	UserServiceImpl userService;
	@Autowired
	ProductServiceImpl productService;
	@Autowired
	BidServiceImpl bidService;

	@PutMapping
	public ResponseEntity<ApiResponse> updateUserDetails(@RequestBody UserDtoUpdate userDtoUpdate) {
		userService.updateUserDetails(userDtoUpdate);
		ApiResponse response=new ApiResponse();
		response.setMessage("User Details Updated..");
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserByUserId(@PathVariable int userId) {
		UserDto userDto = userService.getUserByUserId(userId);
		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}

	@PostMapping("/product")
	public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductDtoAdd productDtoAdd) {
		int productId = productService.addProduct(productDtoAdd);
		ApiResponse response=new ApiResponse();
		response.setMessage("Product added successfully, productId  : " + productId);
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}

	@PutMapping("/product")
	public ResponseEntity<ApiResponse> updateProduct(@RequestBody ProductDtoUpdate productDtoUpdate) {
		productService.updateProduct(productDtoUpdate);
		ApiResponse response=new ApiResponse();
		response.setMessage("Product Updated");
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}

	@GetMapping("/products/{userId}")
	public ResponseEntity<List<ProductDto>> getAllProductsByVendorId(@PathVariable int  userId) {
		List<ProductDto> productDtoList = productService.viewAllProductsByVendorId(userId);
		return new ResponseEntity<List<ProductDto>>(productDtoList, HttpStatus.OK);
	}

	@GetMapping("/product/{productId}")
	public ResponseEntity<ProductDto> viewProductByProductId(@PathVariable int productId) {
		ProductDto productDto = productService.getProductById(productId);
		return new ResponseEntity<ProductDto>(productDto, HttpStatus.OK);
	}

	@DeleteMapping("/product/{productId}")
	public ResponseEntity<ApiResponse> deleteProduct(@PathVariable int productId) {
		productService.deleteProduct(productId);
		ApiResponse response=new ApiResponse();
		response.setMessage("Product Deleted..");
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}

	@PostMapping("/feedback")
	public ResponseEntity<ApiResponse> addFeedback(@RequestBody FeedbackDto feedbackDto) {
		userService.addFeedback(feedbackDto);
		ApiResponse response=new ApiResponse();
		response.setMessage("Thank You for your feedback...");
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	@DeleteMapping("/feedback")
	public ResponseEntity<String> deleteFeedback(int userId) {
		userService.deleteFeedback(userId);
		return new ResponseEntity<String>("Feedback deleted...", HttpStatus.OK);
	}

	@GetMapping("/feedback/{userId}")
	public ResponseEntity<ApiResponse> viewFeedback(@PathVariable int userId) {
		String feedback = userService.viewFeedbackByUserId(userId);
		ApiResponse response=new ApiResponse();
		response.setMessage(feedback);
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	@GetMapping("/bids{productId}")
	public ResponseEntity<List<BidDto>> viewBidsByProductId(@PathVariable int productId) {
		List<BidDto> bidList= bidService.viewBidsByProductId(productId);
		return new ResponseEntity<List<BidDto>>(bidList, HttpStatus.OK);
	}

}
