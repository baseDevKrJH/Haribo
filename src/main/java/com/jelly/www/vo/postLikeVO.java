package com.jelly.www.vo;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class postLikeVO {
	private int like_id; // 좋아요 고유 ID
    private int comment_id; // 댓글 ID (COMMENT 테이블 참조)
    private int user_id; // 사용자 ID (USER 테이블 참조)
    private Timestamp liked_at; //좋아요를 누른 시간
}