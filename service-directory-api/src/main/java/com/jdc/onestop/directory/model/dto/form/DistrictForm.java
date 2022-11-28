package com.jdc.onestop.directory.model.dto.form;

import java.util.function.Function;

import com.jdc.onestop.directory.model.ServiceDirectoryAppException;
import com.jdc.onestop.directory.model.entity.District;
import com.jdc.onestop.directory.model.entity.State;

public record DistrictForm(
		String name,
		String burmeseName,
		int stateId,
		boolean deleted
		) {

	public District entity(Function<Integer, State> stateFinder) {
		return new District(stateFinder.apply(stateId), name, burmeseName, deleted);
	}

	public static DistrictForm of(int state, String line) {
		var array = line.split("\t");
		
		if(array.length != 4) {
			throw new ServiceDirectoryAppException("Invalid File layout.");
		}
		return new DistrictForm(array[0], array[1], state, Boolean.valueOf(array[2]));
	}

}
