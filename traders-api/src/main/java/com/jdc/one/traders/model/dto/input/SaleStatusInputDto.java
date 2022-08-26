package com.jdc.one.traders.model.dto.input;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jdc.one.traders.model.dto.entity.Sale.Status;

public record SaleStatusInputDto(
	int updateUser,
	Status status,
	String message,
	List<MultipartFile> images
		) {

}
