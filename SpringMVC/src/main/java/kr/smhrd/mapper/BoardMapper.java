package kr.smhrd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.smhrd.domain.BoardVO;
@Mapper
public interface BoardMapper {
   //SQL -- X --> SQL Mapper XML File(XML)
   public List<BoardVO> boardList(); 
   @Select("select * from tbl_board order by idx desc")
   public List<BoardVO> boardListAjax();
   
   public void boardInsert(BoardVO vo);  // insert SQL~
   
   @Select("select * from tbl_board where idx = #{idx}")
   public BoardVO boardContent(int idx); // select SQL~
   public void boardDelete(int idx);
   public void boardUpdate(BoardVO vo);
   public int boardDeleteAjax(int idx);
}

