package com.jdc.onestop.directory.model.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jdc.onestop.directory.model.ServiceDirectoryAppException;
import com.jdc.onestop.directory.model.dto.TownshipDto;
import com.jdc.onestop.directory.model.dto.form.TownshipForm;
import com.jdc.onestop.directory.model.entity.District;
import com.jdc.onestop.directory.model.entity.Township;
import com.jdc.onestop.directory.model.repo.DistrictRepo;
import com.jdc.onestop.directory.model.repo.StateRepo;
import com.jdc.onestop.directory.model.repo.TownshipRepo;

import jakarta.validation.Valid;

@Service
@Transactional
public class TownshipService {
	
	@Autowired
	private TownshipRepo townshipRepo;
	@Autowired
	private DistrictRepo districtRepo;
	
	@Autowired
	private StateRepo stateRepo;

	public TownshipDto create(@Valid TownshipForm form) {
		var entity = form.entity(districtRepo::getReferenceById);
		return TownshipDto.from(townshipRepo.save(entity));
	}

	public TownshipDto update(int id, @Valid TownshipForm form) {
		return townshipRepo.findById(id)
				.map(entity -> {
					entity.setName(form.name());
					entity.setBurmeseName(form.burmeseName());
					entity.setDeleted(form.deleted());
					entity.setDistrict(districtRepo.getReferenceById(form.districtId()));
					return entity;
				})
				.map(TownshipDto::from)
				.orElseThrow(() -> new ServiceDirectoryAppException("There is no township with id %d.".formatted(id)));
	}

	public List<TownshipDto> upload(int district, List<String> lines) {
		
		for(var line : lines) {
			create(TownshipForm.of(district, line));
		}
		
		return search(Optional.of(district), Optional.empty(), Optional.empty());
	}

	@Transactional(readOnly = true)
	public TownshipDto findById(int id) {
		return townshipRepo.findById(id)
				.map(TownshipDto::from)
				.orElseThrow(() -> new ServiceDirectoryAppException("There is no township with id %d.".formatted(id)));
	}

	@Transactional(readOnly = true)
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

	public List<TownshipDto> uploadToState(int stateId, List<String> lines) {
		
		// disName, disBurmese, tshName, tshBurmese
		Map<DistrictDto, List<String[]>> map = lines.stream()
			// String[]
			.map(line -> line.split("\t"))
			// Map<DistrictDto, List<String[]>>
			.collect(Collectors.groupingBy(array -> new DistrictDto(array[0], array[1])));
		
		// get state
		var state = stateRepo.getReferenceById(stateId);
		
		for(var dto : map.keySet()) {
			// Find District by name and stateId
			var district = districtRepo.findOneByNameAndStateId(dto.disName, stateId)
					.orElse(District.forFileUplaod(state, dto.disName()));
			
			// Update Burmese Name
			district.setBurmeseName(dto.disBurmese());
			district = districtRepo.save(district);
			
			var townships = map.get(dto);
			for(var array : townships) {
				
				var townshipName = array[2];
				
				var township = townshipRepo.findOneByDistrictIdAndName(district.getId(), townshipName)
						.orElse(Township.forFileUpload(district, townshipName));
				township.setBurmeseName(array[3]);
				townshipRepo.save(township);
			}
		}
		
		// township.district.state.id
		return townshipRepo
				.findByDistrictStateId(stateId)
				.stream().map(TownshipDto::from).toList();
	}
	
	private static record DistrictDto(String disName, String disBurmese) {}
	
}


