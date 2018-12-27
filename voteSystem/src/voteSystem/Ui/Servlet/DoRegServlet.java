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

		//1.获取浏览器提交的数据
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String confirmPwd = request.getParameter("confirmPwd");
		//创建实体类对象，将数据保存到对象的属性中间去
		User user = new User();
		user.setName(name);
		user.setPwd(pwd);
		user.setConfirmPwd(confirmPwd);
		try {
			//2 创建业务逻辑对象，调用业务方法处理数据
			UserService service = new UserServiceImpl(); 
			service.register(user);
			
			//3 衔接JSP：重定向到/login登录页面
			response.sendRedirect(request.getContextPath()+"/login.do");
			
		}catch(RuleException re){
			//因为用户操作不当违反业务规则
			//进行数据回显：还是发回注册表单  reg.jsp
			//通过域对象传回接收到的数据，错误原因
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setAttribute("message", re.getMessage());
			response.sendRedirect(request.getContextPath()+"/reg.do");		
			
		}catch (Exception e) {	
			//TODO  统一异常处理
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
