package com.jdc.onestop.directory.model.entity;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Cacheable(true)
public class State implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, unique = true)
	private String name;
	@Column(nullable = false, name = "burmese_name")
	private String burmeseName;
	@Column(nullable = false)
	private String capital;
	@Column(nullable = false)
	private String region;

	private boolean deleted;

	public State() {
	}

	public State(String name, String burmeseName, String capital, String region, boolean deleted) {
		super();
		this.name = name;
		this.burmeseName = burmeseName;
		this.capital = capital;
		this.region = region;
		this.deleted = deleted;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

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

	public String getBurmeseName() {
		return burmeseName;
	}

	public void setBurmeseName(String burmeseName) {
		this.burmeseName = burmeseName;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
}
