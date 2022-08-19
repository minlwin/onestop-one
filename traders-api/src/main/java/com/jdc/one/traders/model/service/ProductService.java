package com.jdc.one.traders.model.service;

import java.util.List;
import java.util.Optional;

import com.jdc.one.traders.model.dto.input.ProductInputDto;
import com.jdc.one.traders.model.dto.output.ProductDto;

public interface ProductService {

	List<ProductDto> search(Optional<String> category, Optional<String> seller, Optional<String> keyword);

	ProductDto save(ProductInputDto dto);

}
