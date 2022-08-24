package com.jdc.one.traders.model.service;

import java.util.List;
import java.util.Optional;

import com.jdc.one.traders.model.dto.entity.pk.ConversationPk;
import com.jdc.one.traders.model.dto.input.ConversationMessageDto;
import com.jdc.one.traders.model.dto.output.ConversationSummary;
import com.jdc.one.traders.model.dto.output.ConversationVO;

public interface ConversationService {

	List<ConversationSummary> search(Optional<String> product, Optional<String> owner, Optional<String> sender);

	ConversationVO findById(ConversationPk id);

	void addMessage(ConversationMessageDto dto);

}
