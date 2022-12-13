package com.jdc.onestop.balance.api;

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
import org.springframework.web.multipart.MultipartFile;

import com.jdc.onestop.balance.model.dto.ApiResult;
import com.jdc.onestop.balance.model.dto.CategoryDto;
import com.jdc.onestop.balance.model.dto.form.CategoryForm;
import com.jdc.onestop.balance.model.entity.Category.Type;

@RestController
@RequestMapping("category")
public class CategoryApi {

	@GetMapping
	ApiResult<List<CategoryDto>> search(Optional<Type> type, Optional<String> keyword) {
		return null;
	}
	
	@GetMapping("{id}")
	ApiResult<CategoryDto> findById(@PathVariable int id) {
		return null;
	}
	
	@PostMapping
	ApiResult<CategoryDto> create(
			@Validated @RequestBody CategoryForm form, BindingResult result) {
		return null;
	}
	
	@PutMapping("{id}")
	ApiResult<CategoryDto> update(@PathVariable int id, 
			@Validated @RequestBody CategoryForm form, BindingResult result) {
		return null;
	}
	
	
	@PostMapping("upload")
	ApiResult<List<CategoryDto>> upload(@RequestParam MultipartFile part) {
		return null;
	}
}
