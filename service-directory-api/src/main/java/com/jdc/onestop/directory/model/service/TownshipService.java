package com.jdc.onestop.directory.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jdc.onestop.directory.model.ServiceDirectoryAppException;
import com.jdc.onestop.directory.model.dto.TownshipDto;
import com.jdc.onestop.directory.model.dto.form.TownshipForm;
import com.jdc.onestop.directory.model.entity.Township;
import com.jdc.onestop.directory.model.repo.DistrictRepo;
import com.jdc.onestop.directory.model.repo.TownshipRepo;

@Service
@Transactional(readOnly = true)
public class TownshipService {
	
	@Autowired
	private TownshipRepo townshipRepo;
	@Autowired
	private DistrictRepo districtRepo;

	public TownshipDto findById(int id) {
		return townshipRepo.findById(id)
				.map(TownshipDto::from)
				.orElseThrow(() -> new ServiceDirectoryAppException("There is no township with id %d.".formatted(id)));
	}

	@Transactional
	public TownshipDto create(TownshipForm form) {
		var entity = form.entity(districtRepo::getReferenceById);
		return TownshipDto.from(townshipRepo.save(entity));
	}

	@Transactional
	public TownshipDto update(int id, TownshipForm form) {
		return townshipRepo.findById(id)
				.map(entity -> {
					entity.setName(form.name());
					entity.setBurmeseName(form.burmeseName());
					entity.setDeleted(form.deleted());
					entity.setDistrict(districtRepo.getReferenceById(form.distictId()));
					return entity;
				})
				.map(TownshipDto::from)
				.orElseThrow(() -> new ServiceDirectoryAppException("There is no township with id %d.".formatted(id)));
	}

	public List<TownshipDto> search(Optional<Integer> district, Optional<String> keyword, Optional<Boolean> deleted) {
		return townshipRepo.findAll(
					districtSpec(district.filter(a -> a > 0))
					.and(deletedSpec(deleted))
					.and(keywordSpec(keyword.filter(StringUtils::hasLength)))
				).stream().map(TownshipDto::from).toList();
	}
	
	private Specification<Township> districtSpec(Optional<Integer> district) {
		return district.isEmpty() ? Specification.where(null) : 
			(root, query, cb) -> cb.equal(root.get("district").get("id"), district.get());
	}

	private Specification<Township> keywordSpec(Optional<String> keyword) {
		return keyword.isEmpty() ? Specification.where(null) : 
			(root, query, cb) -> cb.or(
					cb.like(cb.lower(root.get("name")), keyword.get().toLowerCase().concat("%")),
					cb.like(root.get("burmeseName"), keyword.get().concat("%"))
			);
	}

	private Specification<Township> deletedSpec(Optional<Boolean> deleted) {
		return deleted.isEmpty() ? Specification.where(null) : 
			(root, query, cb) -> cb.equal(root.get("deleted"), deleted.get());
	}
}
