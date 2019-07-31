package cn.easyBuy.entity;



/**
 * EasybuyProduct entity. @author MyEclipse Persistence Tools
 */

public class Product implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String description;
	private Float price;
	private Integer stock;
	private Integer categoryLevel1id;
	private Integer categoryLevel2id;
	private Integer categoryLevel3id;
	private String fileName;
	private Integer isDelete;

	// Constructors

	/** default constructor */
	public Product() {
	}

	/** minimal constructor */
	public Product(String name, Float price, Integer stock) {
		this.name = name;
		this.price = price;
		this.stock = stock;
	}

	/** full constructor */
	public Product(String name, String description, Float price, Integer stock, Integer categoryLevel1id,
			Integer categoryLevel2id, Integer categoryLevel3id, String fileName, Integer isDelete) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.categoryLevel1id = categoryLevel1id;
		this.categoryLevel2id = categoryLevel2id;
		this.categoryLevel3id = categoryLevel3id;
		this.fileName = fileName;
		this.isDelete = isDelete;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getPrice() {
		return this.price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getStock() {
		return this.stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getCategoryLevel1id() {
		return this.categoryLevel1id;
	}

	public void setCategoryLevel1id(Integer categoryLevel1id) {
		this.categoryLevel1id = categoryLevel1id;
	}

	public Integer getCategoryLevel2id() {
		return this.categoryLevel2id;
	}

	public void setCategoryLevel2id(Integer categoryLevel2id) {
		this.categoryLevel2id = categoryLevel2id;
	}

	public Integer getCategoryLevel3id() {
		return this.categoryLevel3id;
	}

	public void setCategoryLevel3id(Integer categoryLevel3id) {
		this.categoryLevel3id = categoryLevel3id;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

}