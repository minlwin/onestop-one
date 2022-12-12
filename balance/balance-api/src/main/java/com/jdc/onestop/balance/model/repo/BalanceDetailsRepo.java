package com.jdc.onestop.balance.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdc.onestop.balance.model.entity.BalanceDetails;

public interface BalanceDetailsRepo extends JpaRepository<BalanceDetails, Integer> {

}
