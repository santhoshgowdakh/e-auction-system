package com.cg.eauction.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.eauction.dto.FeedbackDto;
import com.cg.eauction.dto.LoginDto;
import com.cg.eauction.dto.UserDtoAdd;
import com.cg.eauction.dto.UserDtoUpdate;
import com.cg.eauction.entities.User;
import com.cg.eauction.repository.UserRepository;
import com.cg.eauction.service.UserService;

@SpringBootTest
class UserDaoTest {

	@Autowired
	UserRepository userRepo;
	@Autowired
	UserService userService;
	@Test
	void testAddUsers() {
		UserDtoAdd userDtoAdd=new UserDtoAdd();
		userDtoAdd.setCity("Agra");
		userDtoAdd.setDateOfBirth(new Date());
		userDtoAdd.setMailId("lara@abc.com");
		userDtoAdd.setMobile("8484848485");
		userDtoAdd.setName("Lara");
		userDtoAdd.setPassword("lara@12");
		userDtoAdd.setPincode(123321);
		userDtoAdd.setState("NewDelhi");
		userDtoAdd.setStreet("church st");
		userDtoAdd.setUserName("lara2");
		userDtoAdd.setUserType("Vendor");
		int id=userService.addUsers(userDtoAdd);
		User user=userRepo.getUserByUserName("lara2");
		assertEquals(id,user.getUserId());
		
	}
	@Test
	void testLogin() {
		LoginDto loginDto=new LoginDto();
		loginDto.setUserName("jack");
		loginDto.setPassword("jack@12");
		assertEquals("Jack", userService.login(loginDto));
	}
	@Test
	void testUpdateUserDetails() {
		UserDtoUpdate userDtoUpdate=new UserDtoUpdate();
		userDtoUpdate.setUserId(20);
		userDtoUpdate.setCity("Mumbai");
		userDtoUpdate.setDateOfBirth(new Date());
		userDtoUpdate.setMailId("tom@abc.com");
		userDtoUpdate.setMobile("7874747878");
		userDtoUpdate.setName("Peter");
		userDtoUpdate.setPincode(141414);
		userDtoUpdate.setState("Maharastra");
		userDtoUpdate.setStreet("RS rd");
		userService.updateUserDetails(userDtoUpdate);
		User user=userRepo.getUserByUserId(20);
		assertEquals("Peter", user.getName());
	}
	@Test
	void testViewAllUsers() {
		List<User> userList= userRepo.findAll();
		assertNotNull(userList);
	}
	@Test
	void testGetUserByUserId() {
		User user=userRepo.getUserByUserId(33002);
		assertEquals(33002, user.getUserId());
	}
	@Test
	void testDeleteUser(int userId) {
		userRepo.deleteById(5);
		User user= userRepo.getUserByUserId(5);
		assertNull(user);
	}
	@Test
	void testAddFeedback() {
		FeedbackDto feedbackDto=new FeedbackDto();
		feedbackDto.setFeedback("test feedback");
		feedbackDto.setUserId(1);
		userService.addFeedback(feedbackDto);
		assertEquals("test feedback", userService.viewFeedbackByUserId(1));
	}
	@Test
	void testDeleteFeedback() {
		userService.deleteFeedback(1);
		assertNull(userRepo.getUserByUserId(1));
	}
	@Test
	void testViewFeedbackByUserId() {
		FeedbackDto feedbackDto=new FeedbackDto();
		feedbackDto.setFeedback("test feedback");
		feedbackDto.setUserId(1);
		userService.addFeedback(feedbackDto);
		assertEquals("test feedback",userService.viewFeedbackByUserId(1));
		
	}
	@Test
	void testViewAllFeedback() {
		List<FeedbackDto> feedbackDtoList=userService.viewAllFeedback();
		assertNotNull(feedbackDtoList);
	}
}
