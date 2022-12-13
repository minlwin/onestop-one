package com.jdc.onestop.balance.model.dto.form;

import com.jdc.onestop.balance.model.BalanceAppBusinessException;
import com.jdc.onestop.balance.model.entity.Category;
import com.jdc.onestop.balance.model.entity.Category.Type;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryForm(
		@NotNull(message = "Please select type.")
		Type type, 
		@NotBlank(message = "Please enter category name.")
		String name) {

	public Category entity() {
		return new Category(type, name);
	}
	
	public static CategoryForm from(String line) {
		var array = line.split(",");
		if(array.length != 2) {
			throw new BalanceAppBusinessException("Please check upload file layout.");
		}
		return new CategoryForm(Type.valueOf(array[0]), array[1]);
	}
}
