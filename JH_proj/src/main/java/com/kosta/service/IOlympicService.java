package com.kosta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.kosta.dto.OlympicDTO;
import com.kosta.mapper.OlympicMapper;

public class IOlympicService implements OlympicService {
	@Autowired
	private OlympicMapper om;
	@Override
	public List<OlympicDTO> getRank() throws Exception {
		return om.selectRankList();
	}

}
