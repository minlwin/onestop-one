package com.jdc.one.traders.model.service;

import java.util.List;
import java.util.Optional;

import com.jdc.one.traders.model.dto.entity.Category;
import com.jdc.one.traders.model.dto.output.CategoryDto;
import com.jdc.one.traders.model.dto.output.SimpleCategory;

public interface CategoryService {

	List<CategoryDto> topCategories(Optional<Integer> limit);

	Category getByName(String category);

	List<SimpleCategory> getSellerCategory(int sellerId);

	List<SimpleCategory> getAll();

	SimpleCategory findById(int id);

}
