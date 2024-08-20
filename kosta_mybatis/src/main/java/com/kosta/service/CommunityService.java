package com.kosta.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kosta.dto.Community;
import com.kosta.dto.CommunityFile;

public interface CommunityService {
	// 글 목록 가져오기
	List<Community> getAll();
	// 글 추가하기
	void add(Community community, int id, List<MultipartFile> files) throws Exception;
	// 특정 글 가져오기
	Community getArticleById(int id);
	// 글 삭제하기
	void deleteArticle(int id) throws Exception;
	
	CommunityFile getCommunityFileById(int id) throws Exception;
}
