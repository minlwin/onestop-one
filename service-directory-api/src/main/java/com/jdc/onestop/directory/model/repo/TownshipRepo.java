package com.jdc.onestop.directory.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.jdc.onestop.directory.model.entity.Township;

public interface TownshipRepo extends JpaRepository<Township, Integer>, JpaSpecificationExecutor<Township>{

}
