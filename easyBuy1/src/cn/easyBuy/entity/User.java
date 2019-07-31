package cn.easyBuy.entity;


/**
 * EasybuyUser entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields
//数据库字段对应
	private Integer id;
	private String loginName;
	private String userName;
	private String password;
	private Integer sex;
	private String identityCode;
	private String email;
	private String mobile;
	private Integer type;

	//注册 表单
	private String repassword;	
	private String verifyCode;
	
	// Constructors

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String loginName, String userName, String password, Integer sex) {
		this.loginName = loginName;
		this.userName = userName;
		this.password = password;
		this.sex = sex;
	}

	/** full constructor */
	public User(String loginName, String userName, String password, Integer sex, String identityCode,
			String email, String mobile, Integer type) {
		this.loginName = loginName;
		this.userName = userName;
		this.password = password;
		this.sex = sex;
		this.identityCode = identityCode;
		this.email = email;
		this.mobile = mobile;
		this.type = type;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getIdentityCode() {
		return this.identityCode;
	}

	public void setIdentityCode(String identityCode) {
		this.identityCode = identityCode;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}