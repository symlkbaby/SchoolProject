package voteSystem.Service;

import voteSystem.Pojo.User;

public interface UserService {

	public void register(User user) throws Exception;
	//根据用户名和密码查询用户
	//返回值就是查询到的对象
	public User login(User user) throws Exception;
	public User getUser(int id) throws Exception;
	public void online(User user,boolean inOrOut);
}
