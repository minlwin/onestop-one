package com.jdc.onestop.directory.model.dto.form;

import java.util.function.Function;

import com.jdc.onestop.directory.model.ServiceDirectoryAppException;
import com.jdc.onestop.directory.model.entity.District;
import com.jdc.onestop.directory.model.entity.Township;

public record TownshipForm(
		String name,
		String burmeseName,
		int distictId,
		boolean deleted
		) {

	public Township entity(Function<Integer, District> function) {
		return new Township(name, burmeseName, function.apply(distictId), deleted);
	}

	public static TownshipForm of(int district, String line) {
		var array = line.split("\t");
		
		if(array.length != 4) {
			throw new ServiceDirectoryAppException("Invalid File layout.");
		}
		
		return new TownshipForm(array[0], array[1], district, Boolean.valueOf(array[2]));
	}
}
