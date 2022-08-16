package com.jdc.one.traders.model.dto.input;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.jdc.one.traders.model.dto.entity.Profile;
import com.jdc.one.traders.model.dto.entity.Profile.Gender;

public record PersonalInfoDto(
		String greeting,
		String coverImage,
		@DateTimeFormat(pattern = "yyyy-MM-dd")
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
