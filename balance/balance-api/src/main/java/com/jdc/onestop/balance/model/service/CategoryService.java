package com.jdc.onestop.balance.model.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.onestop.balance.model.BalanceAppBusinessException;
import com.jdc.onestop.balance.model.BalanceAppValidationException;
import com.jdc.onestop.balance.model.dto.CategoryDto;
import com.jdc.onestop.balance.model.dto.form.CategoryForm;
import com.jdc.onestop.balance.model.entity.Category;
import com.jdc.onestop.balance.model.entity.Category.Type;
import com.jdc.onestop.balance.model.repo.CategoryRepo;

import jakarta.validation.ConstraintViolationException;

@Service
@Transactional
public class CategoryService {

	@Autowired
	private CategoryRepo repo;
	
	@Transactional(readOnly = true)
	public CategoryDto findById(int id) {
		return repo.findById(id)
				.map(CategoryDto::from)
				.orElseThrow(() -> new BalanceAppBusinessException(
						"There is no category with id %d.".formatted(id)));
	}

	public CategoryDto create(@Validated CategoryForm form) {
		return CategoryDto.from(repo.save(form.entity()));
	}

	public CategoryDto update(int id, CategoryForm form) {
		return repo.findById(id)
				.map(entity -> {
					entity.setName(form.name());
					entity.setType(form.type());
					return CategoryDto.from(entity);
				}).orElseThrow(() -> new BalanceAppBusinessException(
						"There is no category with id %d.".formatted(id)));
	}

	public List<CategoryDto> upload(MultipartFile part) {
		
		try(var input = part.getInputStream();
				var reader = new BufferedReader(new InputStreamReader(input))) {

			List<CategoryDto> list = new ArrayList<>();
			String line = null;
			
			while(null != (line = reader.readLine())) {
				var array = line.split(",");
				
				var category = repo.findOneByName(array[0]);
				if(null != category) {
					category.setType(Type.valueOf(array[1]));
					list.add(CategoryDto.from(category));
				} else {
					list.add(create(CategoryForm.from(line)));
				}
					
			}
			
			return list;
			
		} catch (ConstraintViolationException e) {
			throw new BalanceAppValidationException(e.getConstraintViolations()
					.stream().map(a -> a.getMessage()).toList());
		} catch (IOException e) {
			throw new BalanceAppBusinessException("Please check upload file layout.");
		}
	}

	@Transactional(readOnly = true)
	public List<CategoryDto> search(Optional<Type> type, Optional<String> keyword) {
		return repo.findAll(withType(type).and(withName(keyword.filter(StringUtils::hasLength))))
				.stream().map(CategoryDto::from).toList();
	}
	
	private Specification<Category> withType(Optional<Type> type) {
		return type.isEmpty() ? Specification.where(null) : 
			(root, query, cb) -> cb.equal(root.get("type"), type.get());
	}

	private Specification<Category> withName(Optional<String> name) {
		return name.isEmpty() ? Specification.where(null) : 
			(root, query, cb) -> cb.like(cb.lower(root.get("name")), name.get().toLowerCase().concat("%"));
	}
}
