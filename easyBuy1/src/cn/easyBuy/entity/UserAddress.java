package cn.easyBuy.entity;



import java.sql.Timestamp;

/**
 * EasybuyUserAddress entity. @author MyEclipse Persistence Tools
 */

public class UserAddress implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userId;
	private String address;
	private Timestamp createTime;
	private Integer isDefault;
	private String remark;

	// Constructors

	/** default constructor */
	public UserAddress() {
	}

	/** full constructor */
	public UserAddress(Integer userId, String address, Timestamp createTime, Integer isDefault, String remark) {
		this.userId = userId;
		this.address = address;
		this.createTime = createTime;
		this.isDefault = isDefault;
		this.remark = remark;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getIsDefault() {
		return this.isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}