package com.jdc.one.traders.model.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.one.traders.model.service.PhotoService;

@Service
public class PhotoServiceImpl implements PhotoService{

	@Value("${app.photo.location}")
	private String photoLocation;

	@Override
	public Optional<String> create(Path folder, String fileName, MultipartFile file) {
		
		// Prepare Image Folder
		var imageFolder = prepare(folder);
		
		// Image File
		var imageFile = Path.of("%s.%s".formatted(fileName, extension(file)));
		
		// Save File
		saveFile(imageFolder.resolve(imageFile), file);
		
		// Return File Name
		return Optional.of(folder.resolve(imageFile).toString());
	}

	@Override
	public Stream<String> create(Path folder, String fileName, MultipartFile[] files) {
		// Prepare Image Folder
		var imageFolder = prepare(folder);

		return IntStream.range(0, files.length).mapToObj(index -> {
			var file = files[index];
			
			// Extract File Extension
			// Image Path
			var imagePath = Path.of("%s-%d.%s".formatted(fileName, index + 1, extension(files[index])));
			
			// Save File
			saveFile(imageFolder.resolve(imagePath), file);

			// Return File Name
			return folder.resolve(imagePath).toString();
		});
	}

	private Path prepare(Path folder) {
		try {
			var imageFolder = Path.of(photoLocation).resolve(folder);
			if(!Files.exists(imageFolder, LinkOption.NOFOLLOW_LINKS)) {
				Files.createDirectories(imageFolder);
			}
			
			Files.newDirectoryStream(imageFolder).forEach(child -> {
				try {
					Files.deleteIfExists(child);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			
			return imageFolder;
		} catch (Exception e) {
			throw new IllegalArgumentException("Cannot prepare %s.".formatted(folder.toString()));
		}
	}
	
	private void saveFile(Path path, MultipartFile file) {
		try {
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		} catch (java.io.IOException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Can not save file (%s).".formatted(path));
		}
	}

	private String extension(MultipartFile file) {
		var array = file.getOriginalFilename().split("\\.");
		
		if(array.length == 0) {
			throw new IllegalArgumentException("Invalid Image File Name (%s).".formatted(file.getOriginalFilename()));
		}
		
		return array[array.length - 1];
	}


}
