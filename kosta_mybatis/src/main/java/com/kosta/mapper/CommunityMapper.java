package com.kosta.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kosta.dto.Community;
import com.kosta.dto.CommunityFile;

@Mapper
public interface CommunityMapper {
	// 글 목록 가져오기
	List<Community> findAll();
	// 글 추가하기
	boolean save(Community community);
	// 특정 글 가져오기
	Community findById(int id);
	// 글 삭제하기
	void deleteById(int id);
	// 파일 저장
	void insertFiles(List<CommunityFile> fileList);
	// 조회수 올리기
	void updateHits(int id);
	// 파일 가져오기
	List<CommunityFile> findFilesByCommunityId(int id);
	// 파일 다운로드
	CommunityFile findFileById(int id);
	// 파일 삭제
	void deleteFilesByComId(int id) throws Exception;
}
