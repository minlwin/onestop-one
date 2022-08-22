package com.jdc.one.traders.model.dto.output;

import com.jdc.one.traders.model.dto.entity.Conversation;

public record ConversationSummary(
		int senderId,
		String sender,
		ProductDto product,
		int messages
		) {
	
	public ConversationSummary(Conversation entity) {
		this(
		 entity.getSender().getId(),
		 entity.getSender().getName(),
		 new ProductDto(entity.getProduct()),
		 entity.getMessage().size()
		);
	}
}
