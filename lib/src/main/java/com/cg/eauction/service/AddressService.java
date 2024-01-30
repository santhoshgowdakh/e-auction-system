package com.cg.eauction.service;

import java.util.List;

import com.cg.eauction.entities.Address;

public interface AddressService {
	void addAddress(Address address);
	void updateAddress(Address address);
	List<Address> viewAllAddresses();
	Address getAddressByAddressId(int addressId);
	void deleteAddress(int addressId);
}
