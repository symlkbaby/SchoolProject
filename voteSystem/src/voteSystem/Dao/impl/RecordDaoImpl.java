package voteSystem.Dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import voteSystem.Dao.RecordDao;
import voteSystem.Pojo.Record;
import voteSystem.Pojo.RecordQueryModel;
import voteSystem.util.Base.BaseDaoImpl;
import voteSystem.util.Base.BaseQueryModel;
import voteSystem.util.Dao.DbHelper;

public class RecordDaoImpl extends BaseDaoImpl implements RecordDao {

	@Override
	public String getInsertSql(Object data) {
		// TODO Auto-generated method stub
		Record record = (Record)data;
		return "insert into t_record(userId,subjectId,optionId) values("+record.getUserId()+","+record.getSubjectId()+","+record.getOptionId()+")";
	}

	@Override
	public String getFindAllSql() {
		// TODO Auto-generated method stub
		return "select * from t_record";
	}

	@Override
	public Object rsToObject(ResultSet rs) throws Exception {
		Record record= new Record();
		record.setId(rs.getInt("id"));
		record.setUserId(rs.getInt("userId"));
		record.setSubjectId(rs.getInt("subjectId"));
		record.setOptionId(rs.getInt("optionId"));
		return record;
	}

	@Override
	public String getFindConditionSql(BaseQueryModel queryModel) {
		RecordQueryModel qm = (RecordQueryModel)queryModel;
		StringBuilder sb = new StringBuilder();
		sb.append("select * from t_record where 1=1 ");
		if(qm.getSubjectId()!=null){
			sb.append(" subjectId="+qm.getSubjectId());
		}
		return sb.toString();
	}

	@Override
	public String getUpdateSql(Object data) {
		Record record=(Record)data;
		return "update t_record set subjectId='"+record.getSubjectId()+"',optionId="+record.getOptionId()+",userId="+record.getUserId()+" where id="+record.getId();
	}

	@Override
	public String getDeleteSql(Object data) {
		Record record = (Record)data;
		return "delete from t_record where id="+record.getId();
	}

	@Override
	public List getUsers(int subjectId) throws Exception {
		// TODO Auto-generated method stub
		Connection con = DbHelper.getConnection();
		String sql = "select userId from t_record where subjectId="+subjectId;
		PreparedStatement pStatement = con.prepareStatement(sql);
		ResultSet rs = pStatement.executeQuery();
		List list = new ArrayList();
		while(rs.next()) {
			list.add(rs.getInt("userId"));
		}
		DbHelper.closeAll(null,pStatement, rs);
		return list;
	}

}
