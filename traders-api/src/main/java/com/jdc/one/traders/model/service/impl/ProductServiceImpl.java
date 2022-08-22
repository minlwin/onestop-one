package com.jdc.one.traders.model.service.impl;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.one.traders.model.dto.entity.Product;
import com.jdc.one.traders.model.dto.input.ProductInputDto;
import com.jdc.one.traders.model.dto.output.ProductDto;
import com.jdc.one.traders.model.repo.AccountRepo;
import com.jdc.one.traders.model.repo.ProductRepo;
import com.jdc.one.traders.model.service.CategoryService;
import com.jdc.one.traders.model.service.PhotoService;
import com.jdc.one.traders.model.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private AccountRepo accountRepo;
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PhotoService photoService;

	@Override
	public List<ProductDto> search(Optional<String> category, Optional<String> seller, Optional<String> keyword) {

		Specification<Product> spec = Specification.where(null);

		var catWhere = category
				.filter(c -> StringUtils.hasLength(c))
				.map(Integer::parseInt)
				.filter(c -> c > 0)
				.map(c -> {
			Specification<Product> where = (root, query, builder) -> builder.equal(root.get("category").get("id"), c);
			return where;
		}).orElse(Specification.where(null));

		spec = spec.and(catWhere);

		var sellerWhere = seller
				.filter(c -> StringUtils.hasLength(c))
				.map(Integer::parseInt)
				.filter(c -> c > 0)
				.map(c -> {
			Specification<Product> where = (root, query, builder) -> builder.equal(root.get("seller").get("id"), c);
			return where;
		}).orElse(Specification.where(null));

		spec = spec.and(sellerWhere);

		var keywordWhere = keyword.filter(StringUtils::hasLength).map(c -> {
			Specification<Product> where = (root, query, builder) -> builder.like(builder.lower(root.get("name")),
					c.toLowerCase().concat("%"));
			return where;
		}).orElse(Specification.where(null));

		spec = spec.and(keywordWhere);

		return productRepo.findAll(spec).stream().map(ProductDto::new).toList();
	}

	@Override
	@Transactional
	public ProductDto save(ProductInputDto dto) {

		return productRepo.findById(dto.id()).or(() -> {
			var p = new Product();
			p.setSeller(accountRepo.getReferenceById(dto.sellerId()));
			return Optional.of(productRepo.save(p));
		}).map(p -> {
			p.setName(dto.name());
			p.setCondition(dto.condition());
			p.setPrice(dto.price());
			p.setCategory(categoryService.getByName(dto.category()));
			p.setFeatures(dto.features());
			p.setPhotos(dto.photos());
			return p;
		}).map(ProductDto::new).orElseThrow();

	}

	@Override
	@Transactional
	public ProductDto uploadPhoto(int id, MultipartFile[] files) {
		return productRepo.findById(id).map(p -> {
			var photos = photoService.create(Path.of("products", "prod-%d".formatted(id)), "prod", files).toList();
			p.setPhotos(photos);
			return p;
		}).map(ProductDto::new).orElseThrow();
	}

	@Override
	public ProductDto findById(int id) {
		return productRepo.findById(id).map(ProductDto::new).orElseThrow();
	}

}
