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
import com.jdc.onestop.directory.model.dto.DistrictDto;
import com.jdc.onestop.directory.model.dto.form.DistrictForm;
import com.jdc.onestop.directory.model.service.DistrictService;

@RestController
@RequestMapping("district")
public class DistrictApi {
	
	@Autowired
	private DistrictService service;

	@GetMapping
	List<DistrictDto> search(
			@RequestParam Optional<Integer> state, 
			@RequestParam Optional<String> name,
			@RequestParam Optional<Boolean> deleted) {
		return service.search(state, name, deleted);
	}
	
	@GetMapping("{id}")
	DistrictDto findById(@PathVariable int id) {
		return service.findById(id);
	}
	
	@PostMapping
	DistrictDto create(@RequestBody @Validated DistrictForm form, BindingResult result) {
		
		if(result.hasErrors()) {
			throw new ServiceDirectoryAppException(result.getFieldErrors());
		}
		
		return service.create(form);
	}
	
	@PutMapping("{id}")
	DistrictDto update(@PathVariable int id, @RequestBody DistrictForm form, BindingResult result) {
		
		if(result.hasErrors()) {
			throw new ServiceDirectoryAppException(result.getFieldErrors());
		}

		return service.update(id, form);
	}
}
