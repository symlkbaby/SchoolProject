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
 * Servlet implementation class ListServlet
 */

public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//2 创建业务逻辑对象，调用业务方法获取结果
			SubjectService subjectService = new SubjectServiceImpl();
			List<Subject> list = subjectService.getSubjects();
			
			//3 衔接JSP
			request.setAttribute("subjects", list);
			request.getRequestDispatcher("/WEB-INF/pages/list.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
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
