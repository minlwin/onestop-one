package com.jdc.one.traders.model.dto.entity.pk;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ConversationPk implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "product_id")
	private int proudctId;
	@Column(name = "sender_id")
	private int senderId;
	
	public static ConversationPk generate(int productId, int senderId) {
		var id = new ConversationPk();
		id.setProudctId(productId);
		id.setSenderId(senderId);
		return id;
	}

	public int getProudctId() {
		return proudctId;
	}

	public void setProudctId(int proudctId) {
		this.proudctId = proudctId;
	}

	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(proudctId, senderId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConversationPk other = (ConversationPk) obj;
		return proudctId == other.proudctId && senderId == other.senderId;
	}

}
