package com.jdc.one.traders.model.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.one.traders.model.dto.output.DivisionDto;
import com.jdc.one.traders.model.dto.output.TownshipDto;
import com.jdc.one.traders.model.repo.DivisionRepo;
import com.jdc.one.traders.model.repo.TownshipRepo;
import com.jdc.one.traders.model.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService {
	
	@Autowired
	private TownshipRepo townships;
	@Autowired
	private DivisionRepo divisions;
	
	@Override
	public List<DivisionDto> getAllDivisions() {
		return divisions.findAll().stream()
				.map(DivisionDto::new).toList();
	}

	@Override
	@Transactional
	public List<TownshipDto> findTownships(int division) {
		return townships.findByDivisionId(division)
				.map(TownshipDto::new).toList();
	}

	@Override
	public TownshipDto findTownshipById(int id) {
		return townships.findById(id)
				.map(TownshipDto::new)
				.orElseThrow(EntityNotFoundException::new);
	}

}
