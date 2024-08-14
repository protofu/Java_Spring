package com.kosta.service;

import java.util.List;

import com.kosta.dto.OlympicDTO;

public interface OlympicService {
	// 올림픽 RANK 전체 보여주기
	List<OlympicDTO> getRank() throws Exception;
}
