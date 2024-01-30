package com.cg.eauction.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.eauction.entities.Address;
import com.cg.eauction.repository.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	AddressRepository addressRepo;
	
	@Override
	public void addAddress(Address address) {
		addressRepo.save(address);
	}

	@Override
	public void updateAddress(Address address) {
		addressRepo.save(address);
	}

	@Override
	public List<Address> viewAllAddresses() {
		return addressRepo.findAll();
	}

	@Override
	public Address getAddressByAddressId(int addressId) {
		return addressRepo.getAddressByAddressId(addressId);
	}

	@Override
	public void deleteAddress(int addressId) {
		addressRepo.deleteById(addressId);
	}

	

}
