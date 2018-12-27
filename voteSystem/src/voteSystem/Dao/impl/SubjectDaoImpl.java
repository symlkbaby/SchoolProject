package voteSystem.Dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import voteSystem.Dao.SubjectDao;
import voteSystem.Pojo.Subject;
import voteSystem.Pojo.SubjectQueryModel;
import voteSystem.util.Base.BaseDaoImpl;
import voteSystem.util.Base.BaseQueryModel;
import voteSystem.util.Dao.DbHelper;

public class SubjectDaoImpl extends BaseDaoImpl implements SubjectDao{

	@Override
	public String getInsertSql(Object data) {
		Subject subject = (Subject)data;
		return "insert into t_subject(title,number,startTime,endTime,userId) "
				+ "values('"+subject.getTitle()+"',"+subject.getNumber()+""
						+ ","+subject.getStartTime()+","+subject.getEndTime()+","+subject.getUser().getId()+")";
	}

	@Override
	public String getFindAllSql() {
		// TODO Auto-generated method stub
		return "select * from t_subject";
	}

	@Override
	public Object rsToObject(ResultSet rs) throws Exception {
		//将一条记录转换成一个实体类对象
				Subject subject= new Subject();
				//将rs中记录的字段值读取出来
				//赋值给实体类对象的属性
				subject.setId(rs.getInt("id"));
				subject.setTitle(rs.getString("title"));
				subject.setNumber(rs.getInt("number"));
				subject.setStartTime(rs.getLong("startTime"));
				subject.setEndTime(rs.getLong("endTime"));
				subject.getUser().setId(rs.getInt("userId"));
				
				return subject;
	}

	@Override
	public String getFindConditionSql(BaseQueryModel queryModel) {
		SubjectQueryModel qm = (SubjectQueryModel)queryModel;
		StringBuilder sb = new StringBuilder();
		sb.append("select * from t_subject where 1=1 ");
		if(qm.getTitle()!=null && qm.getTitle().trim().length()>0){
			sb.append(" and title='"+qm.getTitle()+"' ");
		}
		if(qm.getUser()!=null || qm.getUser().getId()!=null){
			sb.append(" and userId="+qm.getUser().getId());
		}
		return sb.toString();
	}

	@Override
	public String getUpdateSql(Object data) {
		Subject subject=(Subject)data;
		return "update t_subject set title='"+subject.getTitle()+"',number="+subject.getNumber()+",startTime="+subject.getStartTime()+",endTime="+subject.getEndTime()+" where id="+subject.getId();
	}

	@Override
	public String getDeleteSql(Object data) {
		// TODO Auto-generated method stub
		Subject subject = (Subject)data;
		return "delete from t_subject where id="+subject.getId();
	}

	@Override
	public int getGenerateId() throws Exception {
		int result = 0;
		Connection con = DbHelper.getConnection();
		String sql = "SELECT max(id) as id from t_subject";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		if(rs.next()){
			result = rs.getInt("id");
		}
		DbHelper.closeAll(null, pst, rs);
		return result;
	}

	@Override
	public int getUserCount(int subjectId) throws Exception {
		int result = 0;
		Connection con = DbHelper.getConnection();
		String sql = "SELECT COUNT(DISTINCT userId) as cnt FROM t_record WHERE subjectId="+subjectId;
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		if(rs.next()){
			result = rs.getInt("cnt");
		}
		DbHelper.closeAll(null, pst, rs);
		return result;
	}

	@Override
	public int getUserId(int subjectId) throws Exception {
		int userId = 0;
		Connection con = DbHelper.getConnection();
		String sql = "select userId from t_subject where id="+subjectId;
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		if(rs.next()) {
			userId = rs.getInt("userId");
		}
		DbHelper.closeAll(null, pst, rs);
		return userId;
	}

}
