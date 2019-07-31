package cn.easyBuy.entity;



import java.sql.Timestamp;

/**
 * EasybuyOrder entity. @author MyEclipse Persistence Tools
 */

public class Order implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userId;
	private String loginName;
	private String userAddress;
	private Timestamp createTime;
	private Float cost;
	private String serialNumber;

	// Constructors

	/** default constructor */
	public Order() {
	}

	/** full constructor */
	public Order(Integer userId, String loginName, String userAddress, Timestamp createTime, Float cost,
			String serialNumber) {
		this.userId = userId;
		this.loginName = loginName;
		this.userAddress = userAddress;
		this.createTime = createTime;
		this.cost = cost;
		this.serialNumber = serialNumber;
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

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserAddress() {
		return this.userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Float getCost() {
		return this.cost;
	}

	public void setCost(Float cost) {
		this.cost = cost;
	}

	public String getSerialNumber() {
		return this.serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

}