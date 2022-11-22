package com.jdc.onestop.directory.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jdc.onestop.directory.model.dto.StateDto;
import com.jdc.onestop.directory.model.dto.form.StateForm;

@Service
public class StateService {

	public List<StateDto> search(Optional<String> region, Optional<String> keyword, Optional<Boolean> deleted) {
		// TODO Auto-generated method stub
		return null;
	}

	public StateDto findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public StateDto create(StateForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	public StateDto save(int id, StateForm form) {
		// TODO Auto-generated method stub
		return null;
	}

}
