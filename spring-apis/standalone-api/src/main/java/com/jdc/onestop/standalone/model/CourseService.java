package com.jdc.onestop.standalone.model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jdc.onestop.standalone.model.Course.Level;

@Service
@Transactional
public class CourseService {

	@Autowired
	private CourseRepo repo;

	@Transactional(readOnly = true)
	public List<Course> search(Optional<Level> level, Optional<String> name) {
		return repo.findAll(specLevel(level).and(specName(name.filter(StringUtils::hasLength))));
	}
	
	private Specification<Course> specLevel(Optional<Level> level) {
		return level.isEmpty() ? Specification.where(null) :
			(root, query, cb) -> cb.equal(root.get("level"), level.get());
	}

	private Specification<Course> specName(Optional<String> name) {
		return name.isEmpty() ? Specification.where(null) :
			(root, query, cb) -> cb.like(cb.lower(root.get("name")), 
					name.get().toLowerCase().concat("%"));
	}

	public Course save(Course course) {
		return repo.save(course);
	}

	public Course save(int id, Course course) {
		course.setId(id);
		return repo.save(course);
	}

	public Course findById(int id) {
		return repo.findById(id).orElseThrow();
	}
}
