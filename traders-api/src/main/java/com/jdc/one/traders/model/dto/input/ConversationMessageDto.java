package com.jdc.one.traders.model.dto.input;

import com.jdc.one.traders.model.dto.entity.pk.ConversationPk;

public record ConversationMessageDto(
		int productId,
		int senderId,
		int messageSenderId,
		String message
		) {
	
	public ConversationPk conversationId() {
		return ConversationPk.generate(productId, senderId);
	}
	
}
