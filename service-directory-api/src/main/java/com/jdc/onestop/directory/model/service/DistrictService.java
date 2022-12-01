package com.jdc.onestop.directory.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jdc.onestop.directory.model.ServiceDirectoryAppException;
import com.jdc.onestop.directory.model.dto.DistrictDto;
import com.jdc.onestop.directory.model.dto.UploadResult;
import com.jdc.onestop.directory.model.dto.form.DistrictForm;
import com.jdc.onestop.directory.model.entity.District;
import com.jdc.onestop.directory.model.repo.DistrictRepo;
import com.jdc.onestop.directory.model.repo.StateRepo;

import jakarta.validation.Valid;

@Service
@Transactional
public class DistrictService {
	
	@Autowired
	private StateRepo stateRepo;
	@Autowired
	private DistrictRepo districtRepo;

	public DistrictDto create(@Valid DistrictForm form) {
		var entity = form.entity(stateRepo::getReferenceById);
		return DistrictDto.from(districtRepo.save(entity));
	}

	public DistrictDto update(int id, @Valid DistrictForm form) {
		return districtRepo.findById(id)
				.map(entity -> {
					entity.setName(form.name());
					entity.setBurmeseName(form.burmeseName());
					entity.setState(stateRepo.getReferenceById(form.stateId()));
					entity.setDeleted(form.deleted());
					return entity;
				})
				.map(DistrictDto::from)
				.orElseThrow(() -> new ServiceDirectoryAppException("There is no district with id %d.".formatted(id)));
	}

	public UploadResult upload(int state, List<String> lines) {
		
		for(var line : lines) {
			create(DistrictForm.of(state, line));
		}
		
		return new UploadResult(true, "%d districts has been uploaded for %s.".formatted(
				lines.size(), stateRepo.getReferenceById(state).getName()));
	}

	@Transactional(readOnly = true)
	public DistrictDto findById(int id) {
		return districtRepo.findById(id)
				.map(DistrictDto::from)
				.orElseThrow(() -> new ServiceDirectoryAppException("There is no district with id %d.".formatted(id)));
	}

	@Transactional(readOnly = true)
	public List<DistrictDto> search(Optional<Integer> state, Optional<String> name, Optional<Boolean> deleted) {
		return districtRepo.findAll(
				stateSpec(state.filter(stateId -> stateId > 0))
				.and(deletedSpec(deleted))
				.and(nameSpec(name.filter(StringUtils::hasLength)))
				).stream().map(DistrictDto::from).toList();
	}
	
	private Specification<District> stateSpec(Optional<Integer> state) {
		return state.isEmpty() ? Specification.where(null) 
				: (root, query, cb) -> cb.equal(root.get("state").get("id"), state.get());
	}
	
	private Specification<District> nameSpec(Optional<String> name) {
		return name.isEmpty() ? Specification.where(null)
				: (root, query, cb) -> cb.or(
						cb.like(cb.lower(root.get("name")), name.get().toLowerCase().concat("%")),
						cb.like(root.get("burmeseName"), name.get().concat("%"))
				);
	}
	
	private Specification<District> deletedSpec(Optional<Boolean> deleted) {
		return deleted.isEmpty() ? Specification.where(null) 
				: (root, query, cb) -> cb.equal(root.get("deleted"), deleted.get());
	}

}
