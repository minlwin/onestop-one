package com.jdc.one.traders.model.dto.output;

import java.time.LocalDateTime;

import com.jdc.one.traders.model.dto.entity.ConversationMessage;

public record ConversationMessageVO(
		long id,
		int speakerId,
		String speaker,
		String message,
		LocalDateTime sendTime
		) {
	
	public ConversationMessageVO(ConversationMessage m) {
		this(
		m.getId(), 
		m.getSpeaker().getId(), 
		m.getSpeaker().getName(), 
		m.getMessage(), 
		m.getSpeakAt());
	}
}
