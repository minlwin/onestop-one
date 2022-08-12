package com.jdc.one.traders.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.jdc.one.traders.model.dto.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product>{

}
