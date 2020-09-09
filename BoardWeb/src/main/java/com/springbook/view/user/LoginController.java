package com.springbook.view.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springbook.biz.user.UserVO;
import com.springbook.biz.user.impl.UserDAO;

// post와 get방식의 method 각각 따로 만들기
// get only for view(login.jsp)
// post for seding user info, DB connection...

// * method= RequestMethod.GET에서 대문자로 쓰는 이유는 enum이라서.. 
//	public enum RequestMethod {
//		GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE
//	}

@Controller
public class LoginController {

	@RequestMapping(value = "login.do", method = RequestMethod.GET)
	public String loginView(@ModelAttribute("user") UserVO vo) {
		// Command 객체 이름은 클래스 이름의 첫글자를 소문자로 변경하여 자동으로 설정된다.
		// => 예) userVO
		// 객체 이름으로 *.jsp에서 멤버변수를 getter로 호출할 수 있으므로
		// 이름을 바꾸고 싶다면 @ModelAttribute 사용할것
		// => 지금 설정되어있는 것은 user

		System.out.println("로그인 처리");

		vo.setId("test");
		vo.setPassword("1234"); // null인 객체를 줄 수는 없으니 db값 하나 넣어준다.

		return "login.jsp";
	}

	@RequestMapping(value = "login.do", method = RequestMethod.POST)
	public String login(UserVO vo, UserDAO userDAO, HttpSession session) {

		System.out.println("로그인 처리");

		// 아이디 입력 안하거나 null일때 강제로 예외 발생시켜서 예외 처리!
		if (vo.getId() == null || vo.getId().equals("")) {
			throw new IllegalArgumentException("아이디는 반드시 입력해야 합니다.");
		}
		UserVO user = userDAO.getUser(vo);

		if (userDAO.getUser(vo) != null) {
			session.setAttribute("userName", user.getName());
			return "redirect:getBoardList.do";
		} else {
			return "redirect:login.jsp";
		}
	}

}
