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

import com.jdc.onestop.directory.model.dto.StateDto;
import com.jdc.onestop.directory.model.dto.form.StateForm;

@RestController
@RequestMapping("state")
public class StateApi {
	
	@GetMapping
	List<StateDto> search(
			@RequestParam Optional<String> regision, 
			@RequestParam Optional<String> keyword,
			@RequestParam Optional<Boolean> deleted) {
		return null;
	}
	
	@GetMapping("{id}")
	StateDto findById(@PathVariable int id) {
		return null;
	}

	@PostMapping
	StateDto create(
			@Validated @RequestBody StateForm form, BindingResult result) {
		return null;
	}
	
	@PutMapping("{id}")
	StateDto update(@PathVariable int id, 
			@RequestBody @Validated StateForm form, BindingResult result) {
		return null;
	}
}
