package com.jdc.onestop.directory.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.onestop.directory.model.ServiceDirectoryAppException;
import com.jdc.onestop.directory.model.dto.StateDto;
import com.jdc.onestop.directory.model.dto.form.StateForm;
import com.jdc.onestop.directory.model.repo.StateRepo;

@Service
@Transactional
public class StateService {
	
	@Autowired
	private StateRepo repo;

	@Transactional(readOnly = true)
	public List<StateDto> search(Optional<String> region, Optional<String> keyword, Optional<Boolean> deleted) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

}
