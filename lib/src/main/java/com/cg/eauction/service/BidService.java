package com.cg.eauction.service;

import java.util.List;

import com.cg.eauction.dto.BidDto;
import com.cg.eauction.dto.BidDtoAdd;

public interface BidService {

	int addBidToProduct(BidDtoAdd bidDtoAdd);
	List<BidDto> viewBidsByCustomerId(int userId);
	List<BidDto> viewBidsByProductId(int productId);
	List<BidDto> viewAllBids();
	BidDto viewBidByBiddingId(int biddingId);
	void deleteBid(int biddingId);
	

}
