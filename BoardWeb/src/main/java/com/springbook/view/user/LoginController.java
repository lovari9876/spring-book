package com.springbook.view.user;

import org.springframework.stereotype.Controller;

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
	public String loginView(UserVO vo) {

		System.out.println("로그인 처리");

		vo.setId("test");
		vo.setPassword("1234");
		// test로 진입해서 그냥 기본적으로 목록 볼 수 있도록...

		return "login.jsp";
	}

	@RequestMapping(value = "login.do", method = RequestMethod.POST)
	public String login(UserVO vo, UserDAO userDAO) {

		System.out.println("로그인 처리");

		if (userDAO.getUser(vo) != null) {
			return "redirect:getBoardList.do";
		} else {
			return "redirect:login.jsp";
		}
	}

}
