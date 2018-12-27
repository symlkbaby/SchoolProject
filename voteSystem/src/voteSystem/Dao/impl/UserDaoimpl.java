package voteSystem.Dao.impl;

import java.sql.ResultSet;
import voteSystem.Dao.UserDao;
import voteSystem.Pojo.User;
import voteSystem.Pojo.UserQueryModel;
import voteSystem.util.Base.BaseDaoImpl;
import voteSystem.util.Base.BaseQueryModel;

/**
 * �û����ݷ����ࣺ�̳�BaseDaoImpl���࣬ʵ��UserDao�ӽӿ�
 * ����Ÿ���sql���
 * @author lk
 *
 */
public class UserDaoimpl extends BaseDaoImpl implements UserDao{

	
	
	public String getInsertSql(Object data) {
		//���������û���sql���
		User user = (User)data;
		return "insert into t_user(name,pwd,online) "
				+ "values('"+user.getName()+"','"+user.getPwd()+"',"+user.getOnline()+")";
	}



	@Override
	public String getFindAllSql() {
		// TODO Auto-generated method stub
		return "select * from t_user";
	}


	@Override
	public Object rsToObject(ResultSet rs) throws Exception{
		// TODO Auto-generated method stub
		User user = new User();//����һ��ʵ�������
		user.setId(rs.getInt("id"));
		user.setPwd(rs.getString("pwd"));
		user.setOnline(rs.getInt("online"));
		user.setName(rs.getString("name"));
		return user;
	}

	@Override
	public String getFindConditionSql(BaseQueryModel queryModel) {
		// TODO Auto-generated method stub
		//������������ѯ�û�
		UserQueryModel qm = (UserQueryModel)queryModel;
		//��дsql���
		StringBuilder sb=new StringBuilder();
		sb.append("select * from t_user ");
		sb.append(" where 1=1 ");
		//qm��������ʲô����ֵ������sql�����дʲô����
		if(qm.getName()!=null && qm.getName().trim().length()>0) {
			sb.append(" and name='"+qm.getName()+"' ");
		}
		if(qm.getPwd()!=null && qm.getPwd().trim().length()>0) {
			sb.append(" and pwd ='"+qm.getPwd()+"'");
		}
		if(qm.getOnline()>=1) {
			sb.append(" and online="+qm.getOnline());
		}
		return sb.toString();
	}



	@Override
	public String getUpdateSql(Object data) {
		// TODO Auto-generated method stub
		//��д��ѯ�û�
		
		User user = (User)data;
		return "update t_user set name='"+user.getName()+"',"+"pwd='"+user.getPwd()+"',"
				+ "online="+user.getOnline()+" where id="+user.getId()+"";
	}



	@Override
	public String getDeleteSql(Object data) {
		// TODO Auto-generated method stub
		User user = (User)data;
		return "delete from t_user where id="+user.getId()+"";
	}




}
