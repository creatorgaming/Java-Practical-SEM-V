package dev.project.sqlQueryRunner;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;

@WebServlet("/tableEntries")
public class TableEntries extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int colNo = 0; 
	private	String tableName = null; 
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		colNo = Integer.parseInt(request.getParameter("cols"));
		tableName = request.getParameter("tablename");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		sendPage(out);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tableName = request.getParameter("tableName");
		int columnNo = Integer.parseInt(request.getParameter("colno"));
		response.setContentType("text/html");;
		
		ArrayList<String> colName = new ArrayList<String>();
		ArrayList<String> colType = new ArrayList<String>();
		ArrayList<String> colLength = new ArrayList<String>();
		@SuppressWarnings("unused")
		boolean execute = false;
//		PrintWriter out = response.getWriter();
		
		int i = 1;
		while (i <= columnNo) {
			colName.add(request.getParameter("col"+i));
			colType.add(request.getParameter("type"+i));
			colLength.add(request.getParameter("length"+i));
			i++;
		}
		
		i = 0;
		Connection conn = Connector.createConnection();
		String query = "CREATE TABLE " + tableName + "(";
		while (i < columnNo) {
			query += colName.get(i) + " " + colType.get(i) + "(" + colLength.get(i) + ")";
			if (i != columnNo - 1)
				query += ",";
			i++;
		}
		query += ")";

		PreparedStatement stt;
		try {
			stt = conn.prepareStatement(query);
			execute = stt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getServletContext().getRequestDispatcher("/index.html").forward(request, response);
	}
	
	private void sendPage(PrintWriter out) {
		int i = 1;
		out.println("<html>");
		out.println("<head>");
		out.println("<title>");
		out.print("JSP | TableEntries");
		out.println("</title>");
		out.println("</head>");
		out.println("</head>");
		out.println("<body>");
		out.println("<form action='tableEntries' method='get'>");
		out.println("<input type='hidden' name=\'colno\' value='" + colNo + "'>");		
		out.println("<input type='hidden' name=\'tableName\' value='" + tableName + "'>");		
		out.println("<label>Column Name &nbsp&nbsp&nbsp&nbsp Column Type &nbsp&nbsp&nbsp&nbsp Length </label><br>");
		while (i <= colNo) {
			out.println("<input type='text' name=\'col" + i +"\'>");		
			out.println("<select name=\'type" + i +"\'>");
			out.println("<option value='varchar'>varchar</option>");
			out.println("<option value='int'>int</option>");
			out.println("<option value='date'>date</option>");
			out.println("</select>");
			out.println("<input type='number' name=\'length" + i +"\'>");		
			out.print("<br>");
			i++;
		}
		out.println("<input type='submit' id='submit'>");
		out.println("</form>");
		out.println("</body>");		
		out.println("</html>");
	}
}
