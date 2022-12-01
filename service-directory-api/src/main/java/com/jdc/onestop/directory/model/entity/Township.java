package com.jdc.onestop.directory.model.entity;

import java.io.Serializable;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
@Cacheable(true)
public class Township implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false, name = "burmese_name")
	private String burmeseName;

	@ManyToOne(optional = false)
	private District district;
	
	public Township() {
	}
	
	public static Township forFileUpload(District district, String name) {
		return new Township(name, district);
	}
	
	private Township(String name, District district) {
		super();
		this.name = name;
		this.district = district;
		this.deleted = false;
	}
	
	public static Township forInsert(String name, String burmeseName, District district, boolean deleted) {
		return new Township(name, burmeseName, district, deleted);
	}
	
	private Township(String name, String burmeseName, District district, boolean deleted) {
		super();
		this.name = name;
		this.burmeseName = burmeseName;
		this.district = district;
		this.deleted = deleted;
	}

	private boolean deleted;

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

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

}
