package com.jdc.onestop.standalone.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CourseRepo extends JpaRepository<Course, Integer>, JpaSpecificationExecutor<Course> {

}
