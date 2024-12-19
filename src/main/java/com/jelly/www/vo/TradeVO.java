package com.jelly.www.vo;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TradeVO {
	
	int tradeId;
	int productSellerId;
	int buyerId;
	int addressId;
	int couponId;
	int totalPrice;
	int tradeStatus;
	Timestamp tradeDate;
	Timestamp completedAt;
	Timestamp createdAt;
	Timestamp updatedAt;
	
}
