package com.jdc.one.traders.model.service;

import java.util.List;
import java.util.Optional;

import com.jdc.one.traders.model.dto.output.CategoryDto;

public interface CategoryService {

	List<CategoryDto> topCategories(Optional<Integer> limit);

}
