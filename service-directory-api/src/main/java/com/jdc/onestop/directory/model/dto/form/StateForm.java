package com.jdc.onestop.directory.model.dto.form;

import javax.validation.constraints.NotBlank;

import com.jdc.onestop.directory.model.entity.State;

public record StateForm(
		@NotBlank(message = "Please enter State name.")
		String name,
		@NotBlank(message = "Please enter burmese name.")
		String burmeseName,
		@NotBlank(message = "Please enter region of state.")
		String region,
		@NotBlank(message = "Please enter capital of state.")
		String capital,
		boolean deleted		
		) {

	public State entity() {
		return new State(name, burmeseName, capital, region, deleted);
	}

}
