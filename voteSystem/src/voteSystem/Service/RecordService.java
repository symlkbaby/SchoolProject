package voteSystem.Service;

import java.util.List;

import voteSystem.Pojo.Record;
import voteSystem.Pojo.Subject;
import voteSystem.Pojo.User;

public interface RecordService {

	public void vote(List<Record> records,User user,int subjectId) throws Exception;
}
