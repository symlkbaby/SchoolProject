package voteSystem.util.exception;
/**
 * Υ��ҵ�������쳣
 * ��¼��Ϊ�û�����������������Υ��ҵ�������쳣
 * һ�㴦��ģʽ�ǣ��������ݻ��ԣ�������������һ������������
 * @author lk
 *RuntimeException����Ҫǿ��ȥ�����쳣
 */
public class RuleException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//��¼������쳣��ԭ��,ͨ�������¼
	public RuleException(String message) {
		super(message);
	}
}
