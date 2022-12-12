package com.jdc.onestop.balance.model.dto;

import com.jdc.onestop.balance.model.entity.Category.Type;

public record CategoryDto(
		int id,
		String name,
		Type type
		) {

}
