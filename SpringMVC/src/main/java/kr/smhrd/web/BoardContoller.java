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
   @RequestMapping("/boardForm.do")
   public String boardForm() {
	    return "boardForm"; //boardForm.jsp
   } 
   @RequestMapping("/boardInsert.do")
   public String boardInsert(BoardVO vo) {
	   boardMapper.boardInsert(vo);
	   return "redirect:/boardList.do";
   }
   
}