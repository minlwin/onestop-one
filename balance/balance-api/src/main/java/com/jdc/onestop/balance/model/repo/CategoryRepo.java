package com.jdc.onestop.balance.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdc.onestop.balance.model.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
