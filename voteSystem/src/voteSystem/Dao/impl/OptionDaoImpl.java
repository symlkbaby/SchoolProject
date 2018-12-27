package voteSystem.Dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import voteSystem.Dao.OptionDao;
import voteSystem.Pojo.Option;
import voteSystem.Pojo.OptionQueryModel;
import voteSystem.util.Base.BaseDaoImpl;
import voteSystem.util.Base.BaseQueryModel;
import voteSystem.util.Dao.DbHelper;

public class OptionDaoImpl extends BaseDaoImpl implements OptionDao{

	@Override
	public String getInsertSql(Object data) {
		Option option = (Option)data;
		return "insert into t_option(content,idx,subjectId) "
				+ "values('"+option.getContent()+"',"+option.getIdx()+","
						+ ""+option.getSubjectId()+")";
	}

	@Override
	public String getFindAllSql() {
		// TODO Auto-generated method stub
		return "select * from t_option";
	}

	@Override
	public Object rsToObject(ResultSet rs) throws Exception {
		//将一条记录转换成一个实体类对象
		Option option= new Option();
		//将rs中记录的字段值读取出来
		//赋值给实体类对象的属性
		option.setId(rs.getInt("id"));
		option.setIdx(rs.getInt("idx"));
		option.setSubjectId(rs.getInt("subjectId"));
		option.setContent(rs.getString("content"));
		option.setCount(getOptionCount(option.getId()));
		return option;
	}

	@Override
	public String getFindConditionSql(BaseQueryModel queryModel) {
		OptionQueryModel qm = (OptionQueryModel)queryModel;
		StringBuilder sb = new StringBuilder();
		sb.append("select * from t_option where 1=1 ");
		if(qm.getSubjectId()!=null){
			sb.append(" and subjectId="+qm.getSubjectId());
		}
		return sb.toString();
	}

	@Override
	public String getUpdateSql(Object data) {
		Option option = (Option)data;
		return "update t_option set content='"+option.getContent()+"',idx="+option.getIdx()+",subjectId="+option.getSubjectId()+" where id="+option.getId();
	}

	@Override
	public String getDeleteSql(Object data) {
		Option option = (Option)data;
		return "delete from t_option where id="+option.getId();
	}


	@Override
	public int deleteOptions(int subjectId) throws Exception {
		Connection con = DbHelper.getConnection();
		String sql = "delete from t_option where subjectId="+subjectId;
		PreparedStatement pst = con.prepareStatement(sql);
		int rows = pst.executeUpdate();
		DbHelper.closeAll(null, pst, null);
		return rows;
	}

	

	@Override
	public int getOptionCount(int optionId) throws Exception {

		int result = 0;
		Connection con = DbHelper.getConnection();
		String sql = "SELECT COUNT(*) as cnt FROM t_record WHERE optionId="+optionId;
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		if(rs.next()){
			result = rs.getInt("cnt");
		}
		DbHelper.closeAll(null, pst, rs);
		return result;
	}

}
