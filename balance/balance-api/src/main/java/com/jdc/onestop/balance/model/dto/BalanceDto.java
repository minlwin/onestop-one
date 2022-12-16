package com.jdc.onestop.balance.model.dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.jdc.onestop.balance.model.entity.Balance;

public record BalanceDto(
		int id,
		CategoryDto category,
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		LocalDate issueAt,
		String remark,
		List<BalanceDetailsDto> details,
		boolean deleted,
		boolean fixed
		) {

	public static BalanceDto from(Balance entity) {
		return new BalanceDto(
				entity.getId(), 
				CategoryDto.from(entity.getCategory()), 
				entity.getIssueAt(),
				entity.getRemark(), 
				entity.getDetails().stream().map(BalanceDetailsDto::from).toList(), 
				entity.isDeleted(), 
				entity.isFixed());
	}
}
