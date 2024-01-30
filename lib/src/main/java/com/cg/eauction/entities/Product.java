package com.cg.eauction.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
	@Id
	@SequenceGenerator(name = "product_id_seq", initialValue = 10001, sequenceName = "product_id_seq_gen", allocationSize = 2)
	@GeneratedValue(generator = "product_id_seq", strategy = GenerationType.SEQUENCE)
	private int productId;
	@Column(name = "productName", length = 25)
	private String productName;
	@Column(name = "minPrice")
	private int minPrice;
	@Column(name = "description", length = 150)
	private String description;
	@ManyToOne
	@JoinColumn(name = "vendorId")
	private User vendor;
	@Column(name = "highestBid")
	private double highestBid;
	@Column(name = "productType", length = 30)
	private String productType;
	@Column(name = "status", length = 15)
	private String status;
	@Column(name = "startDate", columnDefinition = "date")
	private Date startDate;
	@Column(name = "endDate", columnDefinition = "date")
	private Date endDate;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getVendor() {
		return vendor;
	}

	public void setVendor(User vendor) {
		this.vendor = vendor;
	}

	public double getHighestBid() {
		return highestBid;
	}

	public void setHighestBid(double highestBid) {
		this.highestBid = highestBid;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
