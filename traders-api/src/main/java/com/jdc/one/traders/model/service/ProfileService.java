package com.jdc.one.traders.model.service;

import org.springframework.web.multipart.MultipartFile;

import com.jdc.one.traders.model.dto.input.AccountProfile;
import com.jdc.one.traders.model.dto.input.AddressDto;
import com.jdc.one.traders.model.dto.input.BankingDto;
import com.jdc.one.traders.model.dto.output.AddressVO;

public interface ProfileService {

	AccountProfile save(AccountProfile profile);

	AccountProfile findOne(int id);

	AccountProfile updateProfileImage(int id, MultipartFile file);

	void addBanking(int id, BankingDto dto);

	AddressVO addAddress(int id, AddressDto dto);

	AddressVO findAddressById(int id);

}
