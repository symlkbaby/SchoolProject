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
		this.use = arg0.getServletContext();//服务器打开的时候取得一个实例化对象
		this.use.setAttribute("onlineuser", new TreeSet<String>());//设置一个空集合
	}

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		Set all = (Set)this.use.getAttribute("onlineuser");//取出设置的集合
		all.remove(arg0.getSession().getAttribute("userid"));//取出session中设置的内容
		this.use.setAttribute("onlineuser", all);//将更新的集合重新保存
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		//用户登录成功以后将用户加入集合中（用户列表中）
		@SuppressWarnings("unchecked")
		Set<Object> all = (Set<Object>)this.use.getAttribute("onlineuser");
		all.add(arg0.getValue());
		this.use.setAttribute("onlineuser", all);
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		//用户退出时将用户从集合中取出，用户列表不再显示
		Set all = (Set)this.use.getAttribute("onlineuser");
		all.remove(arg0.getValue());
		this.use.setAttribute("onlineuser", all);
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		
	}



}
