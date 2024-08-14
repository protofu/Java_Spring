package com.kosta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OlympicDTO {
	private int id, president_id;
	private String name, content;
	private int gold, silver, bronze;
}
