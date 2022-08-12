package com.jdc.one.traders.model.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.jdc.one.traders.model.dto.entity.Account;
import com.jdc.one.traders.model.dto.output.TopSellerDto;

public interface AccountRepo extends JpaRepository<Account, Integer>, JpaSpecificationExecutor<Account>{

	@Query("""
		select new com.jdc.one.traders.model.dto.output.TopSellerDto(a.id, a.name, count(s))  
		from Account a join a.product p join p.sale s 
		group by a.id, a.name order by count(s) desc
			""")
	List<TopSellerDto> getTopSeller(Pageable page);

	
}
