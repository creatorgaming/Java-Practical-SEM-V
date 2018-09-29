package dev.project.sqlQueryRunner;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mainmenu")
public class MainMenu extends HttpServlet {
	
	public void service(HttpServletRequest _request, HttpServletResponse _response) throws IOException {
		String optionSelected = _request.getParameter("choice");
		switch (optionSelected) {
		case "create":			
			break;
		case "insert":			
			break;
		case "structure":			
			break;
		case "show":			
			break;
		default:
			break;
		}
		PrintWriter writer =  _response.getWriter();
		writer.print(optionSelected);
	}
}
