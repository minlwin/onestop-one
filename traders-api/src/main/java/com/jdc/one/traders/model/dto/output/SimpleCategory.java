package com.jdc.one.traders.model.dto.output;

import com.jdc.one.traders.model.dto.entity.Category;

public record SimpleCategory(
		int id,
		String name
		) {
	
	public SimpleCategory(Category c) {
		this(c.getId(), c.getName());
	}
}
