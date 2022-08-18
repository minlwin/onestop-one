package com.jdc.one.traders.model.dto.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "product")
@EntityListeners(AuditingEntityListener.class)
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "product_seq")
	@SequenceGenerator(name = "product_seq")
	private int id;
	private String name;
	@ManyToOne
	private Category category;
	@ManyToOne
	private Account seller;
	@Column(name = "prod_cond")
	private Condition condition;
	private int price;
	@CreatedDate
	@Column(name = "publish_at")
	private LocalDateTime publishAt;

	@ElementCollection
	@CollectionTable(name = "product_feature")
	@MapKeyColumn(name = "name")
	private Map<String, String> features;
	@ElementCollection
	@CollectionTable(name = "product_photo")
	private List<String> photos;

	@OneToMany(mappedBy = "product")
	private List<Sale> sale;

	private boolean soldOut;
	
	public Product() {
		features = new HashMap<>();
		photos = new ArrayList<>();
		sale = new ArrayList<>();
	}

	public enum Condition {
		New, Used
	}

	public List<Sale> getSale() {
		return sale;
	}

	public void setSale(List<Sale> sale) {
		this.sale = sale;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Account getSeller() {
		return seller;
	}

	public void setSeller(Account seller) {
		this.seller = seller;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public LocalDateTime getPublishAt() {
		return publishAt;
	}

	public void setPublishAt(LocalDateTime publishAt) {
		this.publishAt = publishAt;
	}

	public Map<String, String> getFeatures() {
		return features;
	}

	public void setFeatures(Map<String, String> features) {
		this.features = features;
	}

	public List<String> getPhotos() {
		return photos;
	}

	public void setPhotos(List<String> photos) {
		this.photos = photos;
	}

	public boolean isSoldOut() {
		return soldOut;
	}

	public void setSoldOut(boolean soldOut) {
		this.soldOut = soldOut;
	}

}
