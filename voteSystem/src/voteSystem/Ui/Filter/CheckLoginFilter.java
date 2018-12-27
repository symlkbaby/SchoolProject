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

		//���˵�һЩ����Ҫ�˹��ܵ������ַ
//		if(req.getRemoteAddr().contains("/reg")){
//			chain.doFilter(request, response);
//		}
		//������������web.xml�ļ�������
		// url-pattern: /m/*
		
		
		//����Ƿ��¼(session ��ȡ�û�����)
		
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
