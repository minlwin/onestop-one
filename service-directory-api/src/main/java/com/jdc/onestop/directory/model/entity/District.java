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
public class District implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(optional = false)
	private State state;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false, name = "burmese_name")
	private String burmeseName;

	private boolean deleted;

	public District() {
	}

	public static District forFileUplaod(State state, String name) {
		return new District(state, name);
	}

	private District(State state, String name) {
		super();
		this.state = state;
		this.name = name;
		this.deleted = false;
	}

	public static District forInsert(State state, String name, String burmeseName, boolean deleted) {
		return new District(state, name, burmeseName, deleted);
	}

	private District(State state, String name, String burmeseName, boolean deleted) {
		super();
		this.state = state;
		this.name = name;
		this.burmeseName = burmeseName;
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

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
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

}
