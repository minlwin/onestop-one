package com.jdc.one.traders.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdc.one.traders.model.dto.entity.Sale;

public interface SaleRepo extends JpaRepository<Sale, Long>{

}
