package com.jdc.onestop.balance.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.onestop.balance.model.dto.ApiResult;
import com.jdc.onestop.balance.model.dto.BalanceDto;
import com.jdc.onestop.balance.model.dto.BalanceListDto;
import com.jdc.onestop.balance.model.dto.ErrorDto.Type;
import com.jdc.onestop.balance.model.dto.SingleInputForm;
import com.jdc.onestop.balance.model.dto.form.BalanceForm;
import com.jdc.onestop.balance.model.service.BalanceService;

@RestController
@RequestMapping("balance")
public class BalanceApi {
	
	@Autowired
	private BalanceService service;

	@GetMapping
	ApiResult<List<BalanceListDto>> search(
			@RequestParam Optional<Type> type, 
			@RequestParam Optional<Integer> category, 
			@RequestParam Optional<String> keyword) {
		return ApiResult.from(service.search(type, category, keyword));
	}
	
	@GetMapping("{id}")
	ApiResult<BalanceDto> findById(@PathVariable int id) {
		return ApiResult.from(service.findById(id));
	}
	
	@PostMapping
	ApiResult<BalanceDto> create(@RequestBody BalanceForm form, BindingResult result) {
		return ApiResult.from(service.create(form));
	}
	
	@PutMapping("{id}")
	ApiResult<BalanceDto> update(@PathVariable int id, @RequestBody BalanceForm form, BindingResult result) {
		return ApiResult.from(service.update(id, form));
	}
	
	@PutMapping("{id}/fixed")
	ApiResult<BalanceDto> updateFixedStatus(@PathVariable int id, @RequestBody SingleInputForm<Boolean> form, BindingResult result) {
		return ApiResult.from(service.updateFixedStatus(id, form));
	}
	
	@DeleteMapping("{id}")
	ApiResult<BalanceDto> delete(@PathVariable int id, @RequestBody SingleInputForm<Boolean> form, BindingResult result) {
		return ApiResult.from(service.delete(id, form));
	}

	@DeleteMapping("{id}/details")
	ApiResult<BalanceDto> deleteDetails(@PathVariable int id, @RequestBody SingleInputForm<Integer> form, BindingResult result) {
		return ApiResult.from(service.deleteDetails(id, form));
	}
}
