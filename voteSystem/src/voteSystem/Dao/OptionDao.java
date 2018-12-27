package voteSystem.Dao;

import voteSystem.util.Base.BaseDao;

public interface OptionDao extends BaseDao{

	//根据主题id删除属于该主题的全部选项
	public int deleteOptions(int subjectId) throws Exception;
	public int getOptionCount(int optionId) throws Exception;
}
