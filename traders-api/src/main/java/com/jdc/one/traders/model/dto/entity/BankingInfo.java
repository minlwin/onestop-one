package com.jdc.one.traders.model.dto.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "banking")
public class BankingInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String type;
	@Column(nullable = false, name = "acc_name")
	private String accountName;
	@Column(nullable = false, name = "acc_num")
	private String accountNumber;
	private boolean deleted;

	@ManyToOne(optional = false)
	private Profile profile;
	
	public BankingInfo() {
	}
	
	public BankingInfo(int id, String type, String accountName, String accountNumber, boolean deleted) {
		super();
		this.id = id;
		this.type = type;
		this.accountName = accountName;
		this.accountNumber = accountNumber;
		this.deleted = deleted;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

}
