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
    //处理用户名
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取提交的用户名等数据
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String remember = request.getParameter("remember");
		User user = new User();
		user.setName(name);
		user.setPwd(pwd);
		HttpSession session = request.getSession();
		try {
			//创建业务逻辑对象，调用业务逻辑方法处理数据
			UserService userService = new UserServiceImpl();
			user = userService.login(user);
			//登录成功则状态保持：sesion记录用户对象
			session.setAttribute(User.SESSION_NAME, user);
			//写Cookie
			if(remember!=null) {
				Cookie cookie = new Cookie(User.SESSION_NAME, user.getId().toString());
				cookie.setMaxAge(10*24*60*60);
				response.addCookie(cookie);
			}
			
			//衔接jsp
			//跳转到进入登录时的地址，默认为首页
			//数据包：报文：报文头 报文主题
			//请求：refere：记录了上一次请求的地址  盗链
			
//			request.getHeader("refere");
			//转发到首页还是重定向 预防二次提交 重定向/doLogin

			response.sendRedirect(request.getContextPath()+"/list.do");
		}catch(RuleException re) {
			//数据回显
			session.setAttribute("user", user);
			session.setAttribute("message", re.getMessage());
			response.sendRedirect(request.getContextPath()+"/login.do");
		}
			catch (Exception e) {
			// 交给全局的异常处理过滤器
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
