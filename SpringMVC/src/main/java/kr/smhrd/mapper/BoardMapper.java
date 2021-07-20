package kr.smhrd.mapper;

import java.util.List;

import kr.smhrd.domain.BoardVO;

public interface BoardMapper {
	// SQL --> X --> Mapper XML 파일에 sql문 작성함
	public List<BoardVO> boardList(); 
		
	}

