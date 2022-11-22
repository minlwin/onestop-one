package com.jdc.onestop.directory.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.jdc.onestop.directory.model.dto.form.StateForm;
import com.jdc.onestop.directory.model.service.StateService;

@SpringBootTest
@Sql({
	"/initialize.sql",
	"/state/state-data.sql"
})
public class StateServiceTest {

	@Autowired
	private StateService service;

	@ParameterizedTest
	@CsvFileSource(resources = "/state/test_create.txt", delimiter = '\t')
	void test_create(int id, String name, String burmeseName, String region, String capital, boolean deleted) {
		
		// Prepare Form
		StateForm form = new StateForm(name, burmeseName, region, capital, deleted);
		
		// Execute Create Method
		var result = service.create(form);
		
		// Check Result
		assertEquals(id, result.id());
		assertEquals(name, result.name());
		assertEquals(burmeseName, result.burmeseName());
		assertEquals(region, result.region());
		assertEquals(capital, result.capital());
		assertEquals(deleted, result.deleted());
		
	}
}
