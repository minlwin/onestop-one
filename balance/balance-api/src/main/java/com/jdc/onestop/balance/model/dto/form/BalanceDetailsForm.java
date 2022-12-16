package com.jdc.onestop.balance.model.dto.form;

import com.jdc.onestop.balance.model.entity.BalanceDetails;

public record BalanceDetailsForm(
		int id,
		String article,
		int unitPrice,
		int quentity,
		boolean deleted
		) {

	public BalanceDetails entity() {
		return new BalanceDetails(article, quentity, unitPrice);
	}

}
