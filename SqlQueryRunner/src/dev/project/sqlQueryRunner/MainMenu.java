package dev.project.sqlQueryRunner;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mainmenu")
public class MainMenu extends HttpServlet {
	
	public void service(HttpServletRequest _request, HttpServletResponse _response) throws IOException, ServletException {
		String optionSelected = _request.getParameter("choice");
//		switch (optionSelected) {
//		case "create":			
//			break;
//		case "insert":			
//			break;
//		case "structure":			
//			break;
//		case "show":			
//			break;
//		default:
//			break;
//		}
		if (optionSelected.equals("create")) {
			ServletContext sc = this.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/jsp/Create_Table.jsp");
			rd.include(_request, _response);
		}
//		PrintWriter writer =  _response.getWriter();
//		writer.print(optionSelected);
	}
}