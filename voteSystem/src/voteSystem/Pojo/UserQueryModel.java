package voteSystem.Pojo;

import voteSystem.util.Base.BaseQueryModel;

/**
 * 用户查询模型
 * 查询条件值的子类：继承对应的实体类，
 * 实现BaseQueryModel接口
 * @author lk
 *
 */
public class UserQueryModel extends User implements BaseQueryModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//有特殊的查询条件值，则增加属性
}
