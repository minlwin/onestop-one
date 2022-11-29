package com.jdc.onestop.directory.model.dto.form;

import java.util.function.Function;

import com.jdc.onestop.directory.model.ServiceDirectoryAppException;
import com.jdc.onestop.directory.model.entity.District;
import com.jdc.onestop.directory.model.entity.Township;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record TownshipForm(
		@NotBlank(message = "Please enter township name.")
		String name,
		@NotBlank(message = "Please enter burmese name.")
		String burmeseName,
		@Min(value = 1, message = "Please select district.")
		int districtId,
		boolean deleted
		) {

	public Township entity(Function<Integer, District> function) {
		return new Township(name, burmeseName, function.apply(districtId), deleted);
	}

	public static TownshipForm of(int district, String line) {
		var array = line.split("\t");
		
		if(array.length != 3) {
			throw new ServiceDirectoryAppException("Invalid File layout.");
		}
		
		return new TownshipForm(array[0], array[1], district, Boolean.valueOf(array[2]));
	}
}
