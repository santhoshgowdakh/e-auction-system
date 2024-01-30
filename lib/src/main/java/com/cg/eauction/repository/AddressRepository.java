package com.cg.eauction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.eauction.entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

	@Query(value = "select address from Address address where address.addressId=?1")
	Address getAddressByAddressId(int addressId);
}
