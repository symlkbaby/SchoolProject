package voteSystem.Ui.Filter;
/**
 * ȫ�ֵ��쳣���������
 * һ������Ϊ���������ϵĵ�һ��������
 */
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
public class MyExceptionFilter implements Filter {

	//������ҳ��ĵ�ַ
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
			//дͳһ
			//��־ ��Log4J�Ĵ����¼��־
			//��ת��������ҳ��
			//���쳣���󱣴浽�������
			request.setAttribute("ex", ex);
			//ת��
			request.getRequestDispatcher(this.targetUrl).forward(request, response);
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	//��ȡweb.xml�����õ��ļ���
		//���ļ����ڱ����쳣��Ϣ
		//log4j
		//web.xml�ļ���������ת��Ŀ��ҳ��
		this.targetUrl = filterConfig.getInitParameter("targetUrl");
	}

}
