package com.jdc.one.traders.model.dto.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.jdc.one.traders.model.dto.entity.pk.ConversationPk;

@Entity
@Table(name = "conversation")
public class Conversation implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ConversationPk id;
	
	@ManyToOne
	@MapsId("product_id")
	private Product product;
	@ManyToOne
	@MapsId("sender_id")
	private Account sender;

	@OneToMany(
			mappedBy = "conversation", 
			cascade = {CascadeType.PERSIST, CascadeType.MERGE}, 
			orphanRemoval = true)
	private List<ConversationMessage> message;
	
	
	public Conversation() {
		id = new ConversationPk();
		message = new ArrayList<>();
	}

	public ConversationPk getId() {
		return id;
	}

	public void setId(ConversationPk id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
		this.id.setProudctId(product.getId());
	}

	public Account getSender() {
		return sender;
	}

	public void setSender(Account sender) {
		this.sender = sender;
		this.id.setSenderId(sender.getId());
	}

	public List<ConversationMessage> getMessage() {
		return message;
	}

	public void setMessage(List<ConversationMessage> message) {
		this.message = message;
	}
	
	public Conversation addMessage(ConversationMessage entry) {
		message.add(entry);
		entry.setConversation(this);
		return this;
	}

}
