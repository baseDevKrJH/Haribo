package com.jelly.www.vo;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentVO {
	    	int paymentId;
	    	int tradeId;
	    	String paymentMethod;
	    	String paymentStatus;
	    	int amount;
	    	Timestamp createdAt;
	    	Timestamp updatedAt;

}
