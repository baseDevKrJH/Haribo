package com.jelly.www.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowVO {
	private int followId; // 팔로우 고유 ID
	private FollowStatus followStatus; // 팔로우 상태('FOLLOW', 'UNFOLLOW') 
	private int followerId; // 팔로워 사용자 ID (USER 테이블 참조)
	private int followingId; // 팔로잉 사용자 ID (USER 테이블 참조)
	
	public enum FollowStatus{
		FOLLOW,
		UNFOLLOW
	}
}