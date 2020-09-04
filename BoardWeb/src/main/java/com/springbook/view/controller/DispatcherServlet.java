package com.springbook.view.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private HandlerMapping handlerMapping;
	private ViewResolver viewResolver;

	@Override
	public void init() throws ServletException {
		handlerMapping = new HandlerMapping();
		viewResolver = new ViewResolver();
		viewResolver.setPrefix("./");
		viewResolver.setSufix(".jsp");
	}

	// get 방식 요청 처리
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	// post 방식 요청 처리
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 한글 깨짐 방지.. 이제 개별 VIEW 페이지에서 인코딩 처리 안해줘도 된다.
		request.setCharacterEncoding("UTF-8");
		process(request, response);
	}

	// 실제로 일처리하는 메서드
	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {

		// 1. 클라이언트의 요청 path 정보를 추출한다.
		String uri = request.getRequestURI();
		// Returns the part of this request's URL from the protocolname up to the query
		// string in the first line of the HTTP request.
		String path = uri.substring(uri.lastIndexOf("/")); // 마지막 /부터의 문자열을 path로 지정
		System.out.println(path);

		// 2. HandlerMapping을 통해 path에 해당하는 Controller를 검색한다.
		Controller ctrl = handlerMapping.getController(path);

		// 3. 검색된 Controller를 실행한다.
		String viewName = ctrl.handleRequest(request, response);

		// 4. viewResolver를 통해 viewName에 해당하는 화면을 검색한다.
		String view = null;
		if (!viewName.contains(".do")) {
			view = viewResolver.getView(viewName);
		} else {
			view = viewName;
		}

		// 5. 검색된 화면으로 이동한다.
		response.sendRedirect(view); // redirect url을 client에게 보낸다.
		// url이 바뀌게 되니까, *.jsp면 그냥 화면 띄우는데, *.do일때는 web.xml에 설정한 대로 action으로 naming한
		// DispatcherServlet을 다시 실행하게 되는 것이야!

	}

}
