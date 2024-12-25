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
public class CouponVO {
	int couponId;
	String couponCode;
	String description;
	String couponType;
	int discountAmount;
	int discountPercentage;
	int minimumOrderAmount;
	String expiryDate;
	Timestamp createdAt;
	Timestamp updatedAt;
}
