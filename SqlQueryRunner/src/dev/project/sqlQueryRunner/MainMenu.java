package dev.project.sqlQueryRunner;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.sql.*;

@WebServlet("/mainmenu")
public class MainMenu extends HttpServlet {
	private static final long serialVersionUID = -8326171279234792506L;

	private void insertRecord(HttpServletResponse _response) throws IOException {
		_response.setContentType("text/html");
		Connection conn = Connector.createConnection();
		String query = "SHOW TABLES";
		PreparedStatement stt;
		ResultSet result = null;
		PrintWriter out = _response.getWriter();
		
		try {
			stt = conn.prepareStatement(query);
			result = stt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>");
		out.print("JSP | Select Table");
		out.println("</title>");
		out.println("</head>");
		out.println("</head>");
		out.println("<body>");
		out.println("<form action='InsertData' method='get'>");
		out.println("<h1>Select Table</label><h1>");
		out.println("<select name=\"choice\">");
		try {
			while (result.next()) {
				String resultElement = result.getString("Tables_in_sqlqueryexecuter");
				out.println("<option value=" + resultElement + ">" + resultElement + "</option>");
				out.print("<br>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.println("</submit>");
		out.println("<input type='submit' id='submit' style='margin-left:20px'>");
		out.println("</form>");
		out.println("</body>");		
		out.println("</html>");		
	}

	private void showStructure(HttpServletResponse _response) throws IOException {
		_response.setContentType("text/html");
		Connection conn = Connector.createConnection();
		String query = "SHOW TABLES";
		PreparedStatement stt;
		ResultSet result = null;
		PrintWriter out = _response.getWriter();
		
		try {
			stt = conn.prepareStatement(query);
			result = stt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>");
		out.print("JSP | Select Table");
		out.println("</title>");
		out.println("</head>");
		out.println("</head>");
		out.println("<body>");
		out.println("<form action='Structure' method='get'>");
		out.println("<h1>Select Table<h1>");
		out.println("<select name=\"choice\">");
		try {
			while (result.next()) {
				String resultElement = result.getString("Tables_in_sqlqueryexecuter");
				out.println("<option value=" + resultElement + ">" + resultElement + "</option>");
				out.print("<br>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.println("</submit>");
		out.println("<input type='submit' id='submit'>");
		out.println("</form>");
		out.println("</body>");		
		out.println("</html>");	
	}
	
	private void showData(HttpServletResponse _response) throws IOException {
		_response.setContentType("text/html");
		Connection conn = Connector.createConnection();
		String query = "SHOW TABLES";
		PreparedStatement stt;
		ResultSet result = null;
		PrintWriter out = _response.getWriter();
		
		try {
			stt = conn.prepareStatement(query);
			result = stt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>");
		out.print("JSP | Select Table");
		out.println("</title>");
		out.println("</head>");
		out.println("</head>");
		out.println("<body>");
		out.println("<form action='DataDisplayer' method='get'>");
		out.println("<h1>Select Table<h1>");
		out.println("<select name=\"choice\">");
		try {
			while (result.next()) {
				String resultElement = result.getString("Tables_in_sqlqueryexecuter");
				out.println("<option value=" + resultElement + ">" + resultElement + "</option>");
				out.print("<br>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.println("</submit>");
		out.println("<input type='submit' id='submit'>");
		out.println("</form>");
		out.println("</body>");		
		out.println("</html>");	
	}
	
	public void service(HttpServletRequest _request, HttpServletResponse _response) throws IOException, ServletException {
		String optionSelected = _request.getParameter("choice");
		switch (optionSelected) {
			case "create":
					getServletContext().getRequestDispatcher("/createTable.html").forward(_request, _response);
					break;
			case "insert":
					insertRecord(_response);
					break;
			case "structure":
					showStructure(_response);
					break;
			case "show":
					showData(_response);
					break;
			default:
					break;
		}
	}
	
}
