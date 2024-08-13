package com.board.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.board.dto.BoardDTO;
import com.board.dto.FileDTO;
import com.board.mapper.BoardMapper;
import com.board.mapper.FileMapper;

@Service // 비즈니스 로직을 처리하는 서비스 클래스임을 나타내는 어노테이션
@Transactional
public class BoardServiceImpl implements BoardService {
	@Autowired // Mapper 자동 주입
	private BoardMapper boardMapper;
	@Autowired // Mapper 자동 주입
	private FileMapper fileMapper;
	
	@Override
	public List<BoardDTO> selectBoardList() throws Exception {
		// 비즈니스 로직 (리스트 가져오기)
		List<BoardDTO> boardList = boardMapper.selectBoardList(); 
		return boardList;
	}

	@Override
	public void insertBoard(BoardDTO boardDTO, List<MultipartFile> files) throws Exception {
		boardMapper.insertBoard(boardDTO); // useGeneratedKeys="true" keyProperty="id"로 id 값 갱신
		int boardId = boardDTO.getId();
		
		// 첨부 파일이 있다면
		if (files != null & !files.isEmpty()) {
			// FileDTO 리스트 생성
			List<FileDTO> fileList = new ArrayList<>();
			// 하나하나 파일을 가져온다.
			for(MultipartFile file : files) {
				// 파일이 존재하면
				if (!file.isEmpty()) {
					// 원본 파일명
					String originFileName = file.getOriginalFilename();
					// 새로운 파일명
					String storedFileName = UUID.randomUUID().toString() + "_" + originFileName;
					// 파일 저장 경로 + 새로운 파일명
					String storedFilePath = "C:\\Users\\WD\\board_file\\" + storedFileName;
					// 파일 크기
					long fileSize = file.getSize();
					// FileDTO 생성
					FileDTO fileDTO = new FileDTO();
					fileDTO.setBoardId(boardId);
					fileDTO.setFileSize(fileSize);
					fileDTO.setOriginFileName(originFileName);
					fileDTO.setStoredFilePath(storedFilePath);
					// FileDTO 리스트에 추가
					fileList.add(fileDTO);
					System.out.println(fileList);
					// 파일 저장
					try {
						File dest = new File(storedFilePath);
						file.transferTo(dest);						
					} catch(IOException e) {
						throw new Exception("파일 업로드 중 오류가 발생");
					}
				}
			}
			if (!fileList.isEmpty()) {
				fileMapper.insertFile(fileList);
			}
		}
	}
	
	@Override
	public BoardDTO selectBoardById(int id) throws Exception {
		BoardDTO boardDTO = boardMapper.selectBoardById(id);
		List<FileDTO> fileList = fileMapper.selectFileListByBoardId(id);
		boardDTO.setFileList(fileList);
		boardMapper.updateHit(id);
		return boardDTO;
	}

	@Override
	public void updateBoard(BoardDTO boardDTO) throws Exception {
		boardMapper.updateBoard(boardDTO);
	}

	@Override
	public void deleteBoard(int id) throws Exception {
		boardMapper.deleteBoard(id);
	}

	
}
