package voteSystem.Service;

import voteSystem.Pojo.User;

public interface UserService {

	public void register(User user) throws Exception;
	//�����û����������ѯ�û�
	//����ֵ���ǲ�ѯ���Ķ���
	public User login(User user) throws Exception;
	public User getUser(int id) throws Exception;
	public void online(User user,boolean inOrOut);
}
