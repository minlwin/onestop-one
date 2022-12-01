package com.jdc.onestop.directory.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.jdc.onestop.directory.model.ServiceDirectoryAppException;
import com.jdc.onestop.directory.model.dto.ErrorResult.ErrorType;
import com.jdc.onestop.directory.model.dto.form.TownshipForm;
import com.jdc.onestop.directory.model.service.TownshipService;

@SpringBootTest
@Sql({
	"/initialize.sql",
	"/state/data.sql",
	"/district/data.sql",
	"/township/data.sql"
})
public class TownshipServiceTest {

	@Autowired
	private TownshipService service;
	
	@ParameterizedTest
	@CsvFileSource(resources = "/township/test_find_by_id.txt", delimiter = '\t')
	void test_find_by_id(int id, int districtId, String districtName, String districtBurmese, int stateId, String stateName, String stateBurmese, String name, String burmese, boolean deleted) {
		var result = service.findById(id);
		
		assertNotNull(result);
		assertEquals(name, result.name());
		assertEquals(burmese, result.burmeseName());
		assertEquals(deleted, result.deleted());
		
		var district = result.district();
		assertNotNull(district);
		assertEquals(districtId, district.id());
		assertEquals(districtName, district.name());
		assertEquals(districtBurmese, district.burmeseName());
		
		var state = district.state();
		assertNotNull(state);
		assertEquals(stateId, state.id());
		assertEquals(stateName, state.name());
		assertEquals(stateBurmese, state.burmeseName());
	}
	
	@ParameterizedTest
	@ValueSource(ints = { 0, 12})
	void test_find_by_id_not_found(int id) {
		var exception = assertThrows(ServiceDirectoryAppException.class, () -> service.findById(id));
		
		assertNotNull(exception);
		assertEquals(ErrorType.Business, exception.getType());
		assertEquals(1, exception.getMessages().size());
		assertEquals("There is no township with id %d.".formatted(id), exception.getMessages().get(0));
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/township/test_create.txt", delimiter = '\t')
	void test_create(int id, int districtId, String districtName, String districtBurmese, int stateId, String stateName, String stateBurmese, String name, String burmese, boolean deleted) {
		
		var form = new TownshipForm(name, burmese, districtId, deleted);
		
		var result = service.create(form);
		
		assertNotNull(result);
		assertEquals(name, result.name());
		assertEquals(burmese, result.burmeseName());
		assertEquals(deleted, result.deleted());
		
		var district = result.district();
		assertNotNull(district);
		assertEquals(districtId, district.id());
		assertEquals(districtName, district.name());
		assertEquals(districtBurmese, district.burmeseName());
		
		var state = district.state();
		assertNotNull(state);
		assertEquals(stateId, state.id());
		assertEquals(stateName, state.name());
		assertEquals(stateBurmese, state.burmeseName());
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/township/test_update.txt", delimiter = '\t')
	void test_update(int id, int districtId, String districtName, String districtBurmese, int stateId, String stateName, String stateBurmese, String name, String burmese, boolean deleted) {
		var form = new TownshipForm(name, burmese, districtId, deleted);
		
		var result = service.update(id, form);
		
		assertNotNull(result);
		assertEquals(name, result.name());
		assertEquals(burmese, result.burmeseName());
		assertEquals(deleted, result.deleted());
		
		var district = result.district();
		assertNotNull(district);
		assertEquals(districtId, district.id());
		assertEquals(districtName, district.name());
		assertEquals(districtBurmese, district.burmeseName());
		
		var state = district.state();
		assertNotNull(state);
		assertEquals(stateId, state.id());
		assertEquals(stateName, state.name());
		assertEquals(stateBurmese, state.burmeseName());
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/township/test_update_no_data.txt", delimiter = '\t')
	void test_update_no_data(int id, int districtId, String name, String burmese, boolean deleted) {
		
		var form = new TownshipForm(name, burmese, id, deleted);
		var exception = assertThrows(ServiceDirectoryAppException.class, 
				() -> service.update(id, form));

		assertNotNull(exception);
		assertEquals(ErrorType.Business, exception.getType());
		assertEquals(1, exception.getMessages().size());
		assertEquals("There is no township with id %d.".formatted(id), exception.getMessages().get(0));
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/township/test_search.txt", delimiter = '\t')
	void test_search(Integer district, String keyword, Boolean deleted, int size) {
		var result = service.search(Optional.ofNullable(district), Optional.empty(), Optional.ofNullable(keyword), Optional.ofNullable(deleted));
		assertEquals(size, result.size());
	}
	
}
