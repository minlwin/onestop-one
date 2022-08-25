package com.jdc.one.traders.model.dto.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "sale")
@EntityListeners(AuditingEntityListener.class)
public class Sale implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "sale_seq")
	@SequenceGenerator(name = "sale_seq")
	private long id;
	@ManyToOne
	private Product product;
	private Status status;
	@ManyToOne
	private Account buyer;
	@ManyToOne
	private Address shipping;
	
	@OneToMany(mappedBy = "sale", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
	private List<SaleConversation> conversation;
	
	@Column(name = "order_date")
	private LocalDateTime orderDate;
	@Column(name = "paid_date")
	private LocalDateTime paidDate;
	@Column(name = "shipped_date")
	private LocalDateTime shippedDate;
	@Column(name = "close_date")
	private LocalDateTime closeDate;
	
	@LastModifiedDate
	private LocalDateTime updateDate;
	
	public Sale() {
		conversation = new ArrayList<>();
	}

	public enum Status {
		Order, Paid, Shipped, Finish, Cancel
	}
	
	public void addConversation(SaleConversation data) {
		data.setSale(this);
		conversation.add(data);
	}

	public List<SaleConversation> getConversation() {
		return conversation;
	}

	public void setConversation(List<SaleConversation> conversation) {
		this.conversation = conversation;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Account getBuyer() {
		return buyer;
	}

	public void setBuyer(Account buyer) {
		this.buyer = buyer;
	}

	public Address getShipping() {
		return shipping;
	}

	public void setShipping(Address shipping) {
		this.shipping = shipping;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDateTime getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(LocalDateTime paidDate) {
		this.paidDate = paidDate;
	}

	public LocalDateTime getShippedDate() {
		return shippedDate;
	}

	public void setShippedDate(LocalDateTime shippedDate) {
		this.shippedDate = shippedDate;
	}

	public LocalDateTime getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(LocalDateTime closeDate) {
		this.closeDate = closeDate;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}
	
	

}
