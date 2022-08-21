package com.jdc.one.traders.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.one.traders.model.dto.input.ConversationMessageDto;
import com.jdc.one.traders.model.dto.input.ConversationStart;
import com.jdc.one.traders.model.dto.output.ConversationSummary;
import com.jdc.one.traders.model.dto.output.ConversationVO;
import com.jdc.one.traders.model.service.ConversationService;

@RestController
@RequestMapping("conversations")
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
	
	@GetMapping("{id}")
	ConversationVO findById(@PathVariable long id) {
		return service.findById(id);
	}

	@PostMapping
	ConversationVO create(ConversationStart dto) {
		return service.create(dto);
	}
	
	@PostMapping("message")
	ConversationVO sendMessage(ConversationMessageDto dto) {
		return service.addMessage(dto);
	}
	
}
