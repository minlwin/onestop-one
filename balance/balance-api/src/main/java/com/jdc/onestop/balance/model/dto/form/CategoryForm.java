package com.jdc.onestop.balance.model.dto.form;

import com.jdc.onestop.balance.model.entity.Category.Type;

public record CategoryForm(
		Type type, 
		String name) {

}
