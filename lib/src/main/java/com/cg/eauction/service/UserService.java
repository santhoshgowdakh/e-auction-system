package com.cg.eauction.service;

import java.util.List;

import com.cg.eauction.dto.FeedbackDto;
import com.cg.eauction.dto.LoginDto;
import com.cg.eauction.dto.UserDto;
import com.cg.eauction.dto.UserDtoAdd;
import com.cg.eauction.dto.UserDtoUpdate;
import com.cg.eauction.utils.LoginResponse;

public interface UserService {
	
	int addUsers(UserDtoAdd userDtoAdd);
	LoginResponse login(LoginDto loginDto);
	public void updateUserDetails(UserDtoUpdate userDtoUpdate);
	List<UserDto> viewAllUsers();
	UserDto getUserByUserId(int userId);
	void deleteUser(int userId);
	void addFeedback(FeedbackDto feedbackDto);
	void  deleteFeedback(int userId);
	String viewFeedbackByUserId(int userId);
	List<FeedbackDto> viewAllFeedback();
}
