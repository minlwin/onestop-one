package com.jdc.onestop.directory.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.onestop.directory.model.dto.DistrictDto;
import com.jdc.onestop.directory.model.dto.form.DistrictForm;

@Service
@Transactional
public class DistrictService {

	public DistrictDto create(DistrictForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	public DistrictDto update(int id, DistrictForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly = true)
	public DistrictDto findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly = true)
	public List<DistrictDto> search(Optional<Integer> state, Optional<String> name, Optional<Boolean> deleted) {
		// TODO Auto-generated method stub
		return null;
	}

}
