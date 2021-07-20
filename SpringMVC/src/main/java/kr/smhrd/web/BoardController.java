package kr.smhrd.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// POJO
@Controller
public class BoardController {   // new BoardController(); -> spring Container (DI)
	// 게시판 리스트를 가져오는 동작
	// HandlerMapping
	@RequestMapping("/boardList.do")
	public String boardList(Model model) {
		List<String> list = new ArrayList<String>();
		list.add("사과");
		list.add("바나나");
		list.add("오렌지");
		list.add("포도");
		list.add("귤");
		model.addAttribute("list", list); // 객체바인딩 -> ModelAndView -> Model(*)
	return "boardList";	 // ---> ViewResolver/WEB-INF/views/boardList.jsp
	}
	
}
