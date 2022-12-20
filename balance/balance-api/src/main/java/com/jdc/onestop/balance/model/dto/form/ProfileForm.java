package com.jdc.onestop.balance.model.dto.form;

import jakarta.validation.constraints.NotBlank;

public record ProfileForm(
		@NotBlank(message = "Please enter member name.")
		String name,
		@NotBlank(message = "Please enter phone number.")
		String phone) {

}
