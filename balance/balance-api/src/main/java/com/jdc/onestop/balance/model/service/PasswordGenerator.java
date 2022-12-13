package com.jdc.onestop.balance.model.service;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

@Service
public class PasswordGenerator {

	public String generate() {
		Long code = ThreadLocalRandom.current()
				.longs(100_000, 999_999).limit(1).findAny().getAsLong();
		return code.toString();
	}
}
