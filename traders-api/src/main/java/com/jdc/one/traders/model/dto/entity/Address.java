package com.jdc.one.traders.model.dto.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	private String name;
	@ManyToOne
	private Account account;
	private String address;
	@ManyToOne
	private Township township;

	private boolean deleted;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Township getTownship() {
		return township;
	}

	public void setTownship(Township township) {
		this.township = township;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
