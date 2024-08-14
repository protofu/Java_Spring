package com.kosta.service;

import java.util.List;

import com.kosta.dto.OlympicDTO;

public interface OlympicService {
	// 올림픽 RANK 전체 보여주기
	List<OlympicDTO> getRank() throws Exception;
	// 나라 추가 하기
	void addInternational(OlympicDTO olympicDTO) throws Exception;
	void deleteInternational(int id) throws Exception;
	OlympicDTO getInter(int id) throws Exception;
	void updateOlympic(OlympicDTO olympicDTO) throws Exception;
}
