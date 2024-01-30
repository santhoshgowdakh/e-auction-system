package com.cg.eauction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.eauction.entities.Bid;

@Repository
public interface BidRepository extends JpaRepository<Bid, Integer> {

	@Query(value = "select bid from Bid bid where bid.customer.userId=?1")
	List<Bid> getBidsByCustomerId(int customerId);
	
	@Query(value = "select bid from Bid bid where bid.biddingId=?1")
	Bid getBidByBidId(int biddingId);
	
	@Query(value = "select bid from Bid bid where bid.product.productId=?1")
	List<Bid> getBidsByProductId(int productId);
	
}
