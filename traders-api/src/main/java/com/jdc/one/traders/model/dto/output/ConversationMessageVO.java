package com.jdc.one.traders.model.dto.output;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jdc.one.traders.model.dto.entity.ConversationMessage;

public record ConversationMessageVO(
		long id,
		int speakerId,
		String speaker,
		String message,
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
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
