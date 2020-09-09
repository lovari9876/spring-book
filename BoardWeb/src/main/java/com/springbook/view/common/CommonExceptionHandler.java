package com.springbook.view.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

// xml에  bean 등록하는 방식으로 에러처리를 하기 위해, Annotation 모두 삭제

// @ControllerAdvice("com.springbook.view")
public class CommonExceptionHandler {

	// @ExceptionHandler(ArithmeticException.class)
	public ModelAndView handleArithmeticException(Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.setViewName("/common/arithmeticError.jsp");
		return mav;
	}

	// @ExceptionHandler(NullPointerException.class)
	public ModelAndView handleNullPointerException(Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.setViewName("/common/nullPointerError.jsp");
		return mav;
	}

	// @ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.setViewName("/common/error.jsp");
		return mav;
	}

}
