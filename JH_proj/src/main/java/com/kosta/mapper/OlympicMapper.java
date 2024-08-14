package com.kosta.mapper;

import java.util.List;

import com.kosta.dto.OlympicDTO;

public interface OlympicMapper {
	// 올림픽 랭크 가져오기
	public List<OlympicDTO> selectRankList() throws Exception;

}
