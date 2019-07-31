package cn.easyBuy.entity;



/**
 * EasybuyOrderDetail entity. @author MyEclipse Persistence Tools
 */

public class OrderDetail implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer orderId;
	private Integer productId;
	private Integer quantity;
	private Float cost;

	// Constructors

	/** default constructor */
	public OrderDetail() {
	}

	/** full constructor */
	public OrderDetail(Integer orderId, Integer productId, Integer quantity, Float cost) {
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
		this.cost = cost;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Float getCost() {
		return this.cost;
	}

	public void setCost(Float cost) {
		this.cost = cost;
	}

}