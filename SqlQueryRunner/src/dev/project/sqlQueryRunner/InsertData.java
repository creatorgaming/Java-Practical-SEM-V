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

@WebServlet("/InsertData")
public class InsertData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Connection conn = null;
	private PreparedStatement stt = null;
	private ResultSet result = null;
	private PrintWriter out = null;
       
	private void getTableAttributes(String tableName) {
		String query = "SELECT COLUMN_NAME, DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS "
				 + "WHERE TABLE_NAME = '" + tableName + "' "
				 + "ORDER BY ORDINAL_POSITION\r\n";
		try {
			stt = conn.prepareStatement(query);
			result = stt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String tableName = request.getParameter("choice");
		
		conn = Connector.createConnection();
		out = response.getWriter();
		
		getTableAttributes(tableName);
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>");
		out.print("JSP | Insert Data");
		out.println("</title>");
		out.println("</head>");
		out.println("</head>");
		out.println("<body>");
		out.println("<form action='InsertData' method='POST'>");
		out.println("<h1>Enter Data</h1>");
		out.println("<label style='margin-right:90px;font-weight:bold;font-size:20px;'>Column Name</label>");
		out.println("<label style='margin-right:100px;font-weight:bold;font-size:20px;'>Value</label><br>");
		out.println("<input type='hidden' name=\'tableName\' value='" + tableName + "'>");
		try {
			while(result.next()) {
				out.println("<label style='margin-right:100px;margin-top:50px;'>" + result.getString("COLUMN_NAME") + "</label>");
				out.println("<input type='" + getType(result.getString("DATA_TYPE")) + "' name='" + result.getString("COLUMN_NAME") + "'>");
				out.print("<br>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.println("<input type='submit' id='submit'>");
		out.println("</form>");
		out.println("</body>");		
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tableName = request.getParameter("tableName");
		Connection conn = Connector.createConnection();
		PreparedStatement stt;
		
		getTableAttributes(tableName);
		
		String columns = " (";
		String values = " (";
		
		try {
			while (result.next()) {
				columns += "'" + result.getString("COLUMN_NAME") + "',";
				values += "'" + request.getParameter(result.getString("COLUMN_NAME")) + "',";
			}
			columns = columns.substring(0, columns.length() - 1);
			values = values.substring(0, values.length() - 1);
			columns += ") ";
			values += ") ";
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
		String query = "INSERT INTO `" + tableName + "`" + "VALUES" + values;
		System.out.println(query);
		try {
			stt = conn.prepareStatement(query);
			stt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getServletContext().getRequestDispatcher("/index.html").forward(request, response);
	}

	private String getType(String data_type) {
		String type = null;
		switch (data_type) {
			case "int":
				type = "number";
				break;
			case "varchar":
				type = "text";
				break;
			case "date":
				type = "text";
				break;
			default:
				type = "text";
				break;
		}
		return type;
	}
}
