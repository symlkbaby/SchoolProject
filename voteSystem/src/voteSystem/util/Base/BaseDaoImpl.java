package voteSystem.util.Base;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import voteSystem.util.Dao.DbHelper;

/**
 * 数据访问的父类，实现父接口中的增删改查方法
 * @author lk
 * 
 * 
 *问题1：getInsertSql方法为什么直调用子类的方法，data怎么传到子类中
 */
public abstract class BaseDaoImpl implements BaseDao{

	@Override
	public int insert(Object data) throws Exception {
		//JDBC
		//所以讲创建连接对象抽出来（第一步）
		//1.创建数据库连接对象Connection
		Connection con = DbHelper.getConnection();
		//2.编写sql语句，创建命令对象PreparedStatement
		String sql = getInsertSql(data);//如何获得子类的返回值
		PreparedStatement pst =  con.prepareStatement(sql);
		//3.执行命令：executeUpdate,excuteQuery
		int rows = pst.executeUpdate();	
		//4.处理执行的结果：ResultSet结果集
		//   结果集中的记录要转换成实体类对象
		//5.释放资源（关闭数据库连接）
		DbHelper.closeAll(null,pst, null);
		return rows;//返回更新次数
	}

	@Override
	public int update(Object data) throws Exception {
		//1.创建数据库连接对象Connection
		//JDBC
				//所以讲创建连接对象抽出来（第一步）
				//1.创建数据库连接对象Connection
				Connection con = DbHelper.getConnection();
				//2.编写sql语句，创建命令对象PreparedStatement
				String sql = getUpdateSql(data);//如何获得子类的返回值
				PreparedStatement pst =  con.prepareStatement(sql);
				//3.执行命令：executeUpdate,excuteQuery
				int rows = pst.executeUpdate();	
				//4.处理执行的结果：ResultSet结果集
				//   结果集中的记录要转换成实体类对象
				//5.释放资源（关闭数据库连接）
				DbHelper.closeAll(null,pst, null);
				return rows;//返回更新次数
	}

	@Override
	public int delete(Object id) throws Exception {
		//1.创建数据库连接对象Connection
		//JDBC
		//所以讲创建连接对象抽出来（第一步）
		//1.创建数据库连接对象Connection
		Connection con = DbHelper.getConnection();
		//2.编写sql语句，创建命令对象PreparedStatement
		String sql = getDeleteSql(id);//如何获得子类的返回值
		PreparedStatement pst =  con.prepareStatement(sql);
		//3.执行命令：executeUpdate,excuteQuery
		int rs = pst.executeUpdate();
		//4.处理执行的结果：ResultSet结果集
		//   结果集中的记录要转换成实体类对象
		//5.释放资源（关闭数据库连接）
		DbHelper.closeAll(null,pst,null);
		return rs;
	}


	public List findAll() throws Exception{
		//JDBC
				//所以讲创建连接对象抽出来（第一步）
				//1.创建数据库连接对象Connection
				Connection con = DbHelper.getConnection();
				//2.编写sql语句，创建命令对象PreparedStatement
				String sql = getFindAllSql();
				PreparedStatement pst =  con.prepareStatement(sql);
				//3.执行命令：executeUpdate,excuteQuery
				ResultSet rs = pst.executeQuery();	
				//4.处理执行的结果：ResultSet结果集
				//   结果集中的记录要转换成实体类对象
				List list = new ArrayList();
				while(rs.next()) {
					//将一条记录转换成java对象
					Object data = rsToObject(rs);
					//将对象加入集合
					list.add(data);
				}
				//5.释放资源（关闭数据库连接）
				DbHelper.closeAll(null,pst, rs);
				return list;
	}
	//根据id主键查询一个对象
	public Object findOne(int id) throws Exception{
		// 1 创建数据库连接对象Connection
				Connection con = DbHelper.getConnection();
				// 2 编写sql语句，创建命令对象PreparedStatement
				String sql = getFindAllSql() + " where id=" + id;
				PreparedStatement pst = con.prepareStatement(sql);

				// 3 执行命令：executeUpdate ,executeQuery
				ResultSet rs = pst.executeQuery();
				// 4 处理执行的结果：ResultSet结果集
				// 结果集中的记录转换成实体类对象
				Object result = null;
				// 按照结果集中的数据进行循环
				if (rs.next()) {
					// 将一条记录转换成java对象
					result = rsToObject(rs);
				}

				// 5 释放资源(关闭连接)
				DbHelper.closeAll(null, pst, rs);

				return result;
	}
	//按照条件来查询所有满足条件的记录
	//构造条件的数据：一般是属于某一些属性的值；实体类
	//UserQueryModel SubjectQueryModel
	public List findByCondition(BaseQueryModel queryModel) throws Exception{
		//JDBC
		//所以讲创建连接对象抽出来（第一步）
		//1.创建数据库连接对象Connection
		Connection con = DbHelper.getConnection();
		//2.编写sql语句，创建命令对象PreparedStatement
		String sql = getFindConditionSql(queryModel);
		PreparedStatement pst =  con.prepareStatement(sql);
		//3.执行命令：executeUpdate,excuteQuery
		ResultSet rs = pst.executeQuery();	
		//4.处理执行的结果：ResultSet结果集
		//   结果集中的记录要转换成实体类对象
		List list = new ArrayList();
		while(rs.next()) {
			//将一条记录转换成java对象
			Object data = rsToObject(rs);
			//将对象加入集合
			list.add(data);
		}
		//5.释放资源（关闭数据库连接）
		DbHelper.closeAll(null,pst, rs);
		return list;
	}
	public Long findId() throws Exception{
		//JDBC
				//所以讲创建连接对象抽出来（第一步）
				//1.创建数据库连接对象Connection
				Connection con = DbHelper.getConnection();
				//2.编写sql语句，创建命令对象PreparedStatement
				String sql = "select last_insert_id() as maxid";
				PreparedStatement pst =  con.prepareStatement(sql);
				//3.执行命令：executeUpdate,excuteQuery
				ResultSet rs = pst.executeQuery();	
				//4.处理执行的结果：ResultSet结果集
				//   结果集中的记录要转换成实体类对象
				Long result =null;
				while(rs.next()) {
					result = rs.getLong("maxid");
				}
				//5.释放资源（关闭数据库连接）
				DbHelper.closeAll(null,pst, rs);
				return result;
	}
	public int findMaxId() throws Exception {
		// 1 创建Connection对象
		Connection con = DbHelper.getConnection();
		// 2 编写SQL语句，创建PreparedStatement对象
		String sql = "SELECT LAST_INSERT_ID() AS maxid";
		PreparedStatement pst = con.prepareStatement(sql);
		// 3 执行命令:executeUpdate,executeQuery
		// 返回值就是结果集
		ResultSet rs = pst.executeQuery();
		// 4 处理执行的结果(ResultSet中的记录=>java对象)
		// 对结果集进行循环，每循环一次读取一条记录
		int result = 0;
		if (rs.next()) {
			result = rs.getInt("maxid");
		}
		// 5 释放资源
		DbHelper.closeAll(null, pst, null);

		return result;
	}
	//产生sql语句的抽象方法，留给子类实现
	public abstract String getInsertSql(Object data);
	public abstract String getFindAllSql();
	//将一条结果集中的一条记录转换成一个java对象
	public abstract Object rsToObject(ResultSet rs) throws Exception;
	public abstract String getFindConditionSql(BaseQueryModel queryModel);
	public abstract String getUpdateSql(Object data);
	public abstract String getDeleteSql(Object data);
}
