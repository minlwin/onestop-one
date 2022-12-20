package com.jdc.onestop.balance.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.onestop.balance.model.dto.ApiResult;
import com.jdc.onestop.balance.model.dto.BalanceDto;
import com.jdc.onestop.balance.model.dto.BalanceListDto;
import com.jdc.onestop.balance.model.dto.SingleInputForm;
import com.jdc.onestop.balance.model.dto.form.BalanceForm;
import com.jdc.onestop.balance.model.entity.Category.Type;
import com.jdc.onestop.balance.model.service.BalanceService;

@RestController
@RequestMapping("balance")
public class BalanceApi {
	
	@Autowired
	private BalanceService service;

	@GetMapping
	@PreAuthorize("#username == authentication.name")
	ApiResult<List<BalanceListDto>> search(
			@RequestHeader("balance-user") String username, 
			@RequestParam Optional<Type> type, 
			@RequestParam Optional<Integer> category, 
			@RequestParam Optional<String> keyword,
			@RequestParam Optional<LocalDate> from, 
			@RequestParam Optional<LocalDate> to 
			) {
		return ApiResult.from(service.search(username, type, category, keyword, from, to));
	}
	
	@GetMapping("{id}")
	@PreAuthorize("#username == authentication.name")
	ApiResult<BalanceDto> findById(@RequestHeader("balance-user") String username, 
			@PathVariable int id) {
		return ApiResult.from(service.findById(id));
	}
	
	@PostMapping
	@PreAuthorize("#username == authentication.name")
	ApiResult<BalanceDto> create(
			@RequestHeader("balance-user") String username, 
			@RequestBody BalanceForm form, BindingResult result) {
		return ApiResult.from(service.create(form));
	}
	
	@PutMapping("{id}")
	@PreAuthorize("#username == authentication.name")
	ApiResult<BalanceDto> update(
			@RequestHeader("balance-user") String username, 
			@PathVariable int id, @RequestBody BalanceForm form, BindingResult result) {
		return ApiResult.from(service.update(id, form));
	}
	
	@PutMapping("{id}/fixed")
	@PreAuthorize("#username == authentication.name")
	ApiResult<BalanceDto> updateFixedStatus(@RequestHeader("balance-user") String username, 
			@PathVariable int id, @RequestBody SingleInputForm<Boolean> form, BindingResult result) {
		return ApiResult.from(service.updateFixedStatus(id, form));
	}
	
	@PutMapping("{id}/deleted")
	@PreAuthorize("#username == authentication.name")
	ApiResult<BalanceDto> updateDeleteFlug(
			@RequestHeader("balance-user") String username, 
			@PathVariable int id, @RequestBody SingleInputForm<Boolean> form, BindingResult result) {
		return ApiResult.from(service.delete(id, form));
	}

	@DeleteMapping("{id}/details")
	@PreAuthorize("#username == authentication.name")
	ApiResult<BalanceDto> deleteDetails(
			@RequestHeader("balance-user") String username, 
			@PathVariable int id, @RequestBody SingleInputForm<Integer> form, BindingResult result) {
		return ApiResult.from(service.deleteDetails(id, form));
	}
}
