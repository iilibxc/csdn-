package cn.easyBuy.entity;



/**
 * EasybuyNews entity. @author MyEclipse Persistence Tools
 */

public class News implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private String content;
	private String createTime;

	// Constructors

	/** default constructor */
	public News() {
	}

	/** full constructor */
	public News(String title, String content, String createTime) {
		this.title = title;
		this.content = content;
		this.createTime = createTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}