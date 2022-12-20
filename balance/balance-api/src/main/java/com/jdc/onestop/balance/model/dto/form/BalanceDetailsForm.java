package com.jdc.onestop.balance.model.dto.form;

import com.jdc.onestop.balance.model.entity.BalanceDetails;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record BalanceDetailsForm(
		int id,
		@NotEmpty(message = "Please enter article.")
		String article,
		@NotNull(message = "Please enter unit price.")
		@Min(value = 1, message = "Please enter unit price.")
		Integer unitPrice,
		@NotNull(message = "Please enter quentity.")
		@Min(value = 1, message = "Please enter quentity.")
		Integer quentity,
		boolean deleted
		) {

	public BalanceDetails entity() {
		return new BalanceDetails(article, quentity, unitPrice);
	}

}
