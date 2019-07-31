package cn.easyBuy.entity;


/**
 * EasybuyProductCategory entity. @author MyEclipse Persistence Tools
 */

public class ProductCategory implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Integer parentId;
	private Integer type;
	private String iconClass;

	// Constructors

	/** default constructor */
	public ProductCategory() {
	}

	/** minimal constructor */
	public ProductCategory(String name, Integer parentId) {
		this.name = name;
		this.parentId = parentId;
	}

	/** full constructor */
	public ProductCategory(String name, Integer parentId, Integer type, String iconClass) {
		this.name = name;
		this.parentId = parentId;
		this.type = type;
		this.iconClass = iconClass;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getIconClass() {
		return this.iconClass;
	}

	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}

}