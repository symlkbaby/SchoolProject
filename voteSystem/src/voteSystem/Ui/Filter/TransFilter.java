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
			//1��������
			DbHelper.beginTransaction();
			//2����
			chain.doFilter(request, response);
			//3�ύ����
			DbHelper.commitTransaction();
			//4�ر����ݿ�����
			DbHelper.close();
		} catch (Exception e) {
			//5������쳣�ͻع�
			try {
				DbHelper.rollbackTransaction();
			} catch (Exception e1) {
				throw new RuntimeException(e1);
			}
			//���׳��쳣������ȫ���쳣���������
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
