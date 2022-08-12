package com.jdc.one.traders.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdc.one.traders.model.dto.entity.Conversation;

public interface ConversationRepo extends JpaRepository<Conversation, Long>{

}
