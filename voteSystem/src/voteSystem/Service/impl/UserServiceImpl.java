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
 * 业务逻辑的实现类
 * 1.实现接口
 * 2.业务逻辑的访问类
 * 事务处理：做一件事情，有多个步骤，并且多个步骤构成了个整体，
 *           多个步骤要么全部成功要么全部失败。
 *           扩展：redis缓存数据库（芒果）读写效率高
 *           No sql
 *           传统数据库支持事务性处理所以效率低
 *           非事务性数据就放在芒果数据库中
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
		//1.检查用户名不能为空
		//2.用户操作不当而违反操作规则的异常类
		if(user.getName()==null || 
				user.getName().trim().length()==0) {
			//抛出异常程序不会往下走
			throw new RuleException("用户名不能为空");
		}
		if(user.getPwd()==null ||user.getPwd().equals("")) {
			throw new RuleException("密码不能为空");
		}
		//密码啊等等..
		if(!user.getPwd().equals(user.getConfirmPwd())) {
			throw new RuleException("密码必须和确认密码一致");
		}
		//用户名不能重复：
		UserQueryModel qm = new UserQueryModel();
		qm.setName(user.getName());
		List list = userDao.findByCondition(qm);
		if(list.size()>0) {
			throw new RuleException("用户名已经被注册");
		}
		//
		user.setOnline(1);
		//md5加密
		user.setPwd(Md5Class.stringToMd5(user.getPwd()));
		//向数据库中新增用户数据
		userDao.insert(user);
	}

	@Override
	public User login(User user) throws Exception {
		// TODO Auto-generated method stub
		//用户已经在线就不能再登录
		//根据用户名和密码去查询用户
		UserQueryModel qm = new UserQueryModel();
		qm.setName(user.getName());
		qm.setPwd(Md5Class.stringToMd5(user.getPwd()));
		List list = userDao.findByCondition(qm);
		if(list.size() == 1) {
			user = (User)list.get(0);
			if(user.getOnline()==User.ONLINE) {
				throw new RuleException("该用户已经登录");
			}
			//修改在线状态为已在线
			user.setOnline(User.ONLINE);
			userDao.update(user);
		}else {
			throw new RuleException("没有该用户名或密码错误");
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
