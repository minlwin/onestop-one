package com.jdc.one.traders.model.dto.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "conversation_message")
@EntityListeners(AuditingEntityListener.class)
public class ConversationMessage implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "message_seq")
	@SequenceGenerator(name = "message_seq")
	private long id;
	
	@ManyToOne
	private Conversation conversation;

	@ManyToOne
	private Account speaker;
	private String message;
	
	@CreatedDate
	@Column(name = "speak_at")
	private LocalDateTime speakAt;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public Account getSpeaker() {
		return speaker;
	}

	public void setSpeaker(Account speaker) {
		this.speaker = speaker;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getSpeakAt() {
		return speakAt;
	}

	public void setSpeakAt(LocalDateTime speakAt) {
		this.speakAt = speakAt;
	}

}
