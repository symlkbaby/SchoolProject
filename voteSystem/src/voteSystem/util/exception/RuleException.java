package voteSystem.util.exception;
/**
 * 违反业务规则的异常
 * 记录因为用户操作不当而引发的违反业务规则的异常
 * 一般处理模式是：进行数据回显（操作不当再来一波，哈哈哈）
 * @author lk
 *RuntimeException不需要强迫去处理异常
 */
public class RuleException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//记录特殊的异常的原因,通过父类记录
	public RuleException(String message) {
		super(message);
	}
}
