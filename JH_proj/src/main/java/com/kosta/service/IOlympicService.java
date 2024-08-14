package com.kosta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.dto.OlympicDTO;
import com.kosta.mapper.OlympicMapper;

@Service
public class IOlympicService implements OlympicService {
	@Autowired
	private OlympicMapper om;
	@Override
	public List<OlympicDTO> getRank() throws Exception {
		return om.selectRankList();
	}
	@Override
	public void addInternational(OlympicDTO olympicDTO) throws Exception{
		om.insertInternational(olympicDTO);
	}
	@Override
	public void deleteInternational(int id) throws Exception {
		om.removeInternational(id);
	}
	@Override
	public OlympicDTO getInter(int id) throws Exception {
		return om.selectInter(id);
	}
	@Override
	public void updateOlympic(OlympicDTO olympicDTO) throws Exception {
		om.updateOlympic(olympicDTO);
	}

}
