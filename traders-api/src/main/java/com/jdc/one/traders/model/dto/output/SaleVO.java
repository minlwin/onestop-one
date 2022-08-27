package com.jdc.one.traders.model.dto.output;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jdc.one.traders.model.dto.entity.Sale;
import com.jdc.one.traders.model.dto.entity.Sale.Status;

public record SaleVO(
	long id,
	ProductDto product,
	int buyerId,
	String buyerName,
	AddressVO shippingAddress,
	Status status,
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime orderDate,
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime paidDate,
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime shippedDate,
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime closeDate	
		) {

	public static SaleVO from(Sale sale) {
		return new SaleVO(
				sale.getId(), 
				new ProductDto(sale.getProduct()), 
				sale.getBuyer().getId(), 
				sale.getBuyer().getName(), 
				AddressVO.from(sale.getShipping()), 
				sale.getStatus(), 
				sale.getOrderDate(), 
				sale.getPaidDate(), 
				sale.getShippedDate(), 
				sale.getCloseDate());
	}
}
