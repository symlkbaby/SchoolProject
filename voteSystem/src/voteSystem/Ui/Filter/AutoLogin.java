package voteSystem.Ui.Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import voteSystem.Pojo.User;
import voteSystem.Service.UserService;
import voteSystem.Service.impl.UserServiceImpl;

/**
 * Servlet Filter implementation class AutoLogin
 */
@WebFilter("/*")
public class AutoLogin implements Filter {

    /**
     * Default constructor. 
     */
    public AutoLogin() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//�Զ���¼,��¼�Ľ������¼�û����ݱ�����session��
		//���û�е�¼���Զ���¼
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		if(session.getAttribute(User.SESSION_NAME)==null){
			//��ȡcookie�е��û�id
			Cookie[] cookies = req.getCookies();
			if(cookies!=null) {
				for(Cookie cookie:cookies) {
					//��ȡ�û�id
					if(User.SESSION_NAME.equals(cookie.getName())) {
						//��cookie�ж�ȡ�û�id
						int id = Integer.parseInt(cookie.getValue());
						try {
							//�����û�id��ȡ�û�����
							UserService userService = new UserServiceImpl();
							User user = userService.getUser(id);
							// ģ���¼�ɹ������û�����д�뵽session��
							userService.online(user, true);
							session.setAttribute(User.SESSION_NAME, user);
							break;
						} catch (Exception e) {
							// TODO Auto-generated catch block
							throw new RuntimeException(e);
						}
					}
				}
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
