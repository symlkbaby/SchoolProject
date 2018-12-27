package voteSystem.Dao;

import voteSystem.util.Base.BaseDao;

public interface SubjectDao extends BaseDao {

	public int getGenerateId() throws Exception;
	public int getUserCount(int subjectId) throws Exception;
	public int getUserId(int subjectId) throws Exception;
}
