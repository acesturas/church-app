package com.application.church.features.login;

import java.util.List;

public class LoginResponse {
	
	private boolean success;
	private String token;
	private String firstName;
	private List<String> userAccess;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public List<String> getUserAccess() {
		return userAccess;
	}
	public void setUserAccess(List<String> userAccess) {
		this.userAccess = userAccess;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	

}
