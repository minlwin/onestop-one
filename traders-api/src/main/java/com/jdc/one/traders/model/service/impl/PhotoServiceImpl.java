package com.jdc.one.traders.model.service.impl;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.one.traders.model.TradersApiException;
import com.jdc.one.traders.model.dto.output.PhotoUploadResult;
import com.jdc.one.traders.model.service.PhotoService;

@Service
public class PhotoServiceImpl implements PhotoService{

	@Value("${app.photo.location}")
	private String photoLocation;

	@Override
	public PhotoUploadResult save(int account, MultipartFile file) {
		try {
			var accountFolder = Path.of(photoLocation, String.valueOf(account));
			if(!Files.isDirectory(accountFolder, LinkOption.NOFOLLOW_LINKS)) {
				Files.createDirectories(accountFolder);
			}
			
			var array = file.getOriginalFilename().split("\\.");
			var fileName = String.format("img-%s.%s",
					LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss")),
					array[array.length - 1]);
			
			var imagePath = accountFolder.resolve(fileName);
			Files.copy(file.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
			
			return PhotoUploadResult.success("%d/%s".formatted(account, fileName));
			
		} catch (Exception e) {
			throw new TradersApiException(e);
		}
	}

}
