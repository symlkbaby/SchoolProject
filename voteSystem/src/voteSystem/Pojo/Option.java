package voteSystem.Pojo;

public class Option {

	private Integer id;
	private String content;//����
	private int idx;
	private Integer subjectId;
	private int count=0; // ͳ��ÿ��ѡ���ͶƱ����
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
