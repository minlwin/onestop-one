package com.jdc.onestop.directory.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Service implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(optional = false)
	private Account owner;

	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private Type type;
	@Column(nullable = false)
	private String phone;

	@Column(nullable = false)
	private String address;

	@ManyToOne(optional = false)
	private Township township;

	private String coverPhoto;

	@ElementCollection
	@CollectionTable(name = "service_photos")
	private List<String> photos;

	public Service() {
		photos = new ArrayList<>();
	}

	public enum Type {

		PublicHospital("Public Hospital"), PrivateHospital("Private Hospital"), LocalClinic("Local Clinic"),
		Consultants("Consulting Service");

		private String value;

		private Type(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Account getOwner() {
		return owner;
	}

	public void setOwner(Account owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getCoverPhoto() {
		return coverPhoto;
	}

	public void setCoverPhoto(String coverPhoto) {
		this.coverPhoto = coverPhoto;
	}

	public List<String> getPhotos() {
		return photos;
	}

	public void setPhotos(List<String> photos) {
		this.photos = photos;
	}

}
