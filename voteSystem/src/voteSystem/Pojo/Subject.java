package voteSystem.Pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import voteSystem.util.format.DateUtil;

public class Subject {

	//ʹ�ó���������ö��ֵ
	public static final int SINGLE = 1;
	public static final int MULTI=2;
	//ʹ�ó���������ҳ������Ҫ��ʾ���Ǽ���ֵ
	public static final String SINGLE_NAME="��ѡ";
	public static final String MULTI_NAME="��ѡ";
	//ʹ��map��ֵ�Լ��������峣������ʾֵ֮��Ĺ�ϵ
	public static Map<Integer,String> numberMap 
		     = new HashMap<Integer,String>();
	static{
			numberMap.put(SINGLE,SINGLE_NAME);
			numberMap.put(MULTI, MULTI_NAME);
		}
	private Integer id;
	private String title;
	private int number;
	private Long startTime;
	private Long endTime;
	//����ֶμӵ������ԣ�ʹ�ù�����ʵ�������|����
	//��һ��ʵ�����м��뵼�����ԣ��෽ʵ�������ļ���
	//�ڶ෽ʵ�����м��뵼�����ԣ�һ��ʵ�������     ��ϵ����
	private User user;
	private int userCount;
	//��һ��ά����ϵ
	//ѡ��ѡ���
	private List<Option> options;
	private int optionCount;
	//��ͼ���ԣ�jspҳ������Ҫ��ֵ
	//��ͼֵ�����ǲ���Ҫset������
	//�ڸ���Ӧ�����Ը�ֵʱ���͸���ͼ���Ը�ֵ
	private String startTimeView;
	private String endTimeView;
	private String numberView;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;		
		this.numberView=numberMap.get(this.number);
	}
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;			
		this.startTimeView=DateUtil.toShortDate(this.startTime);
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
		this.endTimeView = DateUtil.toShortDate(this.endTime);
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getStartTimeView() {
		return startTimeView;
	}
	public String getEndTimeView() {
		return endTimeView;
	}
	public String getNumberView() {
		return numberView;
	}
	public List<Option> getOptions() {
		return options;
	}
	public void setOptions(List<Option> options) {
		this.options = options;
		this.optionCount = options.size();
	}
	
	
	//ʵ������������ڼ��ϵ�������
	//���幹�췽�����������϶���
	public Subject() {
		super();
		this.user=new User();
		this.options = new ArrayList<Option>();
	}

	public int getOptionCount() {
		return optionCount;
	}
	public void setOptionCount(int optionCount) {
		this.optionCount = optionCount;
	}
	public int getUserCount() {
		return userCount;
	}
	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}
}
	
	
	

