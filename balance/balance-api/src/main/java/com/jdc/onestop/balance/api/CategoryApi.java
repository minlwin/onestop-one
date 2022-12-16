package com.jdc.onestop.balance.api;

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
import org.springframework.web.multipart.MultipartFile;

import com.jdc.onestop.balance.model.dto.ApiResult;
import com.jdc.onestop.balance.model.dto.CategoryDto;
import com.jdc.onestop.balance.model.dto.form.CategoryForm;
import com.jdc.onestop.balance.model.entity.Category.Type;
import com.jdc.onestop.balance.model.service.CategoryService;

@RestController
@RequestMapping("category")
public class CategoryApi {
	
	@Autowired
	private CategoryService service;

	@GetMapping
	ApiResult<List<CategoryDto>> search(@RequestParam Optional<Type> type, @RequestParam Optional<String> keyword) {
		return ApiResult.from(service.search(type, keyword));
	}
	
	@GetMapping("{id}")
	ApiResult<CategoryDto> findById(@PathVariable int id) {
		return ApiResult.from(service.findById(id));
	}
	
	@PostMapping
	ApiResult<CategoryDto> create(
			@Validated @RequestBody CategoryForm form, BindingResult result) {
		return ApiResult.from(service.create(form));
	}
	
	@PutMapping("{id}")
	ApiResult<CategoryDto> update(@PathVariable int id, 
			@Validated @RequestBody CategoryForm form, BindingResult result) {
		return ApiResult.from(service.update(id, form));
	}
	
	
	@PostMapping("upload")
	ApiResult<List<CategoryDto>> upload(@RequestParam MultipartFile part) {
		return ApiResult.from(service.upload(part));
	}
}
