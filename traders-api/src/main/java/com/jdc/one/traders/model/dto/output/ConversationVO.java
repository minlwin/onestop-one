package com.jdc.one.traders.model.dto.output;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jdc.one.traders.model.dto.entity.Account;
import com.jdc.one.traders.model.dto.entity.Conversation;
import com.jdc.one.traders.model.dto.entity.Product;
import com.jdc.one.traders.model.dto.entity.Product.Condition;

public record ConversationVO(
		int customerId,
		String customer,
		int productId,
		String product,
		int categoryId,
		String category,
		int sellerId,
		String seller,
		Condition condition,
		int price,
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
		LocalDateTime publishAt,
		List<String> photos,
		List<ConversationMessageVO> message
		) {
	
	public ConversationVO(Conversation entity) {
		this(
			entity.getSender().getId(),
			entity.getSender().getName(),
			entity.getProduct().getId(),
			entity.getProduct().getName(),
			entity.getProduct().getCategory().getId(),
			entity.getProduct().getCategory().getName(),
			entity.getProduct().getSeller().getId(),
			entity.getProduct().getSeller().getName(),
			entity.getProduct().getCondition(),
			entity.getProduct().getPrice(),
			entity.getProduct().getPublishAt(),
			entity.getProduct().getPhotos(),
			entity.getMessage().stream().map(ConversationMessageVO::new).toList()
		);
	}
	
	public static ConversationVO getInstance(Account sender, Product product) {
		return new ConversationVO(
				sender.getId(), 
				sender.getName(), 
				product.getId(), 
				product.getName(), 
				product.getCategory().getId(), 
				product.getCategory().getName(), 
				product.getSeller().getId(), 
				product.getSeller().getName(), 
				product.getCondition(), 
				product.getPrice(), 
				product.getPublishAt(), 
				product.getPhotos(),
				List.of());
	}
}
