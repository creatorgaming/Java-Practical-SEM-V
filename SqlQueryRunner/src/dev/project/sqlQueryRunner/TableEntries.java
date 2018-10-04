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
    public TableEntries() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		colNo = Integer.parseInt(request.getParameter("cols"));
		tableName = request.getParameter("tablename");
		PrintWriter out = response.getWriter();
		sendPage(out);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tableNamePost = request.getParameter("tableName");
		int columnNo = Integer.parseInt(request.getParameter("colno"));
		ArrayList<String> colName = new ArrayList<String>();
		ArrayList<String> colType = new ArrayList<String>();
		ArrayList<String> colLength = new ArrayList<String>();
		PrintWriter out = response.getWriter();
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
			query += colName.get(i) + colType.get(i) + "(" + colLength.get(i) + "),"; 
			i++;
		}
		query += ")";
		PreparedStatement stt;
		try {
			stt = conn.prepareStatement(query);
			boolean execute = stt.execute();
			if(execute) {
				System.out.println("TRUE");
			}else {
				System.out.println("FALSE");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void sendPage(PrintWriter out) {
		out.println("<html>");
		out.println("<head>");
		out.println("<title>");
		out.print("JSP | TableEntries");
		out.println("</title>");
		out.println("</head>");
		out.println("</head>");
		out.println("<body>");
		out.println("<form action='tableEntries' method='get'>");
		int i = 1;
		out.println("<input type='hidden' name=\'colno\' value='" + colNo + "'>");		
		out.println("<input type='hidden' name=\'tableName\' value='" + tableName + "'>");		
		out.println("<label>Column Name &nbsp&nbsp&nbsp&nbsp Column Type &nbsp&nbsp&nbsp&nbsp Length </label><br>");
		while (i <= colNo) {
			out.println("<input type='text' name=\'col" + i +"\'>");		
			out.println("<input type='text' name=\'type" + i +"\'>");		
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
