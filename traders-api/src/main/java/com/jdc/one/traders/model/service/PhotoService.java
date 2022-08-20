package com.jdc.one.traders.model.service;

import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;

public interface PhotoService {

	/**
	 * Upload Single Photo
	 * @param folder
	 * @param fileName
	 * @param file
	 * @return
	 */
	Optional<String> create(Path folder, String fileName, MultipartFile file);
	
	/**
	 * Upload Multiple Photos
	 * @param folder
	 * @param fileName
	 * @param file
	 * @return
	 */
	Stream<String> create(Path folder, String fileName, MultipartFile[] file);
}
