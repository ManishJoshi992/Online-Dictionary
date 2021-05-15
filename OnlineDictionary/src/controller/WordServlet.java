package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DbConnection;
import model.Dictionary;

/**
 * Servlet implementation class WordServlet
 */
@WebServlet("/WordServlet")
public class WordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DbConnection dbcon;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WordServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() throws ServletException {
		dbcon = new DbConnection("jdbc:mysql://localhost:3306/entries", "root", "root@1234");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		// System.out.println(1);
		// String inputText = request.getParameter("searchText");

		String title = "Database Result";
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
		out.println(docType + "<html>\n" + "<head><title>" + title + "</title></head>\n"
				+ "<body bgcolor = \"#f0f0f0\">\n" + "<h1 align = \"center\">" + title + "</h1>\n");
		try {
			List<Dictionary> dictionaries = dbcon.listAll("Abacination");
			for (Dictionary dictionary : dictionaries) {
				out.println("word: " + dictionary.getWord() + " ");
				out.println(", wordType: " + dictionary.getWordType() + " ");
				out.println(", definition: " + dictionary.getDefinition() + "<br>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
