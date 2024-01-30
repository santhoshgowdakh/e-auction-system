package com.cg.eauction.test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.eauction.dto.BidDtoAdd;
import com.cg.eauction.entities.Bid;
import com.cg.eauction.repository.BidRepository;
import com.cg.eauction.repository.ProductRepository;
import com.cg.eauction.repository.UserRepository;
import com.cg.eauction.service.BidService;

@SpringBootTest
class BidDaoTest {

	@Autowired
	BidRepository bidRepo;
	@Autowired
	ProductRepository productRepo;
	@Autowired
	BidService bidService;
	@Autowired
	UserRepository userRepo;
	@Test
	void testAddBidToProduct() {
		BidDtoAdd bidDtoAdd=new BidDtoAdd();
		bidDtoAdd.setBidPrice(15300);
		bidDtoAdd.setCustomerId(1);
		bidDtoAdd.setProductId(10004);
		int bidId=bidService.addBidToProduct(bidDtoAdd);
		assertEquals(15300, bidRepo.getBidByBidId(bidId).getBidPrice());
		assertEquals(1, bidRepo.getBidByBidId(bidId).getCustomer().getUserId());
		assertEquals(10004, bidRepo.getBidByBidId(bidId).getProduct().getProductId());
		assertEquals(15300, productRepo.getProductByProductId(10004).getHighestBid());
		
	}
	@Test
	void testViewBidsByCustomerId() {
		List<Bid> bidList = bidRepo.getBidsByCustomerId(1);
		assertNotNull(bidList);
	}
	@Test
	void testViewBidsByProductId() {
		List<Bid> bidList = bidRepo.getBidsByProductId(1);
		assertNotNull(bidList);
	}
	@Test
	void testViewAllBids() {
		List<Bid> bidList = bidRepo.findAll();
		assertNotNull(bidList);
	}
	@Test
	void testViewBidByBiddingId() {
		Bid bid=bidRepo.getBidByBidId(9);
		assertEquals(9, bid.getBiddingId());
	}
	@Test
	void testDeleteBid() {
		bidRepo.deleteById(12);
		Bid bid=bidRepo.getBidByBidId(12);
		assertNull(bid);
	}
}
