package com.jdc.onestop.directory.model.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
public class Profile implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	
	@MapsId
	@OneToOne(optional = false)
	@PrimaryKeyJoinColumn
	private Account account;

	@Column(nullable = false)
	private String phone;
	@Column(nullable = false)
	private String organization;
	@Column(nullable = false)
	private String position;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}
