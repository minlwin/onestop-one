package com.jdc.onestop.balance.model.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.jdc.onestop.balance.model.entity.Balance;
import com.jdc.onestop.balance.model.entity.BalanceDetails;

public record BalanceListDto(
	int id,
	CategoryDto category,
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	LocalDate issueAt,
	int totalCount,
	int totalAmount,
	String remark,
	boolean deleted) {

	public static BalanceListDto from(Balance entity) {
		return new BalanceListDto(
				entity.getId(), 
				CategoryDto.from(entity.getCategory()), 
				entity.getIssueAt(), 
				entity.getDetails().stream().mapToInt(BalanceDetails::getQuentity).sum(), 
				entity.getDetails().stream().mapToInt(a -> a.getQuentity() * a.getUnitPrice()).sum(), 
				entity.getRemark(), entity.isDeleted());
	}
}
