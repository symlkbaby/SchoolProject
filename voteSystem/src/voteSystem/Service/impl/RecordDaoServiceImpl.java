package voteSystem.Service.impl;

import java.util.Date;
import java.util.List;

import voteSystem.Dao.RecordDao;
import voteSystem.Dao.SubjectDao;
import voteSystem.Dao.impl.RecordDaoImpl;
import voteSystem.Dao.impl.SubjectDaoImpl;
import voteSystem.Pojo.Record;
import voteSystem.Pojo.RecordQueryModel;
import voteSystem.Pojo.Subject;
import voteSystem.Pojo.User;
import voteSystem.Service.RecordService;
import voteSystem.util.exception.RuleException;

public class RecordDaoServiceImpl implements RecordService {

	private SubjectDao subjectDao;
    private RecordDao recordDao;
    public RecordDaoServiceImpl(){
    	this.subjectDao=new SubjectDaoImpl();
    	this.recordDao=new RecordDaoImpl();
    }
    
	@Override
	public void vote(List<Record> records,User user,int subjectId) throws Exception {
		if(records.isEmpty()) {
			throw new RuleException("请投票");
		}
		Subject subject= (Subject)subjectDao.findOne(records.get(0).getSubjectId());
		if(subject.getNumber()==1 && records.size()!=1 ||
		   subject.getNumber()==2 && records.size()<1
		){
			throw new RuleException("没有按照单选和多选类别进行选择");
		}
		//验证时间：
		long curr = new Date().getTime();
		if(curr<subject.getStartTime() || curr>subject.getEndTime()){
			throw new RuleException("没有在项目规定的时间内投票");
		}
		//验证用户：
		List list = recordDao.getUsers(subjectId);
		int userid = user.getId();
		boolean flag=false;
		for(int i=0;i<list.size();i++) {
			if((int)list.get(i) == userid) {
				flag = true;
			}
		}
		if(flag) {
			throw new RuleException("您已经投过票了，谢谢");
		}
		//开始投票
		for(Record record:records){
			recordDao.insert(record);
		}
	}

}
