package com.jdc.one.traders.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.one.traders.model.dto.entity.pk.ConversationPk;
import com.jdc.one.traders.model.dto.input.ConversationMessageDto;
import com.jdc.one.traders.model.dto.output.ConversationSummary;
import com.jdc.one.traders.model.dto.output.ConversationVO;
import com.jdc.one.traders.model.service.ConversationService;

@RestController
@RequestMapping("conversation")
public class ConversationApi {
	
	@Autowired
	private ConversationService service;
	
	@GetMapping
	List<ConversationSummary> search(
			@RequestParam Optional<String> product,
			@RequestParam Optional<String> owner,
			@RequestParam Optional<String> sender
			) {
		return service.search(product, owner, sender);
	}
	
	@GetMapping("{product}/{sender}")
	ConversationVO findById(@PathVariable int product, @PathVariable int sender) {
		return service.findById(ConversationPk.generate(product, sender));
	}

	@PostMapping("message")
	ConversationVO sendMessage(@RequestBody ConversationMessageDto dto) {
		service.addMessage(dto);
		return service.findById(dto.conversationId());
	}
	
}
