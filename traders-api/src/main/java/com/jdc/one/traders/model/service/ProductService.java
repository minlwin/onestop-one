package com.jdc.one.traders.model.service;

import java.util.List;
import java.util.Optional;

import com.jdc.one.traders.model.dto.output.ProductDto;

public interface ProductService {

	List<ProductDto> search(Optional<Integer> category, Optional<Integer> seller, Optional<String> keyword);

}
