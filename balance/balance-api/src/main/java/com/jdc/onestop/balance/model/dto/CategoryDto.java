package com.jdc.onestop.balance.model.dto;

import com.jdc.onestop.balance.model.entity.Category;
import com.jdc.onestop.balance.model.entity.Category.Type;

public record CategoryDto(
		int id,
		String name,
		Type type
		) {

	public static CategoryDto from(Category entity) {
		return new CategoryDto(entity.getId(), entity.getName(), entity.getType());
	}	
}
