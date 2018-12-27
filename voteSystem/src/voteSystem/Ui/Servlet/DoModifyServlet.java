package voteSystem.Ui.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import voteSystem.Pojo.Subject;
import voteSystem.Service.SubjectService;
import voteSystem.Service.impl.SubjectServiceImpl;

/**
 * Servlet implementation class DoModifyServlet
 */
@WebServlet("/m/doModify.do")
public class DoModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       int id = Integer.parseInt(request.getParameter("id"));
		
		try {
			SubjectService subjectService = new SubjectServiceImpl();
			Subject subject = subjectService.getSubject(id);
			
			request.setAttribute("subject", subject);
			request.getRequestDispatcher("/WEB-INF/pages/add.jsp")
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
		doGet(request, response);
	}

}
