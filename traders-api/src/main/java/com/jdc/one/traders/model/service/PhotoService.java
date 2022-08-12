package com.jdc.one.traders.model.service;

import org.springframework.web.multipart.MultipartFile;

public interface PhotoService {

	String save(int account, MultipartFile file);

}
