package com.jdc.onestop.directory.model.dto.form;

import java.util.function.Function;

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
}
