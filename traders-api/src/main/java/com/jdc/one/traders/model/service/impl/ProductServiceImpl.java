package com.jdc.one.traders.model.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jdc.one.traders.model.dto.entity.Product;
import com.jdc.one.traders.model.dto.output.ProductDto;
import com.jdc.one.traders.model.repo.ProductRepo;
import com.jdc.one.traders.model.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepo repo;

	@Override
	public List<ProductDto> search(Optional<Integer> category, Optional<Integer> seller, Optional<String> keyword) {
		
		Specification<Product> spec = Specification.where(null);
		
		var catWhere = category.filter(c -> c > 0).map(c -> {
			Specification<Product> where = (root, query, builder) -> builder.equal(root.get("category").get("id"), c);
			return where;
		}).orElse(Specification.where(null));
		
		spec = spec.and(catWhere);
		
		var sellerWhere = seller.filter(c -> c > 0).map(c -> {
			Specification<Product> where = (root, query, builder) -> builder.equal(root.get("seller").get("id"), c);
			return where;
		}).orElse(Specification.where(null));
		
		spec = spec.and(sellerWhere);
		
		var keywordWhere = keyword.filter(StringUtils::hasLength).map(c -> {
			Specification<Product> where = (root, query, builder) -> 
				builder.like(builder.lower(root.get("name")), c.toLowerCase().concat("%"));
			return where;
		}).orElse(Specification.where(null));
		
		spec = spec.and(keywordWhere);
		
				
		return repo.findAll(spec).stream().map(ProductDto::new).toList();
	}

}
