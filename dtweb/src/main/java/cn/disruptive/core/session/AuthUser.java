package cn.disruptive.core.session;

import java.io.Serializable;

import cn.disruptive.common.util.Authentication;

public class AuthUser implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer userId;
	//用户名称
	private String userName;

	private Authentication authentication;
	private Integer isLeader;
	public AuthUser(){
		
	}

	public Integer getUserId() {
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

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getIsLeader() {
		return isLeader;
	}

	public void setIsLeader(Integer isLeader) {
		this.isLeader = isLeader;
	}
	
}
