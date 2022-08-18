package com.jdc.one.traders.model.dto.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "sale_payment")
@EntityListeners(AuditingEntityListener.class)
public class SalePayment implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "sale_payment_seq")
	@SequenceGenerator(name = "sale_payment_seq")
	private long id;
	@ManyToOne
	private Sale sale;
	@ManyToOne
	private BankingInfo banking;
	private int amount;
	@Column(name = "photo")
	private String screenShot;
	@CreatedDate
	@Column(name = "paid_at")
	private LocalDateTime paidAt;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public BankingInfo getBanking() {
		return banking;
	}

	public void setBanking(BankingInfo banking) {
		this.banking = banking;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getScreenShot() {
		return screenShot;
	}

	public void setScreenShot(String screenShot) {
		this.screenShot = screenShot;
	}

	public LocalDateTime getPaidAt() {
		return paidAt;
	}

	public void setPaidAt(LocalDateTime paidAt) {
		this.paidAt = paidAt;
	}

}
