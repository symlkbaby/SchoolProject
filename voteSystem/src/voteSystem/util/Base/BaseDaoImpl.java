package voteSystem.util.Base;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import voteSystem.util.Dao.DbHelper;

/**
 * ���ݷ��ʵĸ��࣬ʵ�ָ��ӿ��е���ɾ�Ĳ鷽��
 * @author lk
 * 
 * 
 *����1��getInsertSql����Ϊʲôֱ��������ķ�����data��ô����������
 */
public abstract class BaseDaoImpl implements BaseDao{

	@Override
	public int insert(Object data) throws Exception {
		//JDBC
		//���Խ��������Ӷ�����������һ����
		//1.�������ݿ����Ӷ���Connection
		Connection con = DbHelper.getConnection();
		//2.��дsql��䣬�����������PreparedStatement
		String sql = getInsertSql(data);//��λ������ķ���ֵ
		PreparedStatement pst =  con.prepareStatement(sql);
		//3.ִ�����executeUpdate,excuteQuery
		int rows = pst.executeUpdate();	
		//4.����ִ�еĽ����ResultSet�����
		//   ������еļ�¼Ҫת����ʵ�������
		//5.�ͷ���Դ���ر����ݿ����ӣ�
		DbHelper.closeAll(null,pst, null);
		return rows;//���ظ��´���
	}

	@Override
	public int update(Object data) throws Exception {
		//1.�������ݿ����Ӷ���Connection
		//JDBC
				//���Խ��������Ӷ�����������һ����
				//1.�������ݿ����Ӷ���Connection
				Connection con = DbHelper.getConnection();
				//2.��дsql��䣬�����������PreparedStatement
				String sql = getUpdateSql(data);//��λ������ķ���ֵ
				PreparedStatement pst =  con.prepareStatement(sql);
				//3.ִ�����executeUpdate,excuteQuery
				int rows = pst.executeUpdate();	
				//4.����ִ�еĽ����ResultSet�����
				//   ������еļ�¼Ҫת����ʵ�������
				//5.�ͷ���Դ���ر����ݿ����ӣ�
				DbHelper.closeAll(null,pst, null);
				return rows;//���ظ��´���
	}

	@Override
	public int delete(Object id) throws Exception {
		//1.�������ݿ����Ӷ���Connection
		//JDBC
		//���Խ��������Ӷ�����������һ����
		//1.�������ݿ����Ӷ���Connection
		Connection con = DbHelper.getConnection();
		//2.��дsql��䣬�����������PreparedStatement
		String sql = getDeleteSql(id);//��λ������ķ���ֵ
		PreparedStatement pst =  con.prepareStatement(sql);
		//3.ִ�����executeUpdate,excuteQuery
		int rs = pst.executeUpdate();
		//4.����ִ�еĽ����ResultSet�����
		//   ������еļ�¼Ҫת����ʵ�������
		//5.�ͷ���Դ���ر����ݿ����ӣ�
		DbHelper.closeAll(null,pst,null);
		return rs;
	}


	public List findAll() throws Exception{
		//JDBC
				//���Խ��������Ӷ�����������һ����
				//1.�������ݿ����Ӷ���Connection
				Connection con = DbHelper.getConnection();
				//2.��дsql��䣬�����������PreparedStatement
				String sql = getFindAllSql();
				PreparedStatement pst =  con.prepareStatement(sql);
				//3.ִ�����executeUpdate,excuteQuery
				ResultSet rs = pst.executeQuery();	
				//4.����ִ�еĽ����ResultSet�����
				//   ������еļ�¼Ҫת����ʵ�������
				List list = new ArrayList();
				while(rs.next()) {
					//��һ����¼ת����java����
					Object data = rsToObject(rs);
					//��������뼯��
					list.add(data);
				}
				//5.�ͷ���Դ���ر����ݿ����ӣ�
				DbHelper.closeAll(null,pst, rs);
				return list;
	}
	//����id������ѯһ������
	public Object findOne(int id) throws Exception{
		// 1 �������ݿ����Ӷ���Connection
				Connection con = DbHelper.getConnection();
				// 2 ��дsql��䣬�����������PreparedStatement
				String sql = getFindAllSql() + " where id=" + id;
				PreparedStatement pst = con.prepareStatement(sql);

				// 3 ִ�����executeUpdate ,executeQuery
				ResultSet rs = pst.executeQuery();
				// 4 ����ִ�еĽ����ResultSet�����
				// ������еļ�¼ת����ʵ�������
				Object result = null;
				// ���ս�����е����ݽ���ѭ��
				if (rs.next()) {
					// ��һ����¼ת����java����
					result = rsToObject(rs);
				}

				// 5 �ͷ���Դ(�ر�����)
				DbHelper.closeAll(null, pst, rs);

				return result;
	}
	//������������ѯ�������������ļ�¼
	//�������������ݣ�һ��������ĳһЩ���Ե�ֵ��ʵ����
	//UserQueryModel SubjectQueryModel
	public List findByCondition(BaseQueryModel queryModel) throws Exception{
		//JDBC
		//���Խ��������Ӷ�����������һ����
		//1.�������ݿ����Ӷ���Connection
		Connection con = DbHelper.getConnection();
		//2.��дsql��䣬�����������PreparedStatement
		String sql = getFindConditionSql(queryModel);
		PreparedStatement pst =  con.prepareStatement(sql);
		//3.ִ�����executeUpdate,excuteQuery
		ResultSet rs = pst.executeQuery();	
		//4.����ִ�еĽ����ResultSet�����
		//   ������еļ�¼Ҫת����ʵ�������
		List list = new ArrayList();
		while(rs.next()) {
			//��һ����¼ת����java����
			Object data = rsToObject(rs);
			//��������뼯��
			list.add(data);
		}
		//5.�ͷ���Դ���ر����ݿ����ӣ�
		DbHelper.closeAll(null,pst, rs);
		return list;
	}
	public Long findId() throws Exception{
		//JDBC
				//���Խ��������Ӷ�����������һ����
				//1.�������ݿ����Ӷ���Connection
				Connection con = DbHelper.getConnection();
				//2.��дsql��䣬�����������PreparedStatement
				String sql = "select last_insert_id() as maxid";
				PreparedStatement pst =  con.prepareStatement(sql);
				//3.ִ�����executeUpdate,excuteQuery
				ResultSet rs = pst.executeQuery();	
				//4.����ִ�еĽ����ResultSet�����
				//   ������еļ�¼Ҫת����ʵ�������
				Long result =null;
				while(rs.next()) {
					result = rs.getLong("maxid");
				}
				//5.�ͷ���Դ���ر����ݿ����ӣ�
				DbHelper.closeAll(null,pst, rs);
				return result;
	}
	public int findMaxId() throws Exception {
		// 1 ����Connection����
		Connection con = DbHelper.getConnection();
		// 2 ��дSQL��䣬����PreparedStatement����
		String sql = "SELECT LAST_INSERT_ID() AS maxid";
		PreparedStatement pst = con.prepareStatement(sql);
		// 3 ִ������:executeUpdate,executeQuery
		// ����ֵ���ǽ����
		ResultSet rs = pst.executeQuery();
		// 4 ����ִ�еĽ��(ResultSet�еļ�¼=>java����)
		// �Խ��������ѭ����ÿѭ��һ�ζ�ȡһ����¼
		int result = 0;
		if (rs.next()) {
			result = rs.getInt("maxid");
		}
		// 5 �ͷ���Դ
		DbHelper.closeAll(null, pst, null);

		return result;
	}
	//����sql���ĳ��󷽷�����������ʵ��
	public abstract String getInsertSql(Object data);
	public abstract String getFindAllSql();
	//��һ��������е�һ����¼ת����һ��java����
	public abstract Object rsToObject(ResultSet rs) throws Exception;
	public abstract String getFindConditionSql(BaseQueryModel queryModel);
	public abstract String getUpdateSql(Object data);
	public abstract String getDeleteSql(Object data);
}
