package jp.jc21.t.yoshizawa.WEB01;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Sentiment implementation class ResultServlet
 */
@WebServlet("/result2")
public class ResultServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ResultServlet2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String string = "Stepover Toehold With Facelock";
		try {
			SLanguage result = Sentiment.getLanguage(string);
			ConfidenceScores message = result.documents[0].confidenceScores;
			double negative =message.negative; 
			double neutral =message.neutral; 
			double positive =message.positive;
			
         	request.setAttribute("negative", negative);
			request.setAttribute("neutral", neutral);
			request.setAttribute("positive", positive);
			
			request.setAttribute("message", message);
			request.getRequestDispatcher("/WEB-INF/jsp/result2.jsp").forward(request, response);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String string = request.getParameter("string");
		request.setAttribute("string", string);

		try {
			SLanguage result = Sentiment.getLanguage(string);
			ConfidenceScores message = result.documents[0].confidenceScores;
			double negative =message.negative; 
			double neutral =message.neutral; 
			double positive =message.positive;
			
         	request.setAttribute("negative", negative);
			request.setAttribute("neutral", neutral);
			request.setAttribute("positive", positive);
			
			request.setAttribute("message", message);
			request.getRequestDispatcher("/WEB-INF/jsp/result2.jsp").forward(request, response);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
