package com.jdc.onestop.directory.api;

import java.util.List;
import java.util.Optional;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.onestop.directory.model.dto.TownshipDto;
import com.jdc.onestop.directory.model.dto.form.TownshipForm;

@RestController
@RequestMapping("township")
public class TownshipApi {

	@GetMapping
	List<TownshipDto> search(
			@RequestParam Optional<Integer> district,
			@RequestParam Optional<String> keyword,
			@RequestParam Optional<Boolean> deleted) {
		return null;
	}
	
	@GetMapping("{id}")
	TownshipDto findById(@PathVariable int id) {
		return null;
	}
	
	@PostMapping
	TownshipDto create(@RequestBody @Validated TownshipForm form, BindingResult result) {
		return null;
	}

	@PostMapping("{id}")
	TownshipDto update(@PathVariable int id, @RequestBody @Validated TownshipForm form, BindingResult result) {
		return null;
	}
}
