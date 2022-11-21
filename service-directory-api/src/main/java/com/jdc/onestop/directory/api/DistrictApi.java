package com.jdc.onestop.directory.api;

import java.util.List;
import java.util.Optional;

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

import com.jdc.onestop.directory.model.dto.DistrictDto;
import com.jdc.onestop.directory.model.dto.form.DistrictForm;

@RestController
@RequestMapping("district")
public class DistrictApi {

	@GetMapping
	List<DistrictDto> search(
			@RequestParam Optional<Integer> state, 
			@RequestParam Optional<String> name,
			@RequestParam Optional<Boolean> deleted) {
		return null;
	}
	
	@GetMapping("{id}")
	DistrictDto findById(@PathVariable int id) {
		return null;
	}
	
	@PostMapping
	DistrictDto create(@RequestBody @Validated DistrictForm form, BindingResult result) {
		return null;
	}
	
	@PutMapping
	DistrictDto update(int id, DistrictForm form, BindingResult result) {
		return null;
	}
}
