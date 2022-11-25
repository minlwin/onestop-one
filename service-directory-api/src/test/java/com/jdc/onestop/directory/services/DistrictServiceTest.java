package com.jdc.onestop.directory.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import com.jdc.onestop.directory.model.dto.form.DistrictForm;
import com.jdc.onestop.directory.model.service.DistrictService;

@SpringBootTest
@Sql({
	"/initialize.sql",
	"/state/data.sql",
	"/district/data.sql"
})
@TestMethodOrder(MethodOrderer.MethodName.class)
public class DistrictServiceTest {
	
	@Autowired
	private DistrictService service;
	
	@ParameterizedTest
	@CsvFileSource(resources = "/district/test_find_by_id.txt", delimiter = '\t')
	void test_find_by_id(int id, int stateId, String stateName, String stateBurmeseName, String name, String burmeseName, boolean deleted) {
		
		var result = service.findById(id);
		
		assertNotNull(result);
		assertEquals(id, result.id());
		assertEquals(name, result.name());
		assertEquals(burmeseName, result.burmeseName());
		assertEquals(deleted, result.deleted());
		
		assertNotNull(result.state());
		assertEquals(stateId, result.state().id());
		assertEquals(stateName, result.state().name());
		assertEquals(stateBurmeseName, result.state().burmeseName());
	}

	@ParameterizedTest
	@ValueSource(ints = {0, 11})
	void test_find_by_id_not_found(int id) {
		
		var exception = assertThrows(ServiceDirectoryAppException.class, () -> service.findById(id));
		
		assertNotNull(exception);
		assertEquals(ErrorType.Business, exception.getType());
		assertEquals(1, exception.getMessages().size());
		assertEquals("There is no district with id %d.".formatted(id), exception.getMessages().get(0));
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/district/test_create.txt", delimiter = '\t')
	void test_create(int id, int stateId, String stateName, String stateBurmeseName, String name, String burmeseName, boolean deleted) {
		var form = new DistrictForm(name, burmeseName, stateId, deleted);
		
		var result = service.create(form);
		
		assertNotNull(result);
		assertEquals(id, result.id());
		assertEquals(name, result.name());
		assertEquals(burmeseName, result.burmeseName());
		assertEquals(deleted, result.deleted());
		
		assertNotNull(result.state());
		assertEquals(stateId, result.state().id());
		assertEquals(stateName, result.state().name());
		assertEquals(stateBurmeseName, result.state().burmeseName());
	}


	@ParameterizedTest
	@CsvFileSource(resources = "/district/test_update.txt", delimiter = '\t')
	void test_update(int id, int stateId, String stateName, String stateBurmeseName, String name, String burmeseName, boolean deleted) {
		var form = new DistrictForm(name, burmeseName, stateId, deleted);
		
		var result = service.update(id, form);
		
		assertNotNull(result);
		assertEquals(id, result.id());
		assertEquals(name, result.name());
		assertEquals(burmeseName, result.burmeseName());
		assertEquals(deleted, result.deleted());
		
		assertNotNull(result.state());
		assertEquals(stateId, result.state().id());
		assertEquals(stateName, result.state().name());
		assertEquals(stateBurmeseName, result.state().burmeseName());
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/district/test_update_no_data.txt", delimiter = '\t')
	void test_update_no_data(int id, int stateId, String stateName, String stateBurmeseName, String name, String burmeseName, boolean deleted) {
		var form = new DistrictForm(name, burmeseName, stateId, deleted);
		
		var exception = assertThrows(ServiceDirectoryAppException.class, 
				() -> service.update(id, form));

		assertNotNull(exception);
		assertEquals(ErrorType.Business, exception.getType());
		assertEquals(1, exception.getMessages().size());
		assertEquals("There is no district with id %d.".formatted(id), exception.getMessages().get(0));
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/district/test_search.txt")
	void test_search(Integer stateId, String keyword, Boolean deleted, int size) {
		
		var result = service.search(
				Optional.ofNullable(stateId), 
				Optional.ofNullable(keyword), 
				Optional.ofNullable(deleted));
		
		assertEquals(size, result.size());
	}
}
