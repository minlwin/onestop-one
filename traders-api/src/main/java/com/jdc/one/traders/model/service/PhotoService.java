package com.jdc.one.traders.model.service;

import org.springframework.web.multipart.MultipartFile;

import com.jdc.one.traders.model.dto.output.SimpleResult;

public interface PhotoService {

	SimpleResult save(int account, MultipartFile file);

}
