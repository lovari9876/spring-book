package com.springbook.view.controller;

import java.util.HashMap;
import java.util.Map;

import com.springbook.biz.board.DeleteBoardController;
import com.springbook.biz.board.GetBoardController;
import com.springbook.biz.board.GetBoardListController;
import com.springbook.biz.board.InsertBoardController;
import com.springbook.biz.board.UpdateBoardController;
import com.springbook.view.user.LoginController;
import com.springbook.view.user.LogoutController;

// 와 대박 너무 깔쌈하다...
// 요청을 handle해줄 Handler, 즉 Controller를 입력한 String path와 key, value로 mapping해주는거구나..
// 기본생성자에 map객체 초기화되게 해두고 get()으로 해당 path에 적합한 Controller 리턴하네... 넘 신기하다.
public class HandlerMapping {

	private Map<String, Controller> mappings;

	public HandlerMapping() {
		mappings = new HashMap<String, Controller>();
		mappings.put("/login.do", new LoginController());
		mappings.put("/logout.do", new LogoutController());
		mappings.put("/getBoardList.do", new GetBoardListController());
		mappings.put("/getBoard.do", new GetBoardController());
		mappings.put("/insertBoard.do", new InsertBoardController());
		mappings.put("/deleteBoard.do", new DeleteBoardController());
		mappings.put("/updateBoard.do", new UpdateBoardController());
		}

	public Controller getController(String path) {
		return mappings.get(path);
	}
}
