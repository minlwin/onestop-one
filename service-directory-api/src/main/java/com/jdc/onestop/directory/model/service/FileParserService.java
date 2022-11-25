package com.jdc.onestop.directory.model.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.onestop.directory.model.ServiceDirectoryAppException;
import com.jdc.onestop.directory.model.dto.ErrorResult.ErrorType;

@Service
public class FileParserService {

	public List<String> parse(MultipartFile file) {
		
		var list = new ArrayList<String>();
		
		try(var reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
			
			String line = null;
			
			while(null != (line = reader.readLine())) {
				list.add(line);
			}
			
		} catch (Exception e) {
			throw new ServiceDirectoryAppException(ErrorType.Platform, List.of(e.getMessage()));
		}
		
		return list;
	}
}
