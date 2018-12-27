package voteSystem.Ui.Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import voteSystem.Pojo.User;

/**
 * Servlet Filter implementation class CheckLoginFilter
 */
@WebFilter("/m/*")
public class CheckLoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public CheckLoginFilter() {
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
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;

		//过滤掉一些不需要此功能的请求地址
//		if(req.getRemoteAddr().contains("/reg")){
//			chain.doFilter(request, response);
//		}
		//过滤器必须在web.xml文件中配置
		// url-pattern: /m/*
		
		
		//检查是否登录(session 读取用户对象)
		
		HttpSession session = req.getSession();
		if(session.getAttribute(User.SESSION_NAME)==null){
			res.sendRedirect(req.getContextPath()+"/login.do");
			return;
		}
		
		chain.doFilter(req, res);
	}


	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
