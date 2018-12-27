package voteSystem.Pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import voteSystem.util.format.DateUtil;

public class Subject {

	//使用常量来定义枚举值
	public static final int SINGLE = 1;
	public static final int MULTI=2;
	//使用常量来定义页面上需要显示的那几个值
	public static final String SINGLE_NAME="单选";
	public static final String MULTI_NAME="多选";
	//使用map键值对集合来定义常量和显示值之间的关系
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
	//外键字段加导航属性：使用关联的实体类对象|集合
	//在一方实体类中加入导航属性：多方实体类对象的集合
	//在多方实体类中加入导航属性：一方实体类对象     关系基数
	private User user;
	private int userCount;
	//在一方维护关系
	//选择选项集合
	private List<Option> options;
	private int optionCount;
	//视图属性：jsp页面中需要的值
	//视图值属性是不需要set访问器
	//在给对应的属性赋值时，就给视图属性赋值
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
	
	
	//实体类中如果存在集合导航属性
	//定义构造方法，创建集合对象
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
	
	
	

