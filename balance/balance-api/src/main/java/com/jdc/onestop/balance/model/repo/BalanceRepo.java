package com.jdc.onestop.balance.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdc.onestop.balance.model.entity.Balance;

public interface BalanceRepo extends JpaRepository<Balance, Integer>{

}
