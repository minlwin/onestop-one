package com.jdc.onestop.directory.model.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.jdc.onestop.directory.model.entity.Township;

public interface TownshipRepo extends JpaRepository<Township, Integer>, JpaSpecificationExecutor<Township>{

	Optional<Township> findOneByDistrictIdAndName(int id, String string);

	List<Township> findByDistrictStateId(int stateId);

}
