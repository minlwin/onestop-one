package com.jdc.one.traders.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdc.one.traders.model.dto.entity.ConversationMessage;

public interface ConversationMessageRepo extends JpaRepository<ConversationMessage, Long>{

}
