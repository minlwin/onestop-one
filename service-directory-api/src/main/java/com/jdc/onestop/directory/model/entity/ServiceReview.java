package com.jdc.onestop.directory.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "service_review")
public class ServiceReview implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(optional = false)
	private Service service;
	
	@Column(nullable = false, name="rank_value")
	private int rank;
	@Column(nullable = false)
	private String remark;
	
	@CreatedDate
	private LocalDateTime creation;
	@Column(nullable = false, name = "track_key")
	private String trackKey;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public LocalDateTime getCreation() {
		return creation;
	}

	public void setCreation(LocalDateTime creation) {
		this.creation = creation;
	}

	public String getTrackKey() {
		return trackKey;
	}

	public void setTrackKey(String trackKey) {
		this.trackKey = trackKey;
	}

}
