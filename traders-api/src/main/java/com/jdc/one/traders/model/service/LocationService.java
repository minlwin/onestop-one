package com.jdc.one.traders.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.one.traders.model.dto.output.DivisionDto;
import com.jdc.one.traders.model.dto.output.TownshipDto;
import com.jdc.one.traders.model.repo.DivisionRepo;
import com.jdc.one.traders.model.repo.TownshipRepo;

@Service
public class LocationService {
	
	@Autowired
	private TownshipRepo townships;
	@Autowired
	private DivisionRepo divisions;
	
	public List<DivisionDto> getAllDivisions() {
		return divisions.findAll().stream()
				.map(DivisionDto::new).toList();
	}

	@Transactional
	public List<TownshipDto> findTownships(int division) {
		return townships.findByDivisionId(division)
				.map(TownshipDto::new).toList();
	}

}
