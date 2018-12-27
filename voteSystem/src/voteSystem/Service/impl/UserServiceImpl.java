package voteSystem.Service.impl;

import java.util.List;

import voteSystem.Dao.UserDao;
import voteSystem.Dao.impl.UserDaoimpl;
import voteSystem.Pojo.User;
import voteSystem.Pojo.UserQueryModel;
import voteSystem.Service.UserService;
import voteSystem.util.exception.RuleException;
import voteSystem.util.format.Md5Class;

/**
 * ҵ���߼���ʵ����
 * 1.ʵ�ֽӿ�
 * 2.ҵ���߼��ķ�����
 * ��������һ�����飬�ж�����裬���Ҷ�����蹹���˸����壬
 *           �������Ҫôȫ���ɹ�Ҫôȫ��ʧ�ܡ�
 *           ��չ��redis�������ݿ⣨â������дЧ�ʸ�
 *           No sql
 *           ��ͳ���ݿ�֧�������Դ�������Ч�ʵ�
 *           �����������ݾͷ���â�����ݿ���
 * @author lk
 *
 */
public class UserServiceImpl implements UserService{

	private UserDao userDao;
	 public UserServiceImpl() {
		userDao = new UserDaoimpl();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void register(User user) throws Exception {
		// TODO Auto-generated method stub
		//1.����û�������Ϊ��
		//2.�û�����������Υ������������쳣��
		if(user.getName()==null || 
				user.getName().trim().length()==0) {
			//�׳��쳣���򲻻�������
			throw new RuleException("�û�������Ϊ��");
		}
		if(user.getPwd()==null ||user.getPwd().equals("")) {
			throw new RuleException("���벻��Ϊ��");
		}
		//���밡�ȵ�..
		if(!user.getPwd().equals(user.getConfirmPwd())) {
			throw new RuleException("��������ȷ������һ��");
		}
		//�û��������ظ���
		UserQueryModel qm = new UserQueryModel();
		qm.setName(user.getName());
		List list = userDao.findByCondition(qm);
		if(list.size()>0) {
			throw new RuleException("�û����Ѿ���ע��");
		}
		//
		user.setOnline(1);
		//md5����
		user.setPwd(Md5Class.stringToMd5(user.getPwd()));
		//�����ݿ��������û�����
		userDao.insert(user);
	}

	@Override
	public User login(User user) throws Exception {
		// TODO Auto-generated method stub
		//�û��Ѿ����߾Ͳ����ٵ�¼
		//�����û���������ȥ��ѯ�û�
		UserQueryModel qm = new UserQueryModel();
		qm.setName(user.getName());
		qm.setPwd(Md5Class.stringToMd5(user.getPwd()));
		List list = userDao.findByCondition(qm);
		if(list.size() == 1) {
			user = (User)list.get(0);
			if(user.getOnline()==User.ONLINE) {
				throw new RuleException("���û��Ѿ���¼");
			}
			//�޸�����״̬Ϊ������
			user.setOnline(User.ONLINE);
			userDao.update(user);
		}else {
			throw new RuleException("û�и��û������������");
		}
		return user;
	}

	@Override
	public User getUser(int id) throws Exception {
		// TODO Auto-generated method stub
		return (User)userDao.findOne(id);
	}

	@Override
	public void online(User user, boolean inOrOut) {
		try {
			if(inOrOut){
				user.setOnline(2);
			}
			else{
			    user.setOnline(1);
			}
			
			userDao.update(user);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
