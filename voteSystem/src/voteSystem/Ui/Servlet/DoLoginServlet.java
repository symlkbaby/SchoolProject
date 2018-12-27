package voteSystem.Ui.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import voteSystem.Pojo.User;
import voteSystem.Service.UserService;
import voteSystem.Service.impl.UserServiceImpl;
import voteSystem.util.exception.RuleException;

/**
 * Servlet implementation class DoLoginServlet
 */

public class DoLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //�����û���
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//��ȡ�ύ���û���������
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String remember = request.getParameter("remember");
		User user = new User();
		user.setName(name);
		user.setPwd(pwd);
		HttpSession session = request.getSession();
		try {
			//����ҵ���߼����󣬵���ҵ���߼�������������
			UserService userService = new UserServiceImpl();
			user = userService.login(user);
			//��¼�ɹ���״̬���֣�sesion��¼�û�����
			session.setAttribute(User.SESSION_NAME, user);
			//дCookie
			if(remember!=null) {
				Cookie cookie = new Cookie(User.SESSION_NAME, user.getId().toString());
				cookie.setMaxAge(10*24*60*60);
				response.addCookie(cookie);
			}
			
			//�ν�jsp
			//��ת�������¼ʱ�ĵ�ַ��Ĭ��Ϊ��ҳ
			//���ݰ������ģ�����ͷ ��������
			//����refere����¼����һ������ĵ�ַ  ����
			
//			request.getHeader("refere");
			//ת������ҳ�����ض��� Ԥ�������ύ �ض���/doLogin

			response.sendRedirect(request.getContextPath()+"/list.do");
		}catch(RuleException re) {
			//���ݻ���
			session.setAttribute("user", user);
			session.setAttribute("message", re.getMessage());
			response.sendRedirect(request.getContextPath()+"/login.do");
		}
			catch (Exception e) {
			// ����ȫ�ֵ��쳣���������
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
