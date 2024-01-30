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
import com.cg.eauction.dto.BidDtoAdd;
import com.cg.eauction.dto.FeedbackDto;
import com.cg.eauction.dto.ProductDto;
import com.cg.eauction.dto.UserDto;
import com.cg.eauction.dto.UserDtoUpdate;
import com.cg.eauction.service.BidServiceImpl;
import com.cg.eauction.service.ProductServiceImpl;
import com.cg.eauction.service.UserServiceImpl;
import com.cg.eauction.utils.ApiResponse;

@CrossOrigin(origins= "*" ,maxAge = 3600)
@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	UserServiceImpl userService;
	@Autowired
	ProductServiceImpl productService;
	@Autowired
	BidServiceImpl bidService;
	
	@PutMapping
	public ResponseEntity<ApiResponse>updateUserDetails(@RequestBody UserDtoUpdate userDtoUpdate){
	userService.updateUserDetails(userDtoUpdate);
	ApiResponse response=new ApiResponse();
	response.setMessage("User Details Updated..");
	return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserByUserId(@PathVariable int userId){
	UserDto userDto=userService.getUserByUserId(userId);
	return new ResponseEntity<UserDto>(userDto,HttpStatus.OK);
	}
	@GetMapping("/product")
	public ResponseEntity<List<ProductDto>> viewAllProducts(){
		List<ProductDto> productDtoList= productService.viewAllProducts();
		return new ResponseEntity<List<ProductDto>>(productDtoList, HttpStatus.OK);
	}
	@GetMapping("/product/{productType}")
	public ResponseEntity<List<ProductDto>> viewProductByProductType(@PathVariable String productType){
		List<ProductDto> productDtoList = productService.viewAllProductsByType(productType);
			return new ResponseEntity<List<ProductDto>>(productDtoList,HttpStatus.OK);
	}
	@GetMapping("/product/id/{productId}")
	public ResponseEntity<ProductDto> viewProductByProductId(@PathVariable int productId){
		ProductDto productDto = productService.getProductById(productId);
			return new ResponseEntity<ProductDto>(productDto,HttpStatus.OK);
	}
	@PostMapping("/bid")
	public ResponseEntity<ApiResponse> bidProduct(@RequestBody BidDtoAdd bidDtoAdd){
		int biddingId=bidService.addBidToProduct(bidDtoAdd);
		ApiResponse response=new ApiResponse();
		response.setMessage("Bid placed bid Id :"+biddingId);
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
		
	}
	@DeleteMapping("/bid/{biddingId}")
	public ResponseEntity<ApiResponse> deleteBid(@PathVariable int biddingId){
		bidService.deleteBid(biddingId);
		ApiResponse response=new ApiResponse();
		response.setMessage("Bid Deleted..");
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	@GetMapping("/bids/id/{userId}")
	public ResponseEntity<List<BidDto>> viewBidsByCustomerId(@PathVariable int userId){
		List<BidDto> bidDtoList=bidService.viewBidsByCustomerId(userId);
		return new ResponseEntity<List<BidDto>>(bidDtoList,HttpStatus.OK);
	}
	@GetMapping("/bids/{productId}")
	public ResponseEntity<List<BidDto>> viewBidsByProductId(@PathVariable int productId){
		List<BidDto> bidDtoList=bidService.viewBidsByProductId(productId);
		return new ResponseEntity<List<BidDto>>(bidDtoList,HttpStatus.OK);
	}
	@PostMapping("/feedback")
	public ResponseEntity<ApiResponse> addFeedback(@RequestBody FeedbackDto feedbackDto){
		userService.addFeedback(feedbackDto);
		ApiResponse response=new ApiResponse();
		response.setMessage("Thank You for your feedback...");
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	@DeleteMapping("/feedback")
	public ResponseEntity<ApiResponse> deleteFeedback(int userId){
		userService.deleteFeedback(userId);
		ApiResponse response=new ApiResponse();
		response.setMessage("Feedback deleted...");
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	@GetMapping("/feedback/{userId}")
	public ResponseEntity<ApiResponse> viewFeedbackByUserId(@PathVariable int userId){
		String feedback=userService.viewFeedbackByUserId(userId);
		ApiResponse response=new ApiResponse();
		response.setMessage("feedback :"+feedback);
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	
}
