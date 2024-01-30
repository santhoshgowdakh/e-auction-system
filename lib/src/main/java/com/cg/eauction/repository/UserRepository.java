package com.cg.eauction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.eauction.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
		@Query(value = "select user from User user where user.userId=?1")
		User getUserByUserId(int userId);
		@Query(value = "select user from User user where user.userName=?1")
		User getUserByUserName(String userName);

}
