package com.jdc.one.traders.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdc.one.traders.model.dto.entity.SalePayment;

public interface SalePaymentRepo extends JpaRepository<SalePayment, Long> {

}
