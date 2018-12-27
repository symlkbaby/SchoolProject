package voteSystem.Pojo;

import java.io.Serializable;

/**
 * 
 * @author lk
 * �û�ʵ����
 * 1>һ���ֶ�=>һ������
 * 2>��ÿ�������ṩgetter��setter������
 * 3>�������ع��췽����һ��Ҫ����Ĭ�Ϲ��췽����
 * 4>��ͼ����
 * 5>��̬����
 * 6>��������
 */

public class User implements Serializable{

	/**
	 * ������ͼ�е�����--��ͼ����--��¼��ͼ�е�����
	 */
	public static final int OFFLINE=1;
	public static final int ONLINE=2;
	public static final String SESSION_NAME="CurrentUser";
	private static final long serialVersionUID = 1L;
	//��ֵΪnull
	private Integer id; 
	private String name;
	private String pwd;
	private String confirmPwd;
	//1��������,2�����ߣ���Ϊ���ܻ������������
	private int online;
	//��ͼ����
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
