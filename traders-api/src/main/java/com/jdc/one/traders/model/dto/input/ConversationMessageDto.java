package com.jdc.one.traders.model.dto.input;

public record ConversationMessageDto(
		long conversationId,
		int senderId,
		String message
		) {

}
