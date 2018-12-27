package voteSystem.Pojo;

public class Option {

	private Integer id;
	private String content;//内容
	private int idx;
	private Integer subjectId;
	private int count=0; // 统计每个选项的投票人数
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Integer integer) {
		this.subjectId = integer;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
