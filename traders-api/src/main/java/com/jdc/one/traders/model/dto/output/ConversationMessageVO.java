package com.jdc.one.traders.model.dto.output;

import java.time.LocalDateTime;

import com.jdc.one.traders.model.dto.entity.ConversationMessage;

public record ConversationMessageVO(
		long id,
		long conversationId,
		int sender,
		String message,
		LocalDateTime sendTime
		) {
	
	public ConversationMessageVO(ConversationMessage m) {
		this(m.getId(), m.getConversation().getId(), m.getSpeaker().getId(), m.getMessage(), m.getSpeakAt());
	}
}
