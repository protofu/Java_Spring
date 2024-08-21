package com.kosta.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;

@Entity
@RequiredArgsConstructor
@Data
public class Article {
	// 기본키 설정
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(updatable=false)
	private Long id;
	
	@Column(nullable=false)
	private String title;
	
	@Column(nullable=false)
	private String content;
	
	@Builder
	public Article(Long id, String title, String content) {
		this.id = id;
		this.title = title;
		this.content = content;
	}
}
