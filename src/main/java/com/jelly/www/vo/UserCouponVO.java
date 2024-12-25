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
public class UserCouponVO {
	int userCouponId;
	int userId;
	int couponId;
	Boolean isUsed;
	Timestamp usedAt;
	
}
