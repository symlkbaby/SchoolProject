package voteSystem.util.Dao;
/**
 * 1.���ݷ��ʵĸ����ࣺ��ȡ���Ӷ���
 * 2.�ͷ���Դ
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

	//��Ҫ��İ����˵��
	private static final String DBDRIVER = "com.mysql.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/votedb?useSSL=false";
	private static final String DBUSER = "root";
	private static final String DBPASSWORD = "123456";
	//��ȡ���Ӷ���
	//1.����c3p0�����ݿ����ӳض���
//	private static DataSource dataSource = 
//			new ComboPooledDataSource();
	//��ͬһ������Ĺ����У��õ������Ӷ���Ӧ����ͬһ�����Ӷ���
	//����ȥ��֤ͬһ���̻߳�ȡ�������ǵ���
	//�õ�ThreadLocal���̱߳��أ�
	
	//����һ���̲߳۶��󣨼��ϣ�ÿ���߳����еļ��ϣ��������汾�߳��д����Ķ���
	private static ThreadLocal<Connection> conPool = new ThreadLocal<Connection>();
	
	
	public static Connection getConnection() 
	throws Exception{
		//���̲߳��л�ȡ���Ӷ���
		Connection con = conPool.get();
		//���û�л�ȡ������ʾ���߳��л�û�д������Ӷ���
		if(con==null) {
		//����Ҫ�����ݿ����ӳ��л�ȡһ�����Ӷ���
			//dataSource.getConnection();
			Class.forName(DBDRIVER);
			con = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
		//����һ����ȡ�����Ӷ�����뵽�̲߳��м�ȥ��ǰ�����õ������ӳأ�
			conPool.set(con);
		}
		return con;
	}
	//�ر����Ӷ����ͬʱ��ҲҪ���̲߳����Ƴ������Ӷ���
	public static void close() throws Exception{
		Connection con = conPool.get();
		if(con!=null) {
			con.close();
			conPool.remove();
		}
	}
	
	//�ͷ���Դ
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
	//��װ������������������������ύ�ͻع�����
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
