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
@Table(name = "bids")
public class Bid {

	@Id
	@SequenceGenerator(name = "bidding_id_seq", initialValue = 10001, sequenceName = "bidding_id_seq_gen", allocationSize = 321)
	@GeneratedValue(generator = "bidding_id_seq", strategy = GenerationType.SEQUENCE)
	private int biddingId;
	@ManyToOne
	@JoinColumn(name = "productId")
	private Product product;
	@Column(name = "productName", length = 25)
	private String productName;
	@Column(name = "bidPrice", columnDefinition = "numeric(8,2)")
	private int bidPrice;
	@ManyToOne
	@JoinColumn(name = "customererId")
	private User customer;
	@ManyToOne
	@JoinColumn(name = "vendorId")
	private User vendor;

	@Column(name = "date", columnDefinition = "date default current_date")
	private Date date;

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public User getVendor() {
		return vendor;
	}

	public void setVendor(User vendor) {
		this.vendor = vendor;
	}

	public void setBiddingId(int biddingId) {
		this.biddingId = biddingId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getBiddingId() {
		return biddingId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(int bidPrice) {
		this.bidPrice = bidPrice;
	}

}
