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
public class ProductSellerVO {
	int productSellerId;
	int productId;
	int sellerId;
	int sizeId;
	int price;
	int stock;
	Timestamp createdAt;
	Timestamp updatedAt;

}
