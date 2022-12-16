package com.jdc.onestop.balance.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Balance implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDate issueAt;
	@ManyToOne
	private Category category;
	
	@ManyToOne
	private Account account;
	private String remark;
	private boolean deleted;
	private boolean fixed;
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
	private List<BalanceDetails> details;
	
	public Balance() {
		details = new ArrayList<>();
	}
	
	public Balance(LocalDate issueAt, Category category, Account account, String remark) {
		super();
		this.issueAt = issueAt;
		this.category = category;
		this.account = account;
		this.remark = remark;
		details = new ArrayList<>();
	}

	public void addDetails(BalanceDetails dto) {
		dto.setBalance(this);
		details.add(dto);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<BalanceDetails> getDetails() {
		return details;
	}

	public void setDetails(List<BalanceDetails> details) {
		this.details = details;
	}

	public LocalDate getIssueAt() {
		return issueAt;
	}

	public void setIssueAt(LocalDate issueAt) {
		this.issueAt = issueAt;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public boolean isFixed() {
		return fixed;
	}

	public void setFixed(boolean fixed) {
		this.fixed = fixed;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
