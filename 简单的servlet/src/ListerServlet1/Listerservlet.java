package ListerServlet1;

import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;



public class Listerservlet implements ServletContextListener,HttpSessionListener,HttpSessionAttributeListener{

	private ServletContext use = null;
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		this.use = arg0.getServletContext();//�������򿪵�ʱ��ȡ��һ��ʵ��������
		this.use.setAttribute("onlineuser", new TreeSet<String>());//����һ���ռ���
	}

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		Set all = (Set)this.use.getAttribute("onlineuser");//ȡ�����õļ���
		all.remove(arg0.getSession().getAttribute("userid"));//ȡ��session�����õ�����
		this.use.setAttribute("onlineuser", all);//�����µļ������±���
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		//�û���¼�ɹ��Ժ��û����뼯���У��û��б��У�
		@SuppressWarnings("unchecked")
		Set<Object> all = (Set<Object>)this.use.getAttribute("onlineuser");
		all.add(arg0.getValue());
		this.use.setAttribute("onlineuser", all);
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		//�û��˳�ʱ���û��Ӽ�����ȡ�����û��б�����ʾ
		Set all = (Set)this.use.getAttribute("onlineuser");
		all.remove(arg0.getValue());
		this.use.setAttribute("onlineuser", all);
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		
	}



}
