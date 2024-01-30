package com.cg.eauction.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.eauction.dto.FeedbackDto;
import com.cg.eauction.dto.LoginDto;
import com.cg.eauction.dto.UserDto;
import com.cg.eauction.dto.UserDtoAdd;
import com.cg.eauction.dto.UserDtoUpdate;
import com.cg.eauction.entities.Address;
import com.cg.eauction.entities.User;
import com.cg.eauction.exceptions.FeedbackNotFoundException;
import com.cg.eauction.exceptions.InvalidEmailException;
import com.cg.eauction.exceptions.InvalidMobileNumberException;
import com.cg.eauction.exceptions.InvalidPasswordException;
import com.cg.eauction.exceptions.InvalidUserNameException;
import com.cg.eauction.exceptions.UserNameAlreadyExistException;
import com.cg.eauction.exceptions.UserNotFoundException;
import com.cg.eauction.repository.UserRepository;
import com.cg.eauction.utils.LoginResponse;
import com.cg.eauction.utils.PasswordManagement;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;
	@Autowired
	AddressServiceImpl addressService;

	@Override
	public int addUsers(UserDtoAdd userDtoAdd) {
		User user = new User();
		Address address = new Address();
		User existingUser = userRepo.getUserByUserName(userDtoAdd.getUserName());
		if (existingUser != null)
			throw new UserNameAlreadyExistException("Username already exist");
		user.setUserName(userDtoAdd.getUserName());
		user.setName(userDtoAdd.getName());
		user.setDateOfBirth(userDtoAdd.getDateOfBirth());
		if(userDtoAdd.getMobile().length()!=10)
			throw new InvalidMobileNumberException("invalid mobile number...");
		user.setMobile(userDtoAdd.getMobile());
		if(!userDtoAdd.getMailId().contains("@")&&userDtoAdd.getMailId().contains("."))
			throw new InvalidEmailException("Invalid Email ...");
		user.setMailId(userDtoAdd.getMailId());
		user.setUserType(userDtoAdd.getUserType());
		user.setPassword(PasswordManagement.encryptPassword(userDtoAdd.getPassword()));
		address.setStreet(userDtoAdd.getStreet());
		address.setCity(userDtoAdd.getCity());
		address.setState(userDtoAdd.getState());
		address.setPincode(userDtoAdd.getPincode());
		addressService.addAddress(address);
		user.setAddress(address);
		userRepo.save(user);
		return user.getUserId();
	}

	@Override
	public void updateUserDetails(UserDtoUpdate userDtoUpdate) {
		User existingUser = userRepo.getUserByUserId(userDtoUpdate.getUserId());
		if (existingUser == null)
			throw new UserNotFoundException("User not found...");
		existingUser.setName(userDtoUpdate.getName());
		existingUser.setDateOfBirth(userDtoUpdate.getDateOfBirth());
		existingUser.setMobile(userDtoUpdate.getMobile());
		existingUser.setMailId(userDtoUpdate.getMailId());
		Address address = new Address();
		address.setStreet(userDtoUpdate.getStreet());
		address.setCity(userDtoUpdate.getCity());
		address.setState(userDtoUpdate.getState());
		address.setPincode(userDtoUpdate.getPincode());
		addressService.addAddress(address);
		existingUser.setAddress(address);
		existingUser.setUserName(existingUser.getUserName());
		existingUser.setPassword(existingUser.getPassword());
		existingUser.setUserType(existingUser.getUserType());
		userRepo.save(existingUser);
	}

	@Override
	public void deleteUser(int userId) {
		User existingUser = userRepo.getUserByUserId(userId);
		if (existingUser == null)
			throw new UserNotFoundException("User not found...");
		userRepo.deleteById(userId);
	}

	@Override
	public List<UserDto> viewAllUsers() {
		List<User> userList = userRepo.findAll();
		if(userList.isEmpty())
			throw new UserNotFoundException("Users not found..");
		List<UserDto> userDtoList = new ArrayList<UserDto>();
		for (User user : userList) {
			UserDto userDto = new UserDto();
			userDto.setUserId(user.getUserId());
			userDto.setName(user.getName());
			userDto.setDateOfBirth(user.getDateOfBirth());
			userDto.setMobile(user.getMobile());
			userDto.setMailId(user.getMailId());
			userDto.setUserType(user.getUserType());
			Address address = user.getAddress();
			userDto.setStreet(address.getStreet());
			userDto.setCity(address.getCity());
			userDto.setPincode(address.getPincode());
			userDto.setState(address.getState());
			userDtoList.add(userDto);
		}
		return userDtoList;
	}

	@Override
	public UserDto getUserByUserId(int userId) {
		User user = userRepo.getUserByUserId(userId);
		if(user==null)
			throw new UserNotFoundException("invalid user Id");
		UserDto userDto = new UserDto();
		userDto.setUserId(user.getUserId());
		userDto.setName(user.getName());
		userDto.setDateOfBirth(user.getDateOfBirth());
		userDto.setMobile(user.getMobile());
		userDto.setMailId(user.getMailId());
		userDto.setUserType(user.getUserType());
		Address address = user.getAddress();
		userDto.setStreet(address.getStreet());
		userDto.setCity(address.getCity());
		userDto.setPincode(address.getPincode());
		userDto.setState(address.getState());
		return userDto;
	}

	@Override
	public void addFeedback(FeedbackDto feedbackDto) {
		User user = userRepo.getUserByUserId(feedbackDto.getUserId());
		if (user == null)
			throw new UserNotFoundException("User doesnot exists..");
		user.setFeedback(feedbackDto.getFeedback());
		userRepo.save(user);
	}

	@Override
	public void deleteFeedback(int userId) {
		User user = userRepo.getUserByUserId(userId);
		if (user == null)
			throw new UserNotFoundException("Invalid user Id..");
		user.setFeedback(null);
		userRepo.save(user);
	}

	@Override
	public String viewFeedbackByUserId(int userId) {
		User user = userRepo.getUserByUserId(userId);
		if (user == null)
			throw new UserNotFoundException("User not exists...");
		if(user.getFeedback()==null)
			throw new FeedbackNotFoundException("Feedback not found"); 
		return user.getFeedback();
	}

	@Override
	public List<FeedbackDto> viewAllFeedback() {
		List<FeedbackDto> feedbackDtoList=new ArrayList<FeedbackDto>();
		List<User> userList = userRepo.findAll();
		if(userList.isEmpty())
			throw new UserNotFoundException("Users not found..");
		for (User user : userList) {
			FeedbackDto feedbackDto=new FeedbackDto();
			if(user.getFeedback()==null)
				continue;
			feedbackDto.setFeedback( user.getFeedback());
			feedbackDto.setUserId(user.getUserId());
			feedbackDtoList.add(feedbackDto);
		}
		if(feedbackDtoList.isEmpty())
			throw new FeedbackNotFoundException("Users not given any feedbak..");
		return feedbackDtoList;
	}

	@Override
	public LoginResponse login(LoginDto loginDto) {
		User user = userRepo.getUserByUserName(loginDto.getUserName());
		if(user==null)
			throw new InvalidUserNameException("Inavlid UserName.."+loginDto.getUserName());
		else if(user.getPassword().equals( PasswordManagement.encryptPassword(loginDto.getPassword()))) {
			LoginResponse loginResponse=new LoginResponse();
			loginResponse.setUserId(user.getUserId());
			loginResponse.setUserType(user.getUserType());
			return loginResponse;
		}
		else
			throw new InvalidPasswordException("Incorrect Password..");
	}

}
