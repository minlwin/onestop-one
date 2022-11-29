package com.jdc.onestop.directory.model.dto.form;

import java.util.function.Function;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.jdc.onestop.directory.model.ServiceDirectoryAppException;
import com.jdc.onestop.directory.model.entity.District;
import com.jdc.onestop.directory.model.entity.State;

public record DistrictForm(
		@NotEmpty(message = "Please enter burmese name.")
		String name,
		@NotEmpty(message = "Please enter burmese name.")
		String burmeseName,
		@Min(value = 1, message = "Please select state.")
		int stateId,
		boolean deleted
		) {

	public District entity(Function<Integer, State> stateFinder) {
		return new District(stateFinder.apply(stateId), name, burmeseName, deleted);
	}

	public static DistrictForm of(int state, String line) {
		var array = line.split("\t");
		
		if(array.length != 3) {
			throw new ServiceDirectoryAppException("Invalid File layout.");
		}
		return new DistrictForm(array[0], array[1], state, Boolean.valueOf(array[2]));
	}

}
