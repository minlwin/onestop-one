package com.jdc.onestop.directory.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.onestop.directory.model.ServiceDirectoryAppException;
import com.jdc.onestop.directory.model.dto.StateDto;
import com.jdc.onestop.directory.model.dto.form.StateForm;
import com.jdc.onestop.directory.model.service.StateService;

@RestController
@RequestMapping("state")
public class StateApi {
	
	@Autowired
	private StateService service;
	
	@GetMapping
	List<StateDto> search(
			@RequestParam Optional<String> region, 
			@RequestParam Optional<String> keyword,
			@RequestParam Optional<Boolean> deleted) {
		return service.search(region, keyword, deleted);
	}
	
	@GetMapping("{id}")
	StateDto findById(@PathVariable int id) {
		return service.findById(id);
	}

	@PostMapping
	StateDto create(
			@Validated @RequestBody StateForm form, BindingResult result) {
		
		if(result.hasErrors()) {
			throw new ServiceDirectoryAppException(result.getFieldErrors());
		}
		
		return service.create(form);
	}
	
	@PutMapping("{id}")
	StateDto update(@PathVariable int id, 
			@RequestBody @Validated StateForm form, BindingResult result) {
		
		if(result.hasErrors()) {
			throw new ServiceDirectoryAppException(result.getFieldErrors());
		}
		
		return service.save(id, form);
	}
}
