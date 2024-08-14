package com.kosta.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kosta.dto.OlympicDTO;

@Mapper
public interface OlympicMapper {
	// 올림픽 랭크 가져오기
	public List<OlympicDTO> selectRankList() throws Exception;

	public void insertInternational(OlympicDTO olympicDTO) throws Exception;

	public void removeInternational(int id) throws Exception;

	public OlympicDTO selectInter(int id) throws Exception;

	public void updateOlympic(OlympicDTO olympicDTO) throws Exception;

}
