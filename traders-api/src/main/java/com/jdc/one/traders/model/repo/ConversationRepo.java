package com.jdc.one.traders.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.jdc.one.traders.model.dto.entity.Conversation;
import com.jdc.one.traders.model.dto.entity.pk.ConversationPk;

public interface ConversationRepo extends JpaRepository<Conversation, ConversationPk>, JpaSpecificationExecutor<Conversation>{

}
