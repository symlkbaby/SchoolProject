package Filterservlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WebFilterServlet implements Filter{

	private String charset;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		arg0.setCharacterEncoding(this.charset);//±àÂë¹ýÂË
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpSession session = request.getSession();
		HttpServletResponse response = (HttpServletResponse)arg1;
		if(session.getAttribute("userid") == null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else {
			arg2.doFilter(arg0, arg1);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		this.charset = arg0.getInitParameter("charset");
	}

}
