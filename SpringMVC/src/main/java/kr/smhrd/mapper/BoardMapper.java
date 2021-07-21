package kr.smhrd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.smhrd.domain.BoardVO;
@Mapper
public interface BoardMapper {
   //SQL -- X --> SQL Mapper XML File(XML)
   public List<BoardVO> boardList(); 
   public void boardInsert(BoardVO vo);  // insert
}

