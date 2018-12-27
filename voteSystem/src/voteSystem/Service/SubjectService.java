package voteSystem.Service;

import java.util.List;

import voteSystem.Pojo.Subject;
import voteSystem.Pojo.User;

public interface SubjectService {

	public void add(Subject subject,User user) throws Exception;
	public List<Subject> getSubjects() throws Exception;
	public Subject getSubject(int id) throws Exception;
	public void modify(Subject subject, User attribute) throws Exception;
}
