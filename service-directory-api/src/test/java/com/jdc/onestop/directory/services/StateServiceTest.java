package com.jdc.onestop.directory.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.jdc.onestop.directory.model.ServiceDirectoryAppException;
import com.jdc.onestop.directory.model.dto.ErrorResult.ErrorType;
import com.jdc.onestop.directory.model.dto.form.StateForm;
import com.jdc.onestop.directory.model.service.StateService;

@SpringBootTest
@Sql({
	"/initialize.sql",
	"/state/data.sql"
})
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class StateServiceTest {

	@Autowired
	private StateService service;

	@ParameterizedTest
	@CsvFileSource(resources = "/state/test_create.txt", delimiter = '\t')
	void test_create(int id, String name, String burmeseName, String region, String capital, boolean deleted) {
		
		// Prepare Form
		var form = new StateForm(name, burmeseName, region, capital, deleted);
		
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
	
	@ParameterizedTest
	@CsvFileSource(resources = "/state/test_update.txt", delimiter = '\t')
	void test_update(int id, String name, String burmeseName, String region, String capital, boolean deleted) {
		
		// Prepare Form
		var form = new StateForm(name, burmeseName, region, capital, deleted);
		
		// Execute Create Method
		var result = service.save(id, form);
		
		// Check Result
		assertEquals(id, result.id());
		assertEquals(name, result.name());
		assertEquals(burmeseName, result.burmeseName());
		assertEquals(region, result.region());
		assertEquals(capital, result.capital());
		assertEquals(deleted, result.deleted());
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/state/test_update_no_data.txt", delimiter = '\t')
	void test_update_no_data(int id, String name, String burmeseName, String region, String capital, boolean deleted) {
		// Prepare Form
		var form = new StateForm(name, burmeseName, region, capital, deleted);
		
		var exception = assertThrows(ServiceDirectoryAppException.class, () -> service.save(id, form));
		
		assertEquals(ErrorType.Business, exception.getType());
		
		var messages = exception.getMessages();

		assertEquals(1, messages.size());
		assertEquals("There is no state with id %d.".formatted(id), messages.get(0));
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/state/test_find_by_id.txt", delimiter = '\t')
	void test_find_by_id(int id, String name, String burmeseName, String region, String capital, boolean deleted) {
		
		var result = service.findById(id);
		
		// Check Result
		assertEquals(id, result.id());
		assertEquals(name, result.name());
		assertEquals(burmeseName, result.burmeseName());
		assertEquals(region, result.region());
		assertEquals(capital, result.capital());
		assertEquals(deleted, result.deleted());
	}
	
	@ParameterizedTest
	@ValueSource(ints = {6, 0, 10})
	void test_find_by_id_not_found(int id) {
		
		var exception = assertThrows(ServiceDirectoryAppException.class, () -> service.findById(id));
		
		assertEquals(ErrorType.Business, exception.getType());
		
		var messages = exception.getMessages();

		assertEquals(1, messages.size());
		assertEquals("There is no state with id %d.".formatted(id), messages.get(0));
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/state/test_search.txt")
	void test_search(String region, String keyword, Boolean deleted, int size) {
		
		var result = service.search(Optional.ofNullable(region), Optional.ofNullable(keyword), Optional.ofNullable(deleted));
		assertEquals(size, result.size());
		
	}
}
