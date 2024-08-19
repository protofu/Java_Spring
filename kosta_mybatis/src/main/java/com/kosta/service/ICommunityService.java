package com.kosta.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.dto.Community;
import com.kosta.dto.CommunityFile;
import com.kosta.dto.User;
import com.kosta.mapper.CommunityMapper;
import com.kosta.mapper.UserMapper;

@Service
public class ICommunityService implements CommunityService{
	@Autowired
	private CommunityMapper cm;
	@Autowired
	private UserMapper um;

	@Override
	public List<Community> getAll() {
		List<Community> list = cm.findAll();
		return list;
	}

	@Override
	public void add(Community community, int id, List<MultipartFile> files) throws Exception {
		// dto의 user를 set
		User user = um.findById(id);
		community.setCreator(user);
		
		cm.save(community);
		int communityId = community.getId();
		
		// 이미지 저장 (커뮤 아이디, 원본 파일명, 새 파일명, 파일크기)
		if (!files.isEmpty()) {
			List<CommunityFile> fileList = new ArrayList<>();
			for (MultipartFile file : files) {
				if (!file.isEmpty()) {
					// 원본 파일명 get
					String originalFilename = file.getOriginalFilename();
					String newFileName = UUID.randomUUID().toString() + "_" + originalFilename;
					long fileSize = file.getSize();
					
					CommunityFile cf = new CommunityFile();
					cf.setCommunityId(communityId);
					cf.setOriginFileName(originalFilename);
					cf.setStoredFilePath(newFileName);
					cf.setFileSize(fileSize);
					
					fileList.add(cf);
					
					File dest = new File("C:\\Users\\WD\\Desktop\\images\\" + newFileName);
					file.transferTo(dest);
				}
			}
			if (!fileList.isEmpty()) {
				cm.insertFiles(fileList);
				community.setFileList(fileList);
			}
		}
	}

	@Override
	public Community getArticleById(int id) {
		Community article = cm.findById(id);
		List<CommunityFile> fileList = cm.findFilesByCommunityId(id);
		article.setFileList(fileList);
		cm.updateHits(id);
		System.out.println(article);
		return article;
	}

	@Override
	public void deleteArticle(int id) {
		cm.deleteById(id);
	}
	
	

}
