package voteSystem.Ui.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import voteSystem.Pojo.User;
import voteSystem.Service.UserService;
import voteSystem.Service.impl.UserServiceImpl;
import voteSystem.util.exception.RuleException;

/**
 * Servlet implementation class DoRegServlet
 */

public class DoRegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public DoRegServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//1.��ȡ������ύ������
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String confirmPwd = request.getParameter("confirmPwd");
		//����ʵ������󣬽����ݱ��浽����������м�ȥ
		User user = new User();
		user.setName(name);
		user.setPwd(pwd);
		user.setConfirmPwd(confirmPwd);
		try {
			//2 ����ҵ���߼����󣬵���ҵ�񷽷���������
			UserService service = new UserServiceImpl(); 
			service.register(user);
			
			//3 �ν�JSP���ض���/login��¼ҳ��
			response.sendRedirect(request.getContextPath()+"/login.do");
			
		}catch(RuleException re){
			//��Ϊ�û���������Υ��ҵ�����
			//�������ݻ��ԣ����Ƿ���ע���  reg.jsp
			//ͨ������󴫻ؽ��յ������ݣ�����ԭ��
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setAttribute("message", re.getMessage());
			response.sendRedirect(request.getContextPath()+"/reg.do");		
			
		}catch (Exception e) {	
			//TODO  ͳһ�쳣����
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doGet(request, response);
	}

}
