package com.jdc.one.traders.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdc.one.traders.model.dto.entity.Profile;

public interface ProfileRepo extends JpaRepository<Profile, Integer>{

}
