package voteSystem.Pojo;

import java.io.Serializable;

/**
 * 
 * @author lk
 * 用户实体类
 * 1>一个字段=>一个属性
 * 2>给每个属性提供getter和setter访问器
 * 3>考虑重载构造方法（一定要保留默认构造方法）
 * 4>视图属性
 * 5>静态常量
 * 6>导航属性
 */

public class User implements Serializable{

	/**
	 * 加入视图中的数据--视图属性--记录视图中的数据
	 */
	public static final int OFFLINE=1;
	public static final int ONLINE=2;
	public static final String SESSION_NAME="CurrentUser";
	private static final long serialVersionUID = 1L;
	//无值为null
	private Integer id; 
	private String name;
	private String pwd;
	private String confirmPwd;
	//1，不在线,2，在线（因为可能还有其他情况）
	private int online;
	//视图属性
	private String remember;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getOnline() {
		return online;
	}
	public void setOnline(int online) {
		this.online = online;
	}
	public String getConfirmPwd() {
		return confirmPwd;
	}
	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}
	public String getRemember() {
		return remember;
	}
	public void setRemember(String remember) {
		this.remember = remember;
	}
	
	
}
