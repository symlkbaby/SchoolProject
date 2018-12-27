package voteSystem.Ui.Filter;
/**
 * 全局的异常处理过滤器
 * 一般设置为过滤器链上的第一个过滤器
 */
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
public class MyExceptionFilter implements Filter {

	//错误处理页面的地址
	private String targetUrl;
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try{
			chain.doFilter(request, response);
		}catch(Exception ex){
			//写统一
			//日志 ，Log4J的代码记录日志
			//跳转到错误处理页面
			//将异常对象保存到域对象中
			request.setAttribute("ex", ex);
			//转发
			request.getRequestDispatcher(this.targetUrl).forward(request, response);
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	//读取web.xml中配置的文件名
		//该文件用于保存异常信息
		//log4j
		//web.xml文件中配置跳转的目标页面
		this.targetUrl = filterConfig.getInitParameter("targetUrl");
	}

}
