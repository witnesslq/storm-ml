package cn.disruptive.core.vo;

import java.io.Serializable;

import cn.disruptive.common.util.Authentication;

public class AuthUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	//用户名称
	private String userName;
	private Authentication authentication;
	public AuthUser(){
		
	}

	public String getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public Authentication getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
}
