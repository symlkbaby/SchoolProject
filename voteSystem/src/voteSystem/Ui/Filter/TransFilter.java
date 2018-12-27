package voteSystem.Ui.Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import voteSystem.util.Dao.DbHelper;

/**
 * Servlet Filter implementation class TransFilter
 */
@WebFilter("*.do")
public class TransFilter implements Filter {

    /**
     * Default constructor. 
     */
    public TransFilter() {
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

		try {
			//1开启事务
			DbHelper.beginTransaction();
			//2放行
			chain.doFilter(request, response);
			//3提交事务
			DbHelper.commitTransaction();
			//4关闭数据库连接
			DbHelper.close();
		} catch (Exception e) {
			//5如果有异常就回滚
			try {
				DbHelper.rollbackTransaction();
			} catch (Exception e1) {
				throw new RuntimeException(e1);
			}
			//再抛出异常，交给全局异常处理过滤器
			throw new RuntimeException(e);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
