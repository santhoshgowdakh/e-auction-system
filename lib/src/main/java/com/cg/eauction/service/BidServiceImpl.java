package com.cg.eauction.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.eauction.dto.BidDto;
import com.cg.eauction.dto.BidDtoAdd;
import com.cg.eauction.entities.Bid;
import com.cg.eauction.entities.Product;
import com.cg.eauction.entities.User;
import com.cg.eauction.exceptions.BidNotFoundException;
import com.cg.eauction.exceptions.InvalidBidPriceException;
import com.cg.eauction.exceptions.ProductNotFoundException;
import com.cg.eauction.exceptions.UserNotFoundException;
import com.cg.eauction.repository.BidRepository;
import com.cg.eauction.repository.ProductRepository;
import com.cg.eauction.repository.UserRepository;
@Service
public class BidServiceImpl implements BidService {
	@Autowired
	BidRepository bidRepo;
	
	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	UserRepository userRepo;
	@Override
	public int addBidToProduct(BidDtoAdd bidDtoAdd) {
		Product product=productRepo.getProductByProductId(bidDtoAdd.getProductId());
		if (product==null)
			throw new ProductNotFoundException("Invalid product id...");
		User user=userRepo.getUserByUserId(bidDtoAdd.getCustomerId());
		if(user==null)
			throw new UserNotFoundException("Enter valid customer Id...");
		if(user.getUserType().equals("vendor")||user.getUserType().equals("Vendor"))
			throw new UserNotFoundException("Vendors cannot bid on products ... ");
		if(bidDtoAdd.getBidPrice()<=product.getHighestBid())
			throw new InvalidBidPriceException("Bid price should be more than highest bid....");
		Bid bid=new Bid();
		bid.setBidPrice(bidDtoAdd.getBidPrice());
		bid.setDate(new Date());
		bid.setProduct(product);
		bid.setProductName(product.getProductName());
		bid.setVendor(product.getVendor());
		bid.setCustomer(userRepo.getUserByUserId( bidDtoAdd.getCustomerId()));
		product.setHighestBid(bidDtoAdd.getBidPrice());
		productRepo.save(product);
		bidRepo.save(bid);
		 return bid.getBiddingId();
		
	}
	@Override
	public void deleteBid(int biddingId) {
		Bid bid=bidRepo.getBidByBidId(biddingId);
		if(bid==null)
			throw new BidNotFoundException("Invalid bidding Id...");
		Product product= productRepo.getProductByProductId(bid.getProduct().getProductId());
		List<Bid> bidList=bidRepo.getBidsByProductId(product.getProductId());
		product.setHighestBid(bidList.get(bidList.size()-2).getBidPrice());
		bidRepo.deleteById(biddingId);
		
	}
	@Override
	public List<BidDto> viewBidsByCustomerId(int userId) {
		User customer=userRepo.getUserByUserId(userId);
		if(customer==null)
			throw new UserNotFoundException("Enter valid customer Id...");
		List<Bid> bidList=bidRepo.getBidsByCustomerId(userId);
		if(bidList.isEmpty())
			throw new BidNotFoundException("Customer not placed any bids...");
		List<BidDto> bidDtoList=new ArrayList<BidDto>();
		for (Bid bid : bidList) {
			BidDto bidDto=new BidDto();
			bidDto.setBiddingId(bid.getBiddingId());
			bidDto.setBidPrice(bid.getBidPrice());
			bidDto.setProductName(bid.getProductName());
			bidDto.setCustomerName(customer.getName());
			User vendor=bid.getVendor();
			bidDto.setVendorName(vendor.getName());
			bidDto.setDate(bid.getDate());
			bidDtoList.add(bidDto);
		}
		Collections.reverse(bidDtoList);
		return bidDtoList;
		
	}
	@Override
	public List<BidDto> viewBidsByProductId(int productId) {
		Product product = productRepo.getProductByProductId(productId);
		if (product == null) 
			throw new ProductNotFoundException("Invalid Product id..!!! ");
		List<Bid> bidList=bidRepo.getBidsByProductId(productId);
		if(bidList.isEmpty())
			throw new BidNotFoundException("Not placed any bids on this product");
		List<BidDto> bidDtoList=new ArrayList<BidDto>();
		for (Bid bid : bidList) {
			BidDto bidDto=new BidDto();
			bidDto.setBiddingId(bid.getBiddingId());
			bidDto.setBidPrice(bid.getBidPrice());
			bidDto.setProductName(bid.getProductName());
			User customer=bid.getCustomer();
			bidDto.setCustomerName(customer.getName());
			User vendor=bid.getVendor();
			bidDto.setVendorName(vendor.getName());
			bidDto.setDate(bid.getDate());
			bidDtoList.add(bidDto);
		}
		Collections.reverse(bidDtoList);
		return bidDtoList;
		
	}
	@Override
	public List<BidDto> viewAllBids() {
		List<Bid> bidList=bidRepo.findAll();
		if(bidList.isEmpty())
			throw new BidNotFoundException("Bid doesnot exists...");
		List<BidDto> bidDtoList=new ArrayList<BidDto>();
		for (Bid bid : bidList) {
			BidDto bidDto=new BidDto();
			bidDto.setBiddingId(bid.getBiddingId());
			bidDto.setBidPrice(bid.getBidPrice());
			bidDto.setProductName(bid.getProductName());
			User customer=bid.getCustomer();
			bidDto.setCustomerName(customer.getName());
			User vendor=bid.getVendor();
			bidDto.setVendorName(vendor.getName());
			bidDto.setDate(bid.getDate());
			bidDtoList.add(bidDto);
		}
		Collections.reverse(bidDtoList);
		return bidDtoList;
	}
	@Override
	public BidDto viewBidByBiddingId(int biddingId) {
		Bid bid=bidRepo.getBidByBidId(biddingId);
		if(bid==null)
			throw new BidNotFoundException("Invalid bidding Id..");
		BidDto bidDto=new BidDto();
		bidDto.setBiddingId(bid.getBiddingId());
		bidDto.setBidPrice(bid.getBidPrice());
		bidDto.setProductName(bid.getProductName());
		User customer=bid.getCustomer();
		bidDto.setCustomerName(customer.getName());
		User vendor=bid.getVendor();
		bidDto.setVendorName(vendor.getName());
		bidDto.setDate(bid.getDate());
		return bidDto;
	}
	
}
