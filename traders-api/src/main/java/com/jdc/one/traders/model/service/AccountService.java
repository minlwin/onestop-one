package com.jdc.one.traders.model.service;

import java.util.List;
import java.util.Optional;

import com.jdc.one.traders.model.dto.input.AccountProfile;
import com.jdc.one.traders.model.dto.output.TopSellerDto;

public interface AccountService {

	List<TopSellerDto> getTopSellers(Optional<Integer> limit);

	AccountProfile save(AccountProfile profile);

	AccountProfile findOne(int id);

}
