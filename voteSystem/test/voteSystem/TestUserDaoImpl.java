package voteSystem;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import voteSystem.Dao.impl.UserDaoimpl;
import voteSystem.Pojo.User;
import voteSystem.Pojo.UserQueryModel;

public class TestUserDaoImpl {

	@Test
	public void testInsert() throws Exception{
		//1创建目标类的对象，2调用目标方法，得到实际运行的结果
		UserDaoimpl userDao = new UserDaoimpl();
		User user = new User();
		user.setName("Rose");
		user.setPwd("123456");
		user.setOnline(1); 
		int actual = userDao.insert(user);
		//写下来预计的结果
		int expected = 1;
		//比较预计的结果和实际运行的结果
		Assert.assertEquals(expected, actual);
	}
	@Test
	public void testFindAll() throws Exception{
		UserDaoimpl userDao =  new UserDaoimpl();
		List actual = userDao.findAll();
		int expected = 2;
		Assert.assertEquals(expected,actual.size());
	}
	
	public void testFindByCondition() throws Exception{
		UserDaoimpl userDao =  new UserDaoimpl();
		UserQueryModel qm = new UserQueryModel();
		qm.setName("Jack");
		List actual = userDao.findAll();
		int expected = 6;
		Assert.assertEquals(expected,actual.size());
	}
	@Test
	public void testUpdate() throws Exception{
		UserDaoimpl userDao = new UserDaoimpl();
		User user= new User();
		user.setId(9);
		user.setName("关羽");
		user.setPwd("654321");
		user.setOnline(1);
		int actual = userDao.update(user);
		int ex = 1;
		Assert.assertEquals(ex, actual);
	}
	@Test
	public void testDelete() throws Exception{
		UserDaoimpl userDao = new UserDaoimpl();
		User user = new User();
		user.setId(1);
		int actual = userDao.delete(user);
		int ex = 1;
		Assert.assertEquals(ex, actual);
	}
}
