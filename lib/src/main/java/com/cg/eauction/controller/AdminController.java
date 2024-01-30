package com.cg.eauction.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.eauction.dto.BidDto;
import com.cg.eauction.dto.FeedbackDto;
import com.cg.eauction.dto.ProductDto;
import com.cg.eauction.dto.ProductDtoUpdate;
import com.cg.eauction.dto.UserDto;
import com.cg.eauction.dto.UserDtoUpdate;
import com.cg.eauction.service.BidServiceImpl;
import com.cg.eauction.service.ProductServiceImpl;
import com.cg.eauction.service.UserServiceImpl;
import com.cg.eauction.utils.ApiResponse;
@CrossOrigin(origins= "*" ,maxAge = 3600)
@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	UserServiceImpl userService;
	@Autowired
	ProductServiceImpl productService;
	@Autowired
	BidServiceImpl bidService;
	
	@GetMapping("/user")
	public ResponseEntity<List<UserDto>> viewAllUsers(){
		List<UserDto> userDtoList= userService.viewAllUsers();
		return new ResponseEntity<List<UserDto>>(userDtoList, HttpStatus.OK);
	}
	@GetMapping("/user/{userId}")
	public ResponseEntity<UserDto> getUserByUserId(@PathVariable int userId){
		UserDto userDto=userService.getUserByUserId(userId);
		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}
	@DeleteMapping("/user/{userId}")
	public ResponseEntity<ApiResponse> deleteUserByUserId(@PathVariable int userId){
		userService.deleteUser(userId);
		ApiResponse response=new ApiResponse();
		response.setMessage("User Deleted..");
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	@PutMapping("/user")
	public ResponseEntity<ApiResponse> updateUser(@RequestBody UserDtoUpdate userDtoUpdate){
		userService.updateUserDetails(userDtoUpdate);
		ApiResponse response=new ApiResponse();
		response.setMessage("User Details Updated..");
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	@PutMapping("/product")
	public ResponseEntity<ApiResponse>updateProduct(@RequestBody ProductDtoUpdate productDtoUpdate){
		productService.updateProduct(productDtoUpdate);
		ApiResponse response=new ApiResponse();
		response.setMessage("Product details Updated..");
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	@DeleteMapping("/product/{productId}")
	public ResponseEntity<ApiResponse> deleteProductByProductId(@PathVariable int productId){
		productService.deleteProduct(productId);
		ApiResponse response=new ApiResponse();
		response.setMessage("Product Deleted...");
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	@GetMapping("/product")
	public ResponseEntity<List<ProductDto>> viewAllProducts(){
		List<ProductDto> productDtoList= productService.viewAllProducts();
		return new ResponseEntity<List<ProductDto>>(productDtoList, HttpStatus.OK);
	}
	@GetMapping("/product/{productId}")
	public ResponseEntity<ProductDto> viewProductByProductId(int productId){
		ProductDto productDto = productService.getProductById(productId);
			return new ResponseEntity<ProductDto>(productDto,HttpStatus.OK);
	}
	@GetMapping("/product/{productType}")
	public ResponseEntity<List<ProductDto>> viewProductByProductType(String productType){
		List<ProductDto> productDtoList = productService.viewAllProductsByType(productType);
			return new ResponseEntity<List<ProductDto>>(productDtoList,HttpStatus.OK);
	}
	@GetMapping("/bids")
	public ResponseEntity<List<BidDto>> viewAllBids(){
		List<BidDto> bidDtoList=bidService.viewAllBids();
		return new ResponseEntity<List<BidDto>>(bidDtoList,HttpStatus.OK);
	}
	@GetMapping("/bids/id/{customerId}")
	public ResponseEntity<List<BidDto>> viewBidsByCustomerId(int userId){
		List<BidDto> bidDtoList=bidService.viewBidsByCustomerId(userId);
		return new ResponseEntity<List<BidDto>>(bidDtoList,HttpStatus.OK);
	}
	@GetMapping("/bids/{productId}")
	public ResponseEntity<List<BidDto>> viewBidsByProductId(int productId){
		List<BidDto> bidDtoList=bidService.viewBidsByProductId(productId);
		return new ResponseEntity<List<BidDto>>(bidDtoList,HttpStatus.OK);
	}
	@DeleteMapping("/bids/{productId}")
	public ResponseEntity<ApiResponse> deleteBidByBiddingId(int biddingId){
		bidService.deleteBid(biddingId);
		ApiResponse response=new ApiResponse();
		response.setMessage("Bid Deleted..");
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	@GetMapping("/feedback")
	public ResponseEntity<List<FeedbackDto>> viewAllFeedbacks(){
		List<FeedbackDto> feedbackDtoList=userService.viewAllFeedback();
		return new ResponseEntity<List<FeedbackDto>>(feedbackDtoList,HttpStatus.OK);
	}

}
