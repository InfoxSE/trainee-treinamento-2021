package br.com.infox.treinamento.trainee.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Landing Page Servlet", urlPatterns = {"/index.html"})
public class LandingPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try(ServletOutputStream outputStream = resp.getOutputStream()) {
			outputStream.println("<html>\n" +
					"<head>\n" +
					"<meta http-equiv=\"Refresh\" content=\"0; URL=index.jsf\">\n" +
					"</head>\n" +
					"</html>");
		}
	}


}
