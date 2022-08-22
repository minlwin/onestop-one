package com.jdc.one.traders.model.dto.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "profile")
public class Profile implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	@OneToOne
	@PrimaryKeyJoinColumn
	@MapsId("id")
	private Account account;
	private String greeting;
	private Gender gender;
	@Column(name = "dob")
	private LocalDate dateOfBirth;
	@Column(name = "photo")
	private String coverImage;

	@OneToMany(mappedBy = "profile", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
	private List<BankingInfo> bankingInfo;
	@OneToMany(mappedBy = "profile", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
	private List<Address> address;
	
	public Profile() {
		bankingInfo = new ArrayList<>();
		address = new ArrayList<>();
	}
	
	public void addBankingInfo(BankingInfo item) {
		item.setProfile(this);
		bankingInfo.add(item);
	}
	
	public void addAddress(Address item) {
		item.setProfile(this);
		address.add(item);
	}

	public List<BankingInfo> getBankingInfo() {
		return bankingInfo;
	}

	public void setBankingInfo(List<BankingInfo> bankingInfo) {
		this.bankingInfo = bankingInfo;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public enum Gender {
		Male, Female
	}

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
		this.id = account.getId();	
	}

	public String getGreeting() {
		return greeting;
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

}
