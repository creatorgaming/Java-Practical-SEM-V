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

@WebServlet("/DataDisplayer")
public class DataDisplayer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tableName = request.getParameter("choice");
		Connection conn = Connector.createConnection();
		PreparedStatement stt;
		PreparedStatement stt_col;
		ResultSet result = null;
		ResultSet result_col = null;
		PrintWriter out = response.getWriter();
		
		String query = "SELECT * FROM " + tableName;
		String query_col = "DESC " + tableName;
		
		try {
			stt_col = conn.prepareStatement(query_col);
			stt = conn.prepareStatement(query);
			result_col = stt_col.executeQuery();
			result = stt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		response.setContentType("text/html");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>");
		out.print("JSP | Table Data");
		out.println("</title>");
		out.println("</head>");
		out.println("</head>");
		out.println("<body>");
		out.println("<center><h1>" + tableName + " Data<h1>");
		out.println("<table border='1' cellpadding=\"10\" width=\"900\">");
		
		
		try {
			out.println("<tr>");
			while(result_col.next()) {
				out.println("<td>" + result_col.getString("Field") + "</td>");
			}
			out.println("</tr>");
						
			while(result.next()) {
				out.println("<tr>");
				result_col.beforeFirst();
				while(result_col.next()) {
					out.println("<td>" + result.getString(result_col.getString("Field")) + "</td>");
				}
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
