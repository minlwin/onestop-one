package com.jdc.one.traders.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdc.one.traders.model.dto.entity.Address;

public interface AddressRepo extends JpaRepository<Address, Integer>{

}
