package voteSystem.util.Base;

import java.util.List;

/**
 * 
 * @author lk
 * ���ݷ�����ĸ��ӿ�
 *   ����ͨ�õ���ɾ�Ĳ飨CRUD���ķ���
 */
public interface BaseDao {

	//��  
	//int:��������ֵ��������������
	//ʹ�ø������ĵط����Դ����κ��������
	//Object date:Ӧ�����ϴ���ԭ��
	public int insert(Object data) throws Exception;
	//�����ж���id���Զ������ģ���Ϊ����
	//������������û��idֵ���޸�ʱ�������б�����idֵ
	public int update(Object data) throws Exception;
	//����id��ɾ������
	public int delete(Object id) throws Exception;
	
	// ��д��ѯ����
	//1.����е����м�¼
	public List findAll() throws Exception;
	//����id������ѯһ������
	public Object findOne(int id) throws Exception;
	//������������ѯ�������������ļ�¼
	//�������������ݣ�һ��������ĳһЩ���Ե�ֵ��ʵ����
	//UserQueryModel SubjectQueryModel
	public List findByCondition(BaseQueryModel queryModel) throws Exception;
	
	public Long findId() throws Exception;
	public int findMaxId() throws Exception;
}








