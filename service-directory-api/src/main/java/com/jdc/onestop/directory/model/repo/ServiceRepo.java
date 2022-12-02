package com.jdc.onestop.directory.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdc.onestop.directory.model.entity.Service;

public interface ServiceRepo extends JpaRepository<Service, Integer>{

}
