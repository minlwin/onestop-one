package com.jdc.onestop.directory.model.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.jdc.onestop.directory.model.entity.District;

public interface DistrictRepo extends JpaRepository<District, Integer>, JpaSpecificationExecutor<District>{

	Optional<District> findOneByNameAndStateId(String disName, int stateId);

}
