package com.jdc.one.traders.model.service;

import java.util.List;

import com.jdc.one.traders.model.dto.output.DivisionDto;
import com.jdc.one.traders.model.dto.output.TownshipDto;

public interface LocationService {

	List<DivisionDto> getAllDivisions();

	List<TownshipDto> findTownships(int division);

	TownshipDto findTownshipById(int id);

}