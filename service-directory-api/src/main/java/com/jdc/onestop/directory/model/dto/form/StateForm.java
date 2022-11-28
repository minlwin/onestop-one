package com.jdc.onestop.directory.model.dto.form;

import javax.validation.constraints.NotBlank;

import com.jdc.onestop.directory.model.ServiceDirectoryAppException;
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

	public static StateForm of(String line) {
		var array = line.split("\t");
		
		if(array.length != 5) {
			throw new ServiceDirectoryAppException("Invalid File layout.");
		}
		
		return new StateForm(array[0], array[1], array[2], array[3], Boolean.valueOf(array[4]));
	}

}
