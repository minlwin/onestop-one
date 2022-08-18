package com.jdc.one.traders.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.one.traders.model.dto.output.DivisionDto;
import com.jdc.one.traders.model.dto.output.TownshipDto;
import com.jdc.one.traders.model.service.LocationService;

@RestController
@RequestMapping("location")
public class LocationApi {
	
	@Autowired
	private LocationService service;

	@GetMapping("division")
	List<DivisionDto> divisions() {
		return service.getAllDivisions();
	}
	
	@GetMapping("township")
	List<TownshipDto> townships(@RequestParam int division) {
		return service.findTownships(division);
	}
	
	@GetMapping("township/{id}")
	TownshipDto findById(@PathVariable int id) {
		return service.findTownshipById(id);
	}
}
