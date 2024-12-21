package com.jelly.www.vo;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor 
public class UserAccountVO {
	int accountId;
	int userId;
	String bankName; // 은행 이름
	String accountNumber; // 계좌번호
	String accountHolder; // 계좌주명
	Boolean isDefault; // 기본 계좌 여부 
	Timestamp createdAt; // 생성일
	


}
