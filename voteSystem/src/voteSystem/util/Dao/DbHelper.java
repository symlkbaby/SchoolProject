package voteSystem.util.Dao;
/**
 * 1.数据访问的辅助类：获取连接对象；
 * 2.释放资源
 * @author lk
 *
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import javax.sql.DataSource;

//import com.mchange.v2.c3p0.ComboPooledDataSource;


public class DbHelper {

	//不要和陌生人说话
	private static final String DBDRIVER = "com.mysql.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/votedb?useSSL=false";
	private static final String DBUSER = "root";
	private static final String DBPASSWORD = "123456";
	//获取连接对象
	//1.定义c3p0的数据库连接池对象
//	private static DataSource dataSource = 
//			new ComboPooledDataSource();
	//在同一次请求的过程中，得到的连接对象应该是同一个连接对象
	//怎样去保证同一个线程获取的连接是单例
	//用到ThreadLocal（线程本地）
	
	//创建一个线程槽对象（集合）每个线程特有的集合，用来保存本线程中创建的对线
	private static ThreadLocal<Connection> conPool = new ThreadLocal<Connection>();
	
	
	public static Connection getConnection() 
	throws Exception{
		//从线程槽中获取连接对象
		Connection con = conPool.get();
		//如果没有获取到，表示本线程中还没有创建连接对象
		if(con==null) {
		//就需要从数据库连接池中获取一个连接对象
			//dataSource.getConnection();
			Class.forName(DBDRIVER);
			con = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
		//将这一个获取的连接对象放入到线程槽中间去（前提是用的是连接池）
			conPool.set(con);
		}
		return con;
	}
	//关闭连接对象的同时，也要在线程槽中移除掉连接对象
	public static void close() throws Exception{
		Connection con = conPool.get();
		if(con!=null) {
			con.close();
			conPool.remove();
		}
	}
	
	//释放资源
	public static void closeAll(Connection con,
			PreparedStatement pst,ResultSet rs) throws Exception {
		if(rs!=null) {
			rs.close();
		}
		if(pst!=null) {
			pst.close();
		}
		if(con!=null) {
			con.close();
		}
	}
	//封装事务处理的三个方法：开启，提交和回滚事务
	public static void beginTransaction() throws Exception{
		getConnection().setAutoCommit(false);
	}
	public static void commitTransaction() throws Exception{
		getConnection().commit();
	}
	public static void rollbackTransaction() throws Exception{
		getConnection().rollback();
	}
}
