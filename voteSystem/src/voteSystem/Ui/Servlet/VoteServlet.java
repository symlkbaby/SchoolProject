package voteSystem.Ui.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import voteSystem.Pojo.Subject;
import voteSystem.Service.SubjectService;
import voteSystem.Service.impl.SubjectServiceImpl;

/**
 * Servlet implementation class VoteServlet
 */

public class VoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1 通过request取得投票项目id
			int id = Integer.parseInt(request.getParameter("id"));
			//2 从数据库拿到
			SubjectService subjectService = new SubjectServiceImpl();
			Subject subject= subjectService.getSubject(id);
			//转发到reg.jsp
			request.setAttribute("subject", subject);
			request.getRequestDispatcher("/WEB-INF/pages/vote.jsp")
			       .forward(request, response);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
