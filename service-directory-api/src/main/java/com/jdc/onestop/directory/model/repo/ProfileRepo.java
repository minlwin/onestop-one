package com.jdc.onestop.directory.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdc.onestop.directory.model.entity.Profile;

public interface ProfileRepo extends JpaRepository<Profile, Integer>{

}
