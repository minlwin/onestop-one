package com.jdc.onestop.directory.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

import com.jdc.onestop.directory.model.dto.ErrorResult;
import com.jdc.onestop.directory.model.dto.ErrorResult.ErrorType;
import com.jdc.onestop.directory.model.dto.StateDto;
import com.jdc.onestop.directory.model.dto.form.StateForm;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql({
	"/initialize.sql",
	"/state/data.sql"
})
public class StateApiTest {

	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate template;
	
	@Disabled
	@ParameterizedTest
	@CsvFileSource(resources = "/state/test_find_by_id.txt", delimiter = '\t')
	void test_find_by_id(int id, String name, String burmeseName, String region, String capital, boolean deleted) {
		
		var result = template.getForObject("%s/%d".formatted(url(), id), StateDto.class);

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
		var result = template.getForObject("%s/%d".formatted(url(), id), ErrorResult.class);
		
		assertEquals(ErrorType.Business, result.type());
		assertEquals(1, result.messages().size());
		assertEquals("There is no state with id %d.".formatted(id), result.messages().get(0));
	}
	
	
	@Disabled
	@ParameterizedTest
	@CsvFileSource(resources = "/state/test_search.txt")
	void test_search(String region, String keyword, Boolean deleted, int size) {
		
		var builder = UriComponentsBuilder.fromHttpUrl(url());
		
		if(StringUtils.hasLength(region)) {
			builder.queryParam("region", region);
		}
		
		if(StringUtils.hasLength(keyword)) {
			builder.queryParam("keyword", keyword);
		}
		
		if(null != deleted) {
			builder.queryParam("deleted", deleted);
		}
		
		var result = template.getForObject(builder.build().encode().toUri(), List.class);
		
		assertEquals(size, result.size());

	}	
	
	@Disabled
	@ParameterizedTest
	@CsvFileSource(resources = "/state/test_create.txt", delimiter = '\t')
	void test_create(int id, String name, String burmeseName, String region, String capital, boolean deleted) {
		
		// Prepare Form
		var form = new StateForm(name, burmeseName, region, capital, deleted);
		
		// Execute Create Method
		var result = template.postForObject(url(), form, StateDto.class);
		
		// Check Result
		assertEquals(id, result.id());
		assertEquals(name, result.name());
		assertEquals(burmeseName, result.burmeseName());
		assertEquals(region, result.region());
		assertEquals(capital, result.capital());
		assertEquals(deleted, result.deleted());
		
	}
	
	@Disabled
	@ParameterizedTest
	@CsvFileSource(resources = "/state/test_update.txt", delimiter = '\t')
	void test_update(int id, String name, String burmeseName, String region, String capital, boolean deleted) {
		
		// Prepare Form
		var form = new StateForm(name, burmeseName, region, capital, deleted);

		var result = template.exchange("%s/%d".formatted(url(), id), HttpMethod.PUT, new HttpEntity<>(form), StateDto.class).getBody();

		// Check Result
		assertEquals(id, result.id());
		assertEquals(name, result.name());
		assertEquals(burmeseName, result.burmeseName());
		assertEquals(region, result.region());
		assertEquals(capital, result.capital());
		assertEquals(deleted, result.deleted());

	}	
	
	
	private String url() {
		return "http://localhost:%d/state".formatted(port);
	}
}
