package com.jdc.onestop.standalone.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.onestop.standalone.model.Course;
import com.jdc.onestop.standalone.model.Course.Level;
import com.jdc.onestop.standalone.model.CourseService;


@RestController
@RequestMapping("course")
public class CourseApi {

	@Autowired
	private CourseService service;
	
	@GetMapping
	List<Course> search(@RequestParam Optional<Level> level, @RequestParam Optional<String> name) {
		return service.search(level, name);
	}
	
	@GetMapping("{id}")
	Course findById(@PathVariable int id) {
		return service.findById(id);
	}
	
	@PostMapping
	Course create(@RequestBody Course course) {
		return service.save(course);
	}
	
	@PutMapping
	Course update(@PathVariable int id, @RequestBody Course course) {
		return service.save(id, course);
	}
}
