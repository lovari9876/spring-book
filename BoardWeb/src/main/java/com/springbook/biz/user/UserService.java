package com.springbook.biz.user;

public interface UserService {

	// CRUD 기능의 메소드 구현 ========================================
	// 회원 등록(근데 걍 횐정 불러오는건디)
	UserVO getUser(UserVO vo);
	// In an interface, if no access modifier is given, the method is implicitly public.

}