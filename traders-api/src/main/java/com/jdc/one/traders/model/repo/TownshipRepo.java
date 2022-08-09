package com.jdc.one.traders.model.repo;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdc.one.traders.model.dto.entity.Township;

public interface TownshipRepo extends JpaRepository<Township, Integer>{

	Stream<Township> findByDivisionId(int division);

}
