package voteSystem.Dao;

import java.util.List;

import voteSystem.util.Base.BaseDao;

public interface RecordDao extends BaseDao {

	public List getUsers(int subjectId) throws Exception;
}
