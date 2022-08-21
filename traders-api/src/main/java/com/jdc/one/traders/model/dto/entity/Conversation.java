package com.jdc.one.traders.model.dto.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "conversation")
public class Conversation implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "conversation_seq")
	@SequenceGenerator(name = "conversation_seq")
	private long id;
	@ManyToOne
	private Product product;
	@ManyToOne
	private Account sender;

	@OneToMany(mappedBy = "conversation")
	private List<ConversationMessage> message;

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

	public Account getSender() {
		return sender;
	}

	public void setSender(Account sender) {
		this.sender = sender;
	}

	public List<ConversationMessage> getMessage() {
		return message;
	}

	public void setMessage(List<ConversationMessage> message) {
		this.message = message;
	}

}
