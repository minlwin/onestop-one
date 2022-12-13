package com.jdc.onestop.balance.model.dto.form;

import jakarta.validation.constraints.NotBlank;

public record PasswordForm(
		@NotBlank(message = "Please enter old password.")
		String oldPass,
		@NotBlank(message = "Please enter new password.")
		String newPass
		) {

}
