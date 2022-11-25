package com.jdc.onestop.directory.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jdc.onestop.directory.model.ServiceDirectoryAppException;
import com.jdc.onestop.directory.model.dto.StateDto;
import com.jdc.onestop.directory.model.dto.form.StateForm;
import com.jdc.onestop.directory.model.entity.State;
import com.jdc.onestop.directory.model.repo.StateRepo;

@Service
@Transactional
public class StateService {
	
	@Autowired
	private StateRepo repo;

	@Transactional(readOnly = true)
	public List<StateDto> search(Optional<String> region, Optional<String> keyword, Optional<Boolean> deleted) {
		return repo.findAll(regionSpec(region.filter(StringUtils::hasLength))
				.and(deletedSpec(deleted))
				.and(keywordSpec(keyword.filter(StringUtils::hasLength))))
				.stream().map(StateDto::from).toList();
	}
	
	@Transactional(readOnly = true)
	public StateDto findById(int id) {
		return repo.findById(id).map(StateDto::from)
				.orElseThrow(() -> new ServiceDirectoryAppException("There is no state with id %d.".formatted(id)));
	}
	
	public StateDto create(StateForm form) {
		var entity = repo.save(form.entity());
		return StateDto.from(entity);
	}

	public StateDto save(int id, StateForm form) {
		return repo.findById(id)
				.map(entity -> {
					entity.setName(form.name());
					entity.setBurmeseName(form.burmeseName());
					entity.setRegion(form.region());
					entity.setCapital(form.capital());
					entity.setDeleted(form.deleted());
					return StateDto.from(entity);
				})
				.orElseThrow(() -> new ServiceDirectoryAppException("There is no state with id %d.".formatted(id)));
	}
	
	private Specification<State> regionSpec(Optional<String> region) {
		return region.isEmpty() ? Specification.where(null) : 
			((root, query, cb) -> cb.equal(root.get("region"), region.get()));
	}

	private Specification<State> keywordSpec(Optional<String> keyword) {
		return keyword.isEmpty() ? Specification.where(null) : 
			((root, query, cb) -> cb.or(
					// name
					cb.like(cb.lower(root.get("name")), keyword.get().toLowerCase().concat("%")),
					// burmese name
					cb.like(root.get("burmeseName"), keyword.get().concat("%")),
					// capital
					cb.like(cb.lower(root.get("capital")), keyword.get().toLowerCase().concat("%"))
					));
	}

	private Specification<State> deletedSpec(Optional<Boolean> deleted) {
		return deleted.isEmpty() ? Specification.where(null) : 
			((root, query, cb) -> cb.equal(root.get("deleted"), deleted.get()));
	}

}
