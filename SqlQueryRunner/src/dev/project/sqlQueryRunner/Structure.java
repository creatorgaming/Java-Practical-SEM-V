package dev.project.sqlQueryRunner;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Structure
 */
@WebServlet("/Structure")
public class Structure extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tableName = request.getParameter("choice");
		Connection conn = Connector.createConnection();
		PreparedStatement stt;
		ResultSet result = null;
		PrintWriter out = response.getWriter();
		
		String query = "DESC " + tableName;
		try {
			stt = conn.prepareStatement(query);
			result = stt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		response.setContentType("text/html");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>");
		out.print("JSP | Table Structure");
		out.println("</title>");
		out.println("</head>");
		out.println("</head>");
		out.println("<body>");
		out.println("<center><h1>" + tableName +" STRUCTURE<h1>");
		out.println("<table border='1' cellpadding=\"10\" width=\"900\">");

		out.println("<tr>");
		out.println("<td>Field</td>");
		out.println("<td>Type</td>");
		out.println("<td>Null</td>");
		out.println("<td>Key</td>");
		out.println("<td>Default</td>");
		out.println("<td>Extra</td>");
		out.println("</tr>");
		
		try {
			while (result.next()) {
				out.println("<tr>");
				out.println("<td>" + result.getString("Field") + "</td>");
				out.println("<td>" + result.getString("Type") + "</td>");
				out.println("<td>" + result.getString("Null") + "</td>");
				out.println("<td>" + result.getString("Key") + "</td>");
				out.println("<td>" + result.getString("Default") + "</td>");
				out.println("<td>" + result.getString("Extra") + "</td>");
				out.println("</tr>");			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		out.println("</table>");
		out.println("<form action='index.html'>");
		out.print("<input type='submit' name='submit' value='Return ->' style='margin:80px;'>");
		out.println("</form>");
		out.println("</center>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
