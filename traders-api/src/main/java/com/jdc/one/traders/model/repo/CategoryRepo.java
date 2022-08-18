package com.jdc.one.traders.model.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.jdc.one.traders.model.dto.entity.Category;
import com.jdc.one.traders.model.dto.output.CategoryDto;

public interface CategoryRepo extends JpaRepository<Category, Integer>, JpaSpecificationExecutor<Category>{

	@Query("""
			select new com.jdc.one.traders.model.dto.output.CategoryDto(c.id, c.name, count(p.id)) 
			from Category c join c.product p 
			group by c.id, c.name 
			order by count(p.id) desc
			""")
	List<CategoryDto> getTopCategory(Pageable page);

	Optional<Category> findOneByName(String category);
	
	
}
