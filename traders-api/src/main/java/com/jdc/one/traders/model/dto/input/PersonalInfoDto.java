package com.jdc.one.traders.model.dto.input;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jdc.one.traders.model.dto.entity.Profile;
import com.jdc.one.traders.model.dto.entity.Profile.Gender;

public record PersonalInfoDto(
		String greeting,
		String coverImage,
		@JsonFormat(pattern = "yyyy-MM-dd")
		LocalDate dateOfBirth,
		Gender gender
		) {
	
	public static PersonalInfoDto from(Profile entity) {
		return new PersonalInfoDto(
				entity.getGreeting(), 
				entity.getCoverImage(), 
				entity.getDateOfBirth(), 
				entity.getGender());
	}

}
