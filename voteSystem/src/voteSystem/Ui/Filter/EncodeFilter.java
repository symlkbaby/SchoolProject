package voteSystem.Ui.Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import voteSystem.util.filter.MyRequest;

public class EncodeFilter implements Filter {
    //记录目标编码的名称
	private String encode;
	
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)req;
		//在servlet执行之前，设置request，response的CharacterEncoding
		req.setCharacterEncoding(encode);
		res.setCharacterEncoding(encode);
		
		//如果是GET方式提交的，代换req对象成为自己定义的请求类的子类对象
		if("GET".equals(request.getMethod().toUpperCase())){
			MyRequest mr  = new MyRequest(request);
			mr.setEncode(this.encode);
			req=mr;
		}
		
		chain.doFilter(req,res);
		

	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		//FilterConfig保存web.xml中配置在
		//过滤器中的初始化参数
		encode = config.getInitParameter("enc");

	}

}

