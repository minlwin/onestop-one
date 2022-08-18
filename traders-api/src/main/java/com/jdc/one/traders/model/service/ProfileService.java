package com.jdc.one.traders.model.service;

import org.springframework.web.multipart.MultipartFile;

import com.jdc.one.traders.model.dto.input.AccountProfile;

public interface ProfileService {

	AccountProfile save(AccountProfile profile);

	AccountProfile findOne(int id);

	AccountProfile updateProfileImage(int id, MultipartFile file);

}
