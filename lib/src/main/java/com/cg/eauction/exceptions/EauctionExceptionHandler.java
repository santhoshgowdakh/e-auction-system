package com.cg.eauction.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.eauction.utils.ApiResponse;

@ControllerAdvice
public class EauctionExceptionHandler {
	
	@ExceptionHandler(value=BidNotFoundException .class)
	public ResponseEntity<String> exception(BidNotFoundException exception){
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value=InvalidBidPriceException .class)
	public ResponseEntity<String> exception(InvalidBidPriceException exception){
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value=InvalidPasswordException .class)
	public ResponseEntity<ApiResponse> exception(InvalidPasswordException exception){
		ApiResponse response=new ApiResponse();
		response.setMessage(exception.getMessage());
		return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value=UserNotFoundException .class)
	public ResponseEntity<String> exception(UserNotFoundException exception){
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value=ProductNotFoundException .class)
	public ResponseEntity<String> exception(ProductNotFoundException exception){
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value=UserNameAlreadyExistException .class)
	public ResponseEntity<String> exception(UserNameAlreadyExistException exception){
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value = InvalidEmailException.class)
	public ResponseEntity<Object> exception(InvalidEmailException exception){
		return new ResponseEntity<Object>(exception.getMessage() ,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value = InvalidMobileNumberException.class)
	public ResponseEntity<Object> exception(InvalidMobileNumberException exception){
		return new ResponseEntity<Object>( exception.getMessage(),HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value = InvalidUserNameException.class)
	public ResponseEntity<Object> exception(InvalidUserNameException exception){
		return new ResponseEntity<Object>( exception.getMessage(),HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value = FeedbackNotFoundException.class)
	public ResponseEntity<Object> exception(FeedbackNotFoundException exception){
		return new ResponseEntity<Object>( exception.getMessage(),HttpStatus.NOT_FOUND);
	}
}
