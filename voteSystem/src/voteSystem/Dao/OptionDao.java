package voteSystem.Dao;

import voteSystem.util.Base.BaseDao;

public interface OptionDao extends BaseDao{

	//��������idɾ�����ڸ������ȫ��ѡ��
	public int deleteOptions(int subjectId) throws Exception;
	public int getOptionCount(int optionId) throws Exception;
}
