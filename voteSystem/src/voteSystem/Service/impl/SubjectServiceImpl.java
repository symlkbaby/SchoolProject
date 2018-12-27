package voteSystem.Service.impl;


import java.util.Date;
import java.util.List;

import voteSystem.Dao.OptionDao;
import voteSystem.Dao.SubjectDao;
import voteSystem.Dao.impl.OptionDaoImpl;
import voteSystem.Dao.impl.SubjectDaoImpl;
import voteSystem.Pojo.Option;
import voteSystem.Pojo.OptionQueryModel;
import voteSystem.Pojo.Subject;
import voteSystem.Pojo.SubjectQueryModel;
import voteSystem.Pojo.User;
import voteSystem.Service.SubjectService;
import voteSystem.util.exception.RuleException;

public class SubjectServiceImpl implements SubjectService{
	    private SubjectDao subjectDao;
	    private OptionDao optionDao;
	    public SubjectServiceImpl(){
	    	this.subjectDao = new SubjectDaoImpl();
	    	this.optionDao = new OptionDaoImpl();
	    }
		@Override
		public void add(Subject subject,User user) throws Exception {
		    if(subject.getTitle()==null || subject.getTitle().trim().length()==0){
		    	throw new RuleException("主题的标题不能为空");
		    }
		    if(subject.getOptions().size()<2){
		    	throw new RuleException("至少要有2个选项");
		    }
		    for(int i=0;i<subject.getOptions().size()-1;i++){
		    	String content = subject.getOptions().get(i).getContent();
		    	for(int j=i+1;j<subject.getOptions().size();j++){
		    		if(content.equals(subject.getOptions().get(j).getContent())){
		    			throw new RuleException("存在重复的选项");
		    		}
		    	}
		    }
		    SubjectQueryModel queryModel=new SubjectQueryModel();
			queryModel.setTitle(subject.getTitle());
			queryModel.getUser().setId(user.getId());
			List list = subjectDao.findByCondition(queryModel);
			if(list!=null && list.size()>0){
				throw new RuleException("同一个发起人不能发起同样主题的投票项目");
			}
			//1 新增主题
			// 默认的开始时间为当前时间
			Long now = new Date().getTime();
			System.out.println(now);
			subject.setStartTime(now);
			// 默认的结束时间为1天以后
			subject.setEndTime(now+10*24*60*60*1000);
			// 默认的发起人是登录用户
			subject.setUser(user);
			
			subjectDao.insert(subject);
			subject.setId(subjectDao.findMaxId());
			//2 新增选项
			int i=1;	
//			int x=10/0;
			for(Option option : subject.getOptions()){
				//设置序号和主题编号
				option.setIdx(i++);
				option.setSubjectId(subject.getId());
				
				optionDao.insert(option);
			}

		}

	@Override
	public List<Subject> getSubjects() throws Exception {
		List list = subjectDao.findAll();
		if(list!=null && list.size()>0){
			for(Object data:list){
				Subject subject = (Subject)data;
				OptionQueryModel queryModel = new OptionQueryModel();
				queryModel.setSubjectId(subject.getId());
				subject.setOptions(optionDao.findByCondition(queryModel));
				subject.setUserCount(subjectDao.getUserCount(subject.getId()));
			}
		}
		return list;
	}
	@Override
	public Subject getSubject(int id) throws Exception {
		Subject subject = (Subject)subjectDao.findOne(id);
		if(subject!=null){			
			OptionQueryModel queryModel = new OptionQueryModel();
			queryModel.setSubjectId(subject.getId());
			subject.setOptions(optionDao.findByCondition(queryModel));
			subject.setUserCount(subjectDao.getUserCount(subject.getId()));
		}
		return subject;
	}
	@Override
	public void modify(Subject subject, User attribute) throws Exception {
		//已经存在投票记录，不允许进行修改
				if(subjectDao.getUserCount(subject.getId())>0){
					throw new RuleException("已经存在投票记录，不可以修改");
				}
				if(subjectDao.getUserId(subject.getId()) != attribute.getId()) {
					throw new RuleException("非法修改项目！");
				}
				//开始修改
				subjectDao.update(subject);
				optionDao.deleteOptions(subject.getId());
				for(int i=0;i<subject.getOptions().size();i++){
					Option op = (Option)subject.getOptions().get(i);
					op.setIdx(i+1);
					op.setSubjectId(subject.getId());
					optionDao.insert(op);
				}
	}
	

}
