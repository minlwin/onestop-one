package com.jdc.one.traders.model.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.one.traders.model.dto.entity.Category;
import com.jdc.one.traders.model.dto.output.CategoryDto;
import com.jdc.one.traders.model.repo.CategoryRepo;
import com.jdc.one.traders.model.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo repo;

	@Override
	public List<CategoryDto> topCategories(Optional<Integer> limit) {
		return repo.getTopCategory(limit.map(size -> PageRequest.ofSize(size)).orElse(null));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Category getByName(String category) {
		return repo.findOneByName(category).orElseGet(() -> {
			var c = new Category();
			c.setName(category);
			return repo.save(c);
		});
	}
}
