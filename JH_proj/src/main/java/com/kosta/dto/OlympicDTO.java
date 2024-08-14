package com.kosta.dto;

import lombok.Data;

@Data
public class OlympicDTO {
	private int id, presidentId;
	private String name, content;
	private int gold, silver, bronze;
	private String flagImg;
}
