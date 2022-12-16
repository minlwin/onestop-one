package com.jdc.onestop.balance.model.dto.form;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

import com.jdc.onestop.balance.model.entity.Account;
import com.jdc.onestop.balance.model.entity.Balance;
import com.jdc.onestop.balance.model.entity.Category;

public record BalanceForm(
		int categoryId,
		LocalDate issueAt,
		String remark,
		List<BalanceDetailsForm> details
		) {

	public Balance entity(Function<Integer, Category> categoryFinder, Account account) {
		return new Balance(issueAt, categoryFinder.apply(categoryId), account, remark);
	}

}
