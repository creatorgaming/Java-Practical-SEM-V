package dev.project.sqlQueryRunner;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainMenu extends HttpServlet {
	
	public void service(HttpServletRequest _request, HttpServletResponse _response) {
		String optionSelected = _request.getParameter("choice");
		System.out.println(optionSelected);
	}
}
