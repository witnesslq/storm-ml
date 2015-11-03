package cn.disruptive.common.util;

import java.io.Serializable;


public class Authentication implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3910493478267433862L;
	
	//账户ID
	private Integer userId;
	//用户名称
	private String userName;
	
	public Authentication(){
		
	}

	public Integer getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
