package com.cg.eauction.entities;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
	@SequenceGenerator(name = "user_id_seq", initialValue = 33001,
	sequenceName = "user_id_seq_gen", allocationSize = 5)
	@GeneratedValue(generator = "user_id_seq", strategy = GenerationType.SEQUENCE)
	private int userId;
	@Column(name = "name", length = 30,nullable = false)
	private String name;
	@Column(name = "userName", length = 20,nullable = false,unique = true)
	private String userName;
	@Column(name = "password", length = 30,nullable = false)
	private String password;
	@Column(name = "dateOfBirth", nullable = false,columnDefinition = "date")
	private Date dateOfBirth;
	@Column(name = "mobile", length = 10,nullable = false)
	private String mobile;
	@Column(name = "mailId", length = 30,nullable = false)
	private String mailId;
	@Column(name = "userType", length = 15,nullable = false)
	private String userType;
	@OneToOne
	@JoinColumn(name = "addressId")
	private Address address;
	@Column(name = "feedback",length = 200)
	private String feedback;
	

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	
}
