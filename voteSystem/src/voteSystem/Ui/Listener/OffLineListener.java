package voteSystem.Ui.Listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import voteSystem.Pojo.User;
import voteSystem.Service.UserService;
import voteSystem.Service.impl.UserServiceImpl;

public class OffLineListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		HttpSession session = arg0.getSession();
		User user = (User)session.getAttribute(User.SESSION_NAME);
        //ÐÞ¸ÄÏÂÏß×´Ì¬
		UserService userService = new UserServiceImpl();
		userService.online(user,false);
	}

}
