package kr.smhrd.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.smhrd.domain.BoardVO;
import kr.smhrd.mapper.BoardMapper;

//POJO
@Controller
public class BoardContoller { //new BoardController(); => Spring Container9DI
   
   //@Autowired // defendency 인젝션 = DI
   @Inject //자동으로 인식
   //@Resource("boardMapper")
   private BoardMapper boardMapper;
   //게시판 리스트를 가져오는 동작
   @RequestMapping("/boardList.do") //핸들러 매핑 @
   public String boardList(Model model) {
      List<BoardVO> list = boardMapper.boardList(); //sessionFactory가 처리
      model.addAttribute("list", list); // 객체바인딩 ->ModelAndView->Model(*)
      return "boardList"; //  -->ViewResolver-->/WEB-INF/views/boardList.jsp
   }
   @RequestMapping("/boardListAjax.do") //핸들러 매핑 @
   public @ResponseBody List<BoardVO> boardListAjax() {
      List<BoardVO> list = boardMapper.boardList(); // 게시판 전체리스트 가져오기
      return list; // 객체를 리턴---{JSON API}-->String 변환 -->응답
   }
   @RequestMapping("/boardForm.do")
   public void boardForm() {
	    //boardForm.jsp
   } 
   @RequestMapping("/boardInsert.do")
   public String boardInsert(BoardVO vo) {  // 파라메터수집(자동) -> new BoardVO();
	   boardMapper.boardInsert(vo);
	   return "redirect:/boardList.do";
   }
   @RequestMapping("/boardContent.do")
   public String boardContent(int idx, Model model) { 
	   BoardVO vo = boardMapper.boardContent(idx);
	   model.addAttribute("vo",vo);
	   return "boardContent";
   }
   @RequestMapping("/boardDelete.do")
   public String boardDelete(int idx) { 
	  boardMapper.boardDelete(idx);
	   return "redirect:/boardList.do";
   }
   @RequestMapping("/boardUpdate.do")
   public String boardUpdate(BoardVO vo) { 
	  boardMapper.boardUpdate(vo);
	   return "redirect:/boardList.do";
   }
   @RequestMapping("/boardDeleteAjax.do")
   public @ResponseBody int boardDeleteAjax(int idx) { 
	  int cnt = boardMapper.boardDeleteAjax(idx);
	  return cnt;  // ajax --> 응답
	 
   }
   
}