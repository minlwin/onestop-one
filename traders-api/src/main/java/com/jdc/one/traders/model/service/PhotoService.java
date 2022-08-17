package com.jdc.one.traders.model.service;

import org.springframework.web.multipart.MultipartFile;

import com.jdc.one.traders.model.dto.output.PhotoUploadResult;

public interface PhotoService {

	PhotoUploadResult save(int account, MultipartFile file);

}
