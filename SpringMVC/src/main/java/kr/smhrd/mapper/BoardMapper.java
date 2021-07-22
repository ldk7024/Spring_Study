package kr.smhrd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.smhrd.domain.BoardVO;
@Mapper
public interface BoardMapper {
   //SQL -- X --> SQL Mapper XML File(XML)
   public List<BoardVO> boardList(); 
   public void boardInsert(BoardVO vo);  // insert SQL~
   
   @Select("select * from tbl_board where idx = #{idx}")
   public BoardVO boardContent(int idx); // select SQL~
   public void boardDelete(int idx);
}

