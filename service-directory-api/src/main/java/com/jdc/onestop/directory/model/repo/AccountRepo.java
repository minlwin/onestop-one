package com.jdc.onestop.directory.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdc.onestop.directory.model.entity.Account;

public interface AccountRepo extends JpaRepository<Account, Integer>{

}
