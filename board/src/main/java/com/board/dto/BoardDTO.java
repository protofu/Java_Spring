package com.board.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
// DTO(Data Transfer Object) : 각 계층(MVC) 간 데이터를 주고 받을 때 사용되는 객체
public class BoardDTO {
	private int id;
	private String title, content;
	private int hit;
	private LocalDateTime createAt;
	private String creator;
	private LocalDateTime updateAt;
}
