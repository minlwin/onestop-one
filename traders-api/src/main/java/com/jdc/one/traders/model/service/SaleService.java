package com.jdc.one.traders.model.service;

import java.util.List;
import java.util.Optional;

import com.jdc.one.traders.model.dto.input.PaidInputDto;
import com.jdc.one.traders.model.dto.input.SaleInputDto;
import com.jdc.one.traders.model.dto.input.SaleStatusInputDto;
import com.jdc.one.traders.model.dto.output.SaleSummary;
import com.jdc.one.traders.model.dto.output.SaleVO;

public interface SaleService {

	List<SaleSummary> search(Optional<String> seller, Optional<String> buyer, Optional<String> status,
			Optional<String> keyword);

	SaleVO findById(long id);

	SaleVO order(SaleInputDto dto);

	SaleVO updateStatus(long id, SaleStatusInputDto dto);

	SaleVO paid(PaidInputDto dto);

}
