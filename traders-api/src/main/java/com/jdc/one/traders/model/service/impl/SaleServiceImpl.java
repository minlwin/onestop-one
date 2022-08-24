package com.jdc.one.traders.model.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jdc.one.traders.model.dto.output.SaleSummary;
import com.jdc.one.traders.model.service.SaleService;

@Service
public class SaleServiceImpl implements SaleService{

	@Override
	public List<SaleSummary> search(Optional<String> seller, Optional<String> buyer, Optional<String> status,
			Optional<String> keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
